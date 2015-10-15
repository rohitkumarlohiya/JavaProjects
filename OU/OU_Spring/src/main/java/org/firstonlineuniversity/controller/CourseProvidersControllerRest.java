package org.firstonlineuniversity.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddProvidersFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditProvidersFormValidation;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseProviders;
import org.firstonlineuniversity.models.status.Status;
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
@RequestMapping("/api/users/courseproviders")
@PropertySource("classpath:message.properties")
public class CourseProvidersControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	AddProvidersFormValidation addCourseProvidersFormValidation;

	@Autowired
	EditProvidersFormValidation editCourseProvidersFormValidation;

	static final Logger logger = Logger
			.getLogger(QuickCodesRestController.class);

	/*
	 * Add Course Provider
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CourseProviders courseProviders)
			throws HibernateException, Exception {

		if (courseProviders == null) {
			throw new EmptyFormElementsException();
		}
		try {
			courseProviders.setCD(new Date());
			courseProviders.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseProviders.setAccountId(customUser.getId());
			courseProviders.setUB(customUser.getId());
			courseProviders.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(courseProviders);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.provider.add"));

	}

	/*
	 * Edit Course Provider
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AbstractEntity edit(@PathVariable long id)
			throws HibernateException, Exception {

		logger.info("ID: " + id);

		CourseProviders courseProviders = (CourseProviders) dataServices
				.getEntityByIdByHQL("CourseProviders", id);
		if (courseProviders == null)
			throw new EntityNotFoundException();

		CourseProviders provider = new CourseProviders(
				courseProviders.isPrimaryProvider(),
				courseProviders.getProviderRole(),
				courseProviders.getCourses(), null, courseProviders.getCB(),
				courseProviders.getCD());
		provider.setId(id);
		provider.setCB(courseProviders.getCB());
		provider.setCD(courseProviders.getCD());
		return provider;
	}

	/*
	 * Update Course Provider
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CourseProviders courseProviders)
			throws HibernateException, Exception {
		if (courseProviders == null) {
			throw new EmptyFormElementsException();
		}

		try {
			courseProviders.setUD(new Date());

			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseProviders.setUB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(courseProviders);

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

		dataServices.deleteEntity(id, CourseProviders.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.providers.deleted"));

	}
}
