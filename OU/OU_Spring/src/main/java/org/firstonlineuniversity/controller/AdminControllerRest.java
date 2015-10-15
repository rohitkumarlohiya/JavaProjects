package org.firstonlineuniversity.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.domains.custom.CustomAccounts;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.formvalidation.rest.SignupFormValidation;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.login.UserRole;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CustomServices;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.services.EntitySummaryServices;
import org.hibernate.HibernateException;
import org.jboss.netty.handler.codec.http.HttpResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/admin")
@PropertySource("classpath:message.properties")
public class AdminControllerRest {

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	EntitySummaryServices entitySummaryServices;

	@Autowired
	SignupFormValidation signupFormValidation;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger.getLogger(AdminControllerRest.class);

	/*
	 * Get Users List
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<CustomAccounts> getAccountsList() {
		List<AbstractEntity> accountListDB = null;
		CustomAccounts customAccounts = null;
		List<CustomAccounts> customAccountsList = null;

		try {
			accountListDB = dataServices.getEntityList(Accounts.class);

			if (accountListDB != null) {
				customAccountsList = new ArrayList<CustomAccounts>();
			}

			for (AbstractEntity abstractEntityDB : accountListDB) {
				Accounts accounts = (Accounts) abstractEntityDB;
				Set<String> roles = new HashSet<String>();
				for (UserRole role : accounts.getUserRole()) {
					roles.add(role.getRole());
				}

				customAccounts = new CustomAccounts(accounts.getAccountEmail(),
						accounts.getAccountPhone(), accounts.getPassword(),
						accounts.getFirstName(), accounts.getLastName(),
						accounts.getAccessLevel(), accounts.isActivated(),
						roles);
				customAccountsList.add(customAccounts);

			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (customAccountsList == null) {
			throw new ListNotFoundException();
		}
		return customAccountsList;

	}

	/*
	 * Get User by id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Accounts getUser(@PathVariable("id") long id) {
		Accounts accounts = null;
		try {
			accounts = (Accounts) dataServices.getEntity(id, Accounts.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (accounts == null) {
			throw new EntityNotFoundException();
		}
		return accounts;
	}

	/*
	 * Update a user
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status editUser(@RequestBody Accounts accounts) {
		if (accounts == null) {
			throw new EmptyFormElementsException();
		}

		// need a little optimization
		List<Errors> errorsList = signupFormValidation.validateForm(accounts);
		if (!signupFormValidation.validateForm(accounts).isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}

		try {
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			accounts.setUB(customUser.getId());
			accounts.setCB(customUser.getId());
			accounts.setUD(new Date());

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			dataServices.updateUserEntity(accounts);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}
		return new Status(HttpStatus.OK.toString(),
				env.getProperty("status.successupdate"));
	}

	/*
	 * Add a role
	 */
	@RequestMapping(value = "/role/add", method = RequestMethod.GET)
	public @ResponseBody Status addRole(@RequestParam String role,
			HttpServletResponse httpResponse, @RequestParam Long accountId) {
		if (accountId == null || role == null)
			throw new SomethingWentWrongException();
		boolean flag = customServices.updateRoles(role, 0, accountId);
		if (flag) {
			return new Status(HttpStatus.OK.toString(),
					env.getProperty("user.role.add"));
		} else {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new Status(HttpStatus.BAD_REQUEST.toString(),
					env.getProperty("user.role.update.failed"));
		}
	}

	/*
	 * Remove a role
	 */
	@RequestMapping(value = "/role/remove", method = RequestMethod.GET)
	public @ResponseBody Status removeRole(@RequestParam String role,
			HttpServletResponse httpResponse, @RequestParam Long accountId) {
		if (accountId == null || role == null)
			throw new SomethingWentWrongException();
		boolean flag = customServices.updateRoles(role, 1, accountId);
		if (flag) {
			return new Status(HttpStatus.OK.toString(),
					env.getProperty("user.role.delete"));
		} else {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new Status(HttpStatus.BAD_REQUEST.toString(),
					env.getProperty("user.role.update.failed"));
		}
	}
}
