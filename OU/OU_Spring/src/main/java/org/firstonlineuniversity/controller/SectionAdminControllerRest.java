package org.firstonlineuniversity.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddSectionFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditSectionFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseSections;
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
@RequestMapping("/api/coursesadmin/courses/sections")
@PropertySource("classpath:message.properties")
public class SectionAdminControllerRest {

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	AddSectionFormValidation addSectionFormValidation;

	@Autowired
	EditSectionFormValidation editSectionFormValidation;

	static final Logger logger = Logger
			.getLogger(SectionAdminControllerRest.class);

	/*
	 * Add Courses Section
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CourseSections courseSections)
			throws HibernateException, Exception {
		if (courseSections == null)
			throw new EntityNotFoundException();

		List<Errors> errorsList = addSectionFormValidation
				.validateForm(courseSections);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {
			courseSections.setCD(new Date());
			courseSections.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseSections.setUB(customUser.getId());
			courseSections.setCB(customUser.getId());
			courseSections.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(courseSections);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.sections.add"));

	}

	/*
	 * Edit Section
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CourseSections edit(@PathVariable long id)
			throws HibernateException, Exception {

		CourseSections sectionDB = (CourseSections) dataServices.getEntity(id,
				CourseSections.class);

		if (sectionDB == null)
			throw new EntityNotFoundException();

		CourseSections section = new CourseSections(sectionDB.getId(),
				sectionDB.getSectionIndex(), sectionDB.getSectionName(),
				sectionDB.getSectionDescription(),
				sectionDB.isSectionVisible(), sectionDB.getSectionPurpose(),
				sectionDB.getActivitySequence(), sectionDB.getSectionKey(),
				sectionDB.getCB(), sectionDB.getCD());
		section.setCourse(new CourseInformation(sectionDB.getCourse()
				.getCourseName(), sectionDB.getCourse().getId()));
		section.setSectionKey(sectionDB.getSectionKey());
		section.setEnabled(sectionDB.isEnabled());

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		customUser.setCb(sectionDB.getCB());
		customUser.setCd(sectionDB.getCD());

		return section;

	}

	/*
	 * Update Sections
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CourseSections courseSections)
			throws HibernateException, Exception {
		if (courseSections == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editSectionFormValidation
				.validateForm(courseSections);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseSections.setUD(new Date());
			courseSections.setUB(customUser.getId());
			courseSections.setCD(customUser.getCd());
			courseSections.setCB(customUser.getCb());

		} catch (Exception e) {
			e.printStackTrace();
		}

		dataServices.updateEntity(courseSections);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("section.update"));
	}

	/*
	 * Delete Sections
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, CourseSections.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("section.delete"));

	}

}
