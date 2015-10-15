package org.firstonlineuniversity.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.enrollements.UniversityMembership;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CustomServices;
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
@RequestMapping("api/users/membership")
@PropertySource("classpath:message.properties")
public class AdminUniversityMembershipControllerRest {
	@Autowired
	Environment env;

	@Autowired
	CustomServices customServices;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	/*
	 * Add Membership
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody UniversityMembership universityMembership)
			throws HibernateException, Exception {
		if (universityMembership == null)
			throw new EntityNotFoundException();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		universityMembership.setStartDate(dateFormat.parse(universityMembership
				.getStrStartDate()));
		universityMembership.setEndDate(dateFormat.parse(universityMembership
				.getStrEndDate()));
		universityMembership.setRenewalDate(dateFormat
				.parse(universityMembership.getStrRenewDate()));
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		universityMembership.setAccounts(new Accounts(customUser.getId()));
		try {
			universityMembership.setCD(new Date());
			universityMembership.setUD(new Date());
			universityMembership.setUB(customUser.getId());
			universityMembership.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		dataServices.addEntity(universityMembership);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("member.add"));

	}

	/*
	 * Edit Membership
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getUser(@PathVariable("id") long id) {
		AbstractEntity abstractEntity = null;
		try {
			abstractEntity = (AbstractEntity) customServices.getEdutableData(
					"UniversityMembership", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (abstractEntity == null)
			throw new EntityNotFoundException();
		UniversityMembership universityMembershipDB = (UniversityMembership) abstractEntity;
		UniversityMembership universityMembership = new UniversityMembership(
				universityMembershipDB.getId(),
				universityMembershipDB.getMembershipType(),
				universityMembershipDB.getGroupId(),
				universityMembershipDB.getStartDate(),
				universityMembershipDB.getEndDate(),
				universityMembershipDB.getRenewalDate(),
				universityMembershipDB.isNotifyExpiary(),
				universityMembershipDB.getExpiaryThreshold());

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("accounts",
				String.valueOf(universityMembershipDB.getAccounts().getId()));
		map.put("universityMembership", universityMembership);

		return map;
	}

	/*
	 * Update Membership
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody UniversityMembership universityMembership)
			throws HibernateException, Exception {
		if (universityMembership == null) {
			throw new EmptyFormElementsException();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		universityMembership.setStartDate(universityMembership.getStartDate());
		universityMembership.setEndDate(universityMembership.getEndDate());
		universityMembership.setRenewalDate(universityMembership
				.getRenewalDate());

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		universityMembership.setAccounts(new Accounts(customUser.getId()));

		try {
			universityMembership.setUD(new Date());
			universityMembership.setUB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(universityMembership);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("member.successupdate"));
	}

	/*
	 * Delete Enrollments
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, UniversityMembership.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("member.delete"));

	}

}
