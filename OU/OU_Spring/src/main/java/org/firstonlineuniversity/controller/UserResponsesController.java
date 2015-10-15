package org.firstonlineuniversity.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.models.userquestions.UserQuestionOptions;
import org.firstonlineuniversity.services.CassandraService;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.utils.Utils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/users/ques/responses")
@PropertySource("classpath:message.properties")
public class UserResponsesController {
	@Autowired
	Environment env;

	@Autowired
	Utils utils;

	@Autowired
	CassandraService cassandraService;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	static final Logger logger = Logger
			.getLogger(UserResponsesController.class);

	/*
	 * Add User Responses
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status add(
			@RequestBody UserQuestionOptions userQuestionOptions)
			throws HibernateException, Exception {
		if (userQuestionOptions == null) {
			throw new EmptyFormElementsException();
		}
		new Date().toString();
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userQuestionOptions.setAccountId(customUser.getId());

		Accounts accounts = (Accounts) dataServices.getEntity(
				customUser.getId(), Accounts.class);
		String name = "";
		if (accounts.getFirstName() != null)
			name += accounts.getFirstName();

		if (accounts.getLastName() != null)
			name += " " + accounts.getLastName();

		userQuestionOptions.setName(name);

		userQuestionOptions.setResponseId(utils.getUuid());

		cassandraService.addUserOptions(userQuestionOptions);

		return new Status(userQuestionOptions.getResponseId(),
				HttpStatus.OK.toString(), env.getProperty("opt.add"));
	}

	/*
	 * User Responses List
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(required = false) Long accountId,
			@RequestParam(required = false) UUID questionId)
			throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (accountId == null)
			accountId = customUser.getId();

		List<UserQuestionOptions> list = cassandraService.getUserOptions(
				accountId, questionId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentUserId", customUser.getId());
		map.put("list", list);

		return map;
	}

	/*
	 * Edit User Responses
	 */
	@RequestMapping(value = "/{optionID}", method = RequestMethod.GET)
	@ResponseBody
	public UserQuestionOptions notes(@RequestParam UUID questionId,
			@PathVariable UUID optionID) throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		UserQuestionOptions questionOptions = cassandraService
				.getUserOptionsById(customUser.getId(), questionId, optionID);

		return questionOptions;
	}

	/*
	 * Update User Responses
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status update(
			@RequestBody UserQuestionOptions userQuestionOptions)
			throws HibernateException, Exception {
		if (userQuestionOptions == null) {
			throw new EmptyFormElementsException();
		}

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		cassandraService.updateUserOptions(userQuestionOptions);
		userQuestionOptions.setAccountId(customUser.getId());
		return new Status(HttpStatus.OK.toString(),
				env.getProperty("opt.update"));
	}

	/*
	 * Delete User Responses
	 */
	@RequestMapping(value = "/{optionID}", method = RequestMethod.DELETE)
	public @ResponseBody Status delete(@RequestParam UUID questionId,
			@PathVariable UUID optionID) throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		cassandraService.deleteUserResponse(customUser.getId(), questionId,
				optionID);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("opt.delete"));
	}

}
