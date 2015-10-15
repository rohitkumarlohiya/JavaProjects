package org.firstonlineuniversity.controller;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.UserContent;
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
@RequestMapping("/api/users/contentstatus")
@PropertySource("classpath:message.properties")
public class UserContentControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger
			.getLogger(LecturesAdminControllerRest.class);

	/*
	 * Add User Content
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody UserContent userContent)
			throws HibernateException, Exception {
		if (userContent == null)
			throw new EntityNotFoundException();

		try {
			userContent.setCD(new Date());
			userContent.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			userContent.setAccountId(customUser.getId());
			userContent.setUB(customUser.getId());
			userContent.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userContent.getId() != null && userContent.getId() != 0)
			dataServices.updateEntity(userContent);
		else
			dataServices.addEntity(userContent);
		
		return new Status(userContent.getId(), HttpStatus.ACCEPTED.toString(),
				env.getProperty("user.content.add"));

	}

	/*
	 * Get User Content
	 */
	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> get(@PathVariable long courseId)
			throws HibernateException, Exception {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Map<String, Object> userContents = customServices
				.getUserContentByUserIdAndCourseId(customUser.getId(), courseId);
		if (userContents == null)
			throw new EntityNotFoundException();
		return userContents;
	}

}
