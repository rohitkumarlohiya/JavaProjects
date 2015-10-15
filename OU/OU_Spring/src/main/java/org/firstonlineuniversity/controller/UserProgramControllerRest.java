package org.firstonlineuniversity.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddUserProgramFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditUserProgramFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.QuickCodes;
import org.firstonlineuniversity.models.courses.UserProgramCources;
import org.firstonlineuniversity.models.courses.UserPrograms;
import org.firstonlineuniversity.models.helper.UserProgramCoursesWrapper;
import org.firstonlineuniversity.models.helper.UserProgramWrapper;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.models.views.CourseCatalogView;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/users/program")
@PropertySource("classpath:message.properties")
public class UserProgramControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	@Autowired
	AddUserProgramFormValidation addUserProgramFormValidation;

	@Autowired
	EditUserProgramFormValidation editUserProgramFormValidation;

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
	 * Get user program List by Program id and user id
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<UserProgramWrapper> getUserProgramList(
			@RequestParam(required = false) Long programId,
			@RequestParam(required = false) Long userId)
			throws HibernateException, Exception {
		List<UserProgramWrapper> userProgramsWrapperList = new ArrayList<UserProgramWrapper>();

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<UserPrograms> userProgramsDb = customServices.getEdutabdleData(
				customUser.getId(), programId);

		for (UserPrograms userPrograms : userProgramsDb) {

			UserPrograms programs = new UserPrograms(
					userPrograms.getProgramName(), userPrograms.getCredits(),
					userPrograms.getProgranDescription(),
					userPrograms.getExpectedComletionTime(),
					userPrograms.isReminder(), null);
			programs.setId(userPrograms.getId());

			UserProgramWrapper userProgramWrapper = new UserProgramWrapper(
					programs);

			Set<UserProgramCources> userProgramCourses = new HashSet<UserProgramCources>();
			Set<UserProgramCoursesWrapper> userProgramCoursesWrapper = new HashSet<UserProgramCoursesWrapper>();
			for (UserProgramCources userProgramCourcesDb : userPrograms
					.getUserProgramCourses()) {
				UserProgramCoursesWrapper programCoursesWrapper = new UserProgramCoursesWrapper();
				UserProgramCources userProgramCources = new UserProgramCources(
						userProgramCourcesDb.getProgramName(),
						userProgramCourcesDb.getPriority(), 0, null,
						userProgramCourcesDb.getCourses(),
						userProgramCourcesDb.getTargetStartDate(),
						userProgramCourcesDb.getTargetEndDate());
				userProgramCources.setId(userProgramCourcesDb.getId());
				userProgramCourses.add(userProgramCources);
				programCoursesWrapper.setUserProgramCources(userProgramCources);

				Long courseId = userProgramCources.getCourses();
				try {
					CourseCatalogView courseCatalogView = customServices
							.getCourseCatalogView(courseId);
					programCoursesWrapper
							.setCourseCatalogView(courseCatalogView);
				} catch (ClassCastException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				userProgramCoursesWrapper.add(programCoursesWrapper);
			}
			userProgramWrapper
					.setUserProgramCoursesWrapper(new ArrayList<UserProgramCoursesWrapper>(
							userProgramCoursesWrapper));
			userProgramsWrapperList.add(userProgramWrapper);
		}
		return userProgramsWrapperList;
	}

	/*
	 * Add User Programs
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody UserPrograms userPrograms)
			throws HibernateException, Exception {

		if (userPrograms == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = addUserProgramFormValidation
				.validateForm(userPrograms);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userPrograms.setAccounts(new Accounts(customUser.getId()));
		try {
			userPrograms.setCD(new Date());
			userPrograms.setUD(new Date());

			userPrograms.setUB(customUser.getId());
			userPrograms.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(userPrograms);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("user.program.add"));

	}

	/*
	 * Edit User Programs
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AbstractEntity edit(@PathVariable long id)
			throws HibernateException, Exception {

		UserPrograms userProgramsDb = (UserPrograms) dataServices
				.getEntityByIdByHQL("UserPrograms", id);
		if (userProgramsDb == null)
			throw new EntityNotFoundException();

		UserPrograms userPrograms = new UserPrograms(
				userProgramsDb.getProgramName(), userProgramsDb.getCredits(),
				userProgramsDb.getProgranDescription(),
				userProgramsDb.getExpectedComletionTime(),
				userProgramsDb.isReminder(), null);
		userPrograms.setId(id);
		return userPrograms;
	}

	/*
	 * Update User Programs
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody UserPrograms userPrograms) throws HibernateException,
			Exception {
		if (userPrograms == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editUserProgramFormValidation
				.validateForm(userPrograms);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {

			userPrograms.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			userPrograms.setUB(customUser.getId());
			userPrograms.setAccounts(new Accounts(customUser.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(userPrograms);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("user.programs.update"));
	}

	/*
	 * Delete User Programs
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, UserPrograms.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("user.programs.delete"));

	}

}
