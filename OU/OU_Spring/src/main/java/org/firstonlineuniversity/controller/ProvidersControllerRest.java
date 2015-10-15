package org.firstonlineuniversity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddProvidersFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditProvidersFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.Providers;
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
@RequestMapping("/api/users/providers/")
@PropertySource("classpath:message.properties")
public class ProvidersControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	@Autowired
	AddProvidersFormValidation addProvidersFormValidation;

	@Autowired
	EditProvidersFormValidation editProvidersFormValidation;

	static final Logger logger = Logger
			.getLogger(QuickCodesRestController.class);

	/*
	 * Add Provider
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody Providers providers)
			throws HibernateException, Exception {

		if (providers == null) {
			throw new EmptyFormElementsException();
		}
		try {
			providers.setCD(new Date());
			providers.setUD(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Errors> errorsList = addProvidersFormValidation
				.validateForm(providers);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		try {
			providers.setCD(new Date());
			providers.setUD(new Date());

			providers.setUB(customUser.getId());
			providers.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (providers.getAccounts() == null
				|| providers.getAccounts().getId() == 0)
			providers.setAccounts(new Accounts(customUser.getId()));

		dataServices.addEntity(providers);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.provider.add"));

	}

	/*
	 * View Provider
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AbstractEntity view(@PathVariable long id)
			throws HibernateException, Exception {

		logger.info("ID: " + id);

		Providers providerDB = (Providers) dataServices.getEntityByIdByHQL(
				"Providers", id);
		
		if (providerDB == null)
			throw new EntityNotFoundException();
		
		Accounts accounts = providerDB.getAccounts();

		Providers provider = new Providers(null, providerDB.getProviderType(),
				providerDB.getProviderName(), providerDB.getExperience(),
				providerDB.getIntroduction(), providerDB.getContactId(),
				providerDB.getProviderPhotoLink(), providerDB.getDesignation(),
				providerDB.getDepartment(), providerDB.isEnabled(), null, null,
				providerDB.getCD(), providerDB.getCB());
		provider.setId(id);
		provider.setAccounts(new Accounts(accounts.getId()));
		return provider;
	}

	/*
	 * Update Provider
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status update(@RequestBody Providers providers)
			throws HibernateException, Exception {
		if (providers == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editProvidersFormValidation
				.validateForm(providers);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}

		AbstractEntity providersDb = dataServices.getEntity(providers.getId(),
				Providers.class);
		try {
			providers.setUD(new Date());
			providers.setCD(providersDb.getCD());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			providers.setUB(customUser.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(providers);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("courses.providers.update"));
	}

	/*
	 * Disable Provider
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.disableEntity(id, Providers.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.providers.disabled"));

	}

	/*
	 * Provider by account Id
	 */
	@RequestMapping(value = "/byaccount", method = RequestMethod.GET)
	@ResponseBody
	public List<Providers> byAccountId() throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<Providers> providersDB = customServices
				.providerListByuAccountId(customUser.getId());

		List<Providers> providers = new ArrayList<Providers>();

		for (Providers providerDB : providersDB) {

			Providers provider = new Providers(null,
					providerDB.getProviderType(), providerDB.getProviderName(),
					providerDB.getExperience(), providerDB.getIntroduction(),
					providerDB.getContactId(),
					providerDB.getProviderPhotoLink(),
					providerDB.getDesignation(), providerDB.getDepartment(),
					providerDB.isEnabled(), null, null, providerDB.getCD(),
					providerDB.getCB());
			provider.setId(providerDB.getId());
			provider.setAccounts(new Accounts(customUser.getId()));
			providers.add(provider);
		}
		return providers;
	}
}
