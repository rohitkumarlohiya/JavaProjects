package org.firstonlineuniversity.controller;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.models.notes.UserNotes;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CassandraService;
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
@RequestMapping("/api/users/notes")
@PropertySource("classpath:message.properties")
public class UserNotesController {
	@Autowired
	Environment env;

	@Autowired
	Utils utils;

	@Autowired
	CassandraService cassandraService;

	static final Logger logger = Logger
			.getLogger(AdminCardsControllerRest.class);

	/*
	 * Add User Notes
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status add(@RequestBody UserNotes userNotes)
			throws HibernateException, Exception {
		if (userNotes == null) {
			throw new EmptyFormElementsException();
		}
		new Date().toString();
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userNotes.setAccountId(customUser.getId());
		userNotes.setNotesId(utils.getUuid());
		cassandraService.addUserNotes(userNotes);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("notes.add"));
	}

	/*
	 * User Notes List
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<UserNotes> list(@RequestParam(required = false) Long courseId)
			throws HibernateException, Exception {

		if (courseId == null)
			courseId = 0l;

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<UserNotes> list = cassandraService.getUserNotes(
				customUser.getId(), courseId);

		return list;
	}

	/*
	 * Edit Notes List
	 */
	@RequestMapping(value = "/{noteId}", method = RequestMethod.GET)
	@ResponseBody
	public UserNotes notes(@RequestParam Long courseId,
			@PathVariable UUID noteId) throws HibernateException, Exception {

		if (courseId == null)
			courseId = 0l;

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		UserNotes userNotes = cassandraService.getUserNotesById(
				customUser.getId(), courseId, noteId);

		return userNotes;
	}

	/*
	 * Update User Notes
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status update(@RequestBody UserNotes userNotes)
			throws HibernateException, Exception {
		if (userNotes == null) {
			throw new EmptyFormElementsException();
		}

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		cassandraService.updateUserNotes(customUser.getId(),
				userNotes.getCourseId(), userNotes.getNotesId(), userNotes);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("notes.update"));
	}

	/*
	 * Delete User Notes
	 */
	@RequestMapping(value = "/{noteId}", method = RequestMethod.DELETE)
	public @ResponseBody Status delete(@RequestParam Long courseId,
			@PathVariable UUID noteId) throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		cassandraService.deleteUserNotes(customUser.getId(), courseId, noteId);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("notes.delete"));
	}

}
