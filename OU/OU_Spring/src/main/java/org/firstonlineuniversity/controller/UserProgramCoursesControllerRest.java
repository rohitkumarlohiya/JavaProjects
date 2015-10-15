package org.firstonlineuniversity.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddUserProgramCoursesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditUserProgramCoursesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.QuickCodes;
import org.firstonlineuniversity.models.courses.UserProgramCources;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.services.EntitySummaryServices;
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
@RequestMapping("api/users/program/courses")
@PropertySource("classpath:message.properties")
public class UserProgramCoursesControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	EntitySummaryServices entitySummaryServices;

	@Autowired
	AddUserProgramCoursesFormValidation addUserProgramCoursesFormValidation;

	@Autowired
	EditUserProgramCoursesFormValidation editUserProgramCoursesFormValidation;

	static final Logger logger = Logger
			.getLogger(QuickCodesRestController.class);

	/*
	 * Get List by Type
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<AbstractEntity> getCurseListByFilter(
			@RequestParam String type) throws HibernateException, Exception {

		// AbstractEntityList abstractEntityList = null;
		List<AbstractEntity> abstractEntities = dataServices.getCodesByType(
				QuickCodes.class, type);

		if (abstractEntities == null || abstractEntities.size() == 0)
			throw new ListNotFoundException();

		return abstractEntities;

	}

	/*
	 * Add User Program Courses
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody UserProgramCources userProgramCources)
			throws HibernateException, Exception {

		if (userProgramCources == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = addUserProgramCoursesFormValidation
				.validateForm(userProgramCources);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("user.program.courses.add"), errorsList);

		}
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userProgramCources.setAccounts((customUser.getId()));
		try {
			userProgramCources.setCD(new Date());
			userProgramCources.setUD(new Date());

			userProgramCources.setUB(customUser.getId());
			userProgramCources.setCB(customUser.getId());
			userProgramCources.setTargetStartDate(new SimpleDateFormat()
					.parse(userProgramCources.getTargetStartDateSt()));
			userProgramCources.setTargetEndDate(new SimpleDateFormat()
					.parse(userProgramCources.getTargetEndDateSt()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(userProgramCources);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("user.program.courses.add"));

	}

	/*
	 * Edit User Program Courses
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AbstractEntity edit(@PathVariable long id)
			throws HibernateException, Exception {

		UserProgramCources userProgramCourcesDb = (UserProgramCources) dataServices
				.getEntityByIdByHQL("UserProgramCources", id);

		if (userProgramCourcesDb == null)
			throw new EntityNotFoundException();

		UserProgramCources userProgramCources = new UserProgramCources(
				userProgramCourcesDb.getProgramName(),
				userProgramCourcesDb.getPriority(),
				userProgramCourcesDb.getAccounts(), null,
				userProgramCourcesDb.getCourses(),
				userProgramCourcesDb.getTargetStartDate(),
				userProgramCourcesDb.getTargetEndDate());
		userProgramCources.setId(id);
		return userProgramCources;
	}

	/*
	 * Update User Program Courses
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody UserProgramCources userProgramCources)
			throws HibernateException, Exception {
		if (userProgramCources == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editUserProgramCoursesFormValidation
				.validateForm(userProgramCources);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userProgramCources.setAccounts((customUser.getId()));
		try {
			userProgramCources.setUD(new Date());
			userProgramCources.setUB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(userProgramCources);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("user.program.courses.update"));
	}

	/*
	 * Delete User Program Courses
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, UserProgramCources.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("user.program.courses.delete"));

	}

	/*
	 * Get User Program Course Summary
	 */
	@RequestMapping(value = "summary/{id}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCoursesSummary(
			@PathVariable("id") long id) {
		Map<String, Object> courseInformationMap = null;

		try {
			courseInformationMap = entitySummaryServices
					.courseHeirarchyFull(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (courseInformationMap == null) {
			throw new EntityNotFoundException();
		}
		return courseInformationMap;
	}
}
