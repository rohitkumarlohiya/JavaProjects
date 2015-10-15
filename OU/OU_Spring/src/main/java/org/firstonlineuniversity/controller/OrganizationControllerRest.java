package org.firstonlineuniversity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.BadRequestException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.profiles.Organization;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.DataServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/org")
@PropertySource("classpath:message.properties")
public class OrganizationControllerRest {

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	static final Logger logger = Logger
			.getLogger(OrganizationControllerRest.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Organization> list() {
		List<AbstractEntity> orgListDb = null;
		List<Organization> oglst = new ArrayList<Organization>();
		try {
			orgListDb = dataServices.getEntityList(Organization.class);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}
		if (orgListDb == null)
			throw new ListNotFoundException();

		for (AbstractEntity org : orgListDb) {
			Organization o = (Organization) org;
			Organization or = new Organization(o.getOrganizationType(),
					o.getOrganizationName(), o.getOrganizationDescription(),
					o.getPhotoLink(), null, null, o.getCountryCode(),
					o.isPartnerFlag(), o.getLogoLink());
			or.setEnabled(o.isEnabled());
			or.setCountryCode(o.getCountryCode());
			or.setPartnerFlag(o.isPartnerFlag());
			or.setId(o.getId());
			oglst.add(or);
		}

		return oglst;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Organization edit(@PathVariable long id) {
		AbstractEntity orgListDb = null;
		try {
			orgListDb = dataServices.getEntity(id, Organization.class);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}
		if (orgListDb == null)
			throw new EntityNotFoundException();

		Organization o = (Organization) orgListDb;
		Organization or = new Organization(o.getOrganizationType(),
				o.getOrganizationName(), o.getOrganizationDescription(),
				o.getPhotoLink(), null, null, o.getCountryCode(),
				o.isPartnerFlag(), o.getLogoLink());
		or.setId(o.getId());
		return or;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody Organization organization) {
		if (organization == null)
			throw new EntityNotFoundException();
		try {
			organization.setCD(new Date());
			organization.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			organization.setUB(customUser.getId());
			organization.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			dataServices.addEntity(organization);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("organization.add"));

	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status update(@RequestBody Organization organization) {
		if (organization == null)
			throw new BadRequestException();
		try {
			organization.setUD(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			dataServices.updateOrg(organization);
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			organization.setUB(customUser.getId());
			organization.setUD(new Date());

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("organization.update"));
	}
}
