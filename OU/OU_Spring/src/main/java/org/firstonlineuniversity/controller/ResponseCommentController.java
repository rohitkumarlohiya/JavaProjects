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
import org.firstonlineuniversity.models.userquestions.UserResponseComments;
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
@RequestMapping("/api/users/ques/responses/comments")
@PropertySource("classpath:message.properties")
public class ResponseCommentController {
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
			@RequestBody UserResponseComments userResponseComments)
			throws HibernateException, Exception {
		if (userResponseComments == null) {
			throw new EmptyFormElementsException();
		}
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Accounts accounts = (Accounts) dataServices.getEntity(
				customUser.getId(), Accounts.class);
		String name = "";
		if (accounts.getFirstName() != null)
			name += accounts.getFirstName();

		if (accounts.getLastName() != null)
			name += " " + accounts.getLastName();

		userResponseComments.setName(name);
		userResponseComments.setAccountId(customUser.getId());
		userResponseComments.setCommentid(utils.getUuid());
		cassandraService.addUserOptionsComments(userResponseComments);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("optcomment.add"));
	}

	/*
	 * User Responses List
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(required = false) UUID responseid,
			@RequestParam(required = false) UUID commentid)
			throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<UserResponseComments> list = cassandraService
				.getUserResponseComments(responseid, commentid);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentUserId", customUser.getId());
		map.put("list", list);

		return map;
	}

	/*
	 * Edit User Responses
	 */
	@RequestMapping(value = "/{responseid}", method = RequestMethod.GET)
	@ResponseBody
	public UserResponseComments notes(@RequestParam UUID commentid,
			@PathVariable UUID responseid) throws HibernateException, Exception {

		UserResponseComments userResponseComments = cassandraService
				.getUserResponseCommentsById(responseid, commentid);

		return userResponseComments;
	}

	/*
	 * Update User Responses
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status update(
			@RequestBody UserResponseComments userResponseComments)
			throws HibernateException, Exception {
		if (userResponseComments == null) {
			throw new EmptyFormElementsException();
		}

		cassandraService.updateUserResponseComments(userResponseComments);
		return new Status(HttpStatus.OK.toString(),
				env.getProperty("optcomment.update"));
	}

	/*
	 * Delete User Responses
	 */
	@RequestMapping(value = "/{responseid}", method = RequestMethod.DELETE)
	public @ResponseBody Status delete(@RequestParam UUID commentid,
			@PathVariable UUID responseid) throws HibernateException, Exception {

		cassandraService.deleteUserResponseComments(responseid, commentid);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("optcomment.delete"));
	}

}
