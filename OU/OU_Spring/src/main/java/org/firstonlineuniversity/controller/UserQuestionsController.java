package org.firstonlineuniversity.controller;

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
import org.firstonlineuniversity.models.userquestions.UserQuestions;
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
@RequestMapping("/api/users/questions")
@PropertySource("classpath:message.properties")
public class UserQuestionsController {
	@Autowired
	Environment env;

	@Autowired
	Utils utils;

	@Autowired
	CassandraService cassandraService;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	static final Logger logger = Logger
			.getLogger(AdminCardsControllerRest.class);

	/*
	 * Add User Questions
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Status add(@RequestBody UserQuestions userQuestions)
			throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Accounts accounts = (Accounts) dataServices.getEntity(
				customUser.getId(), Accounts.class);
		String accountname = "";
		if (accounts.getFirstName() != null)
			accountname += accounts.getFirstName();

		if (accounts.getLastName() != null)
			accountname += " " + accounts.getLastName();

		userQuestions.setAccountname(accountname);

		String tagString = userQuestions.getTags();
		String tag = "{}";

		if (tagString != null) {
			String[] tagsArr = tagString.split(",");

			tag = "{";
			for (int i = 0; i < tagsArr.length; i++) {
				tag += "\'" + tagsArr[i] + "\'";
				if (i != tagsArr.length - 1)
					tag += ",";
			}
			tag += "}";
		}
		logger.info("tag: " + tag);

		userQuestions.setTags(tag);
		userQuestions.setQuestionId(utils.getUuid());
		userQuestions.setAccountId(customUser.getId());

		cassandraService.addUserQuestions(userQuestions);
		return new Status(HttpStatus.OK.toString(), env.getProperty("ques.add"));
	}

	/*
	 * User Questions List
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(required = false) Long courseId)
			throws HibernateException, Exception {

		if (courseId == null)
			courseId = 0l;

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<UserQuestions> list = cassandraService.getUserQuestions(
				customUser.getId(), courseId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentUserId", customUser.getId());
		map.put("list", list);

		return map;
	}

	/*
	 * Edit User Question
	 */
	@RequestMapping(value = "/{questionId}", method = RequestMethod.GET)
	@ResponseBody
	public UserQuestions notes(@RequestParam Long courseId,
			@PathVariable UUID questionId) throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		UserQuestions userQuestions = cassandraService.getUserQuestionsById(
				customUser.getId(), courseId, questionId);

		return userQuestions;
	}

	/*
	 * Update User Question
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status update(@RequestBody UserQuestions userQuestions)
			throws HibernateException, Exception {
		if (userQuestions == null) {
			throw new EmptyFormElementsException();
		}
		String tagString = userQuestions.getTags();
		String[] tagsArr = tagString.split(",");

		String tag = "{";
		for (int i = 0; i < tagsArr.length; i++) {
			tag += "\'" + tagsArr[i] + "\'";
			if (i != tagsArr.length - 1)
				tag += ",";
		}
		tag += "}";
		logger.info("tag: " + tag);

		userQuestions.setTags(tag);
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		cassandraService.updateUserQuestions(userQuestions, customUser.getId());

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("questions.update"));
	}

	/*
	 * Delete User Question
	 */
	@RequestMapping(value = "/{questionId}", method = RequestMethod.DELETE)
	public @ResponseBody Status delete(@RequestParam Long courseId,
			@PathVariable UUID questionId) throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		cassandraService.deleteUserQuestions(customUser.getId(), courseId,
				questionId);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("questions.delete"));
	}
}
