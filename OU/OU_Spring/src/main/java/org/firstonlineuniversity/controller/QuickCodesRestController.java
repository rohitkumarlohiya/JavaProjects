package org.firstonlineuniversity.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddQuickCodesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditQuickCodesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.QuickCodes;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/users/codes")
@PropertySource("classpath:message.properties")
public class QuickCodesRestController {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	@Autowired
	AddQuickCodesFormValidation addQuickCodesFormValidation;

	@Autowired
	EditQuickCodesFormValidation editQuickCodesFormValidation;

	static final Logger logger = Logger
			.getLogger(QuickCodesRestController.class);

	private static final String FREEZE_VIOLATION = "Read only entry";

	/*
	 * Get List by Type
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<AbstractEntity> getCurseListByFilter(
			@RequestParam String type) throws HibernateException, Exception {

		// AbstractEntityList abstractEntityList = null;
		List<AbstractEntity> abstractEntities = dataServices.getCodesByType(
				QuickCodes.class, type);

		return abstractEntities;

	}

	/*
	 * Add Quick Codes
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody QuickCodes quickCodes)
			throws HibernateException, Exception {

		if (quickCodes == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = addQuickCodesFormValidation
				.validateForm(quickCodes);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {
			quickCodes.setCD(new Date());
			quickCodes.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quickCodes.setUB(customUser.getId());
			quickCodes.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(quickCodes);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.quickcodes.add"));

	}

	/*
	 * Edit QuickCodes
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AbstractEntity edit(@PathVariable long id)
			throws HibernateException, Exception {

		AbstractEntity quickCodes = dataServices
				.getEntity(id, QuickCodes.class);
		if (quickCodes == null)
			throw new EntityNotFoundException();

		return quickCodes;
	}

	/*
	 * Update QuickCodes
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(@RequestBody QuickCodes quickCodes)
			throws HibernateException, Exception {
		if (quickCodes == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editQuickCodesFormValidation
				.validateForm(quickCodes);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}

		QuickCodes quickCodesDb = (QuickCodes) dataServices.getEntity(
				quickCodes.getId(), QuickCodes.class);
		if (quickCodesDb == null || quickCodesDb.isFreeze())
			return new Status("303", FREEZE_VIOLATION);
		try {
			quickCodes.setUD(new Date());
			quickCodes.setCD(quickCodesDb.getCD());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quickCodes.setUB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		dataServices.updateEntity(quickCodes);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("courses.quickcodes.update"));
	}

	/*
	 * Delete QuickCodes
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, QuickCodes.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.quickcodes.delete"));
	}

	/*
	 * Get QuickCodes type
	 */
	@RequestMapping(value = "/types", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> types() throws HibernateException, Exception {
		Set<String> set = customServices.allQuickCodesType();
		return set;
	}
}
