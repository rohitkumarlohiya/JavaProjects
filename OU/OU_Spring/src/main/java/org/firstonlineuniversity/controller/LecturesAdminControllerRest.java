package org.firstonlineuniversity.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddCoursesLecturesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditCourseLectureFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.ParentNode;
import org.firstonlineuniversity.models.courses.CourseLectures;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CustomEditableService;
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
@RequestMapping("/api/coursesadmin/courses/lectures")
@PropertySource("classpath:message.properties")
public class LecturesAdminControllerRest {

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomEditableService customEditableService;

	@Autowired
	AddCoursesLecturesFormValidation addCoursesLecturesFormValidation;

	@Autowired
	EditCourseLectureFormValidation editCoursesLecturesFormValidation;

	static final Logger logger = Logger
			.getLogger(LecturesAdminControllerRest.class);

	/*
	 * Add Lectures
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CourseLectures courseLectures)
			throws HibernateException, Exception {
		if (courseLectures == null)
			throw new EntityNotFoundException();

		List<Errors> errorsList = addCoursesLecturesFormValidation
				.validateForm(courseLectures);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {
			courseLectures.setCD(new Date());
			courseLectures.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseLectures.setUB(customUser.getId());
			courseLectures.setCB(customUser.getId());
			courseLectures.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(courseLectures);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.lectures.add"));

	}

	/*
	 * Edit Lecture
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> edit(@PathVariable long id)
			throws HibernateException, Exception {

		CourseLectures lecturesDB = customEditableService
				.getCourseLecturesEditableData(id);

		if (lecturesDB == null)
			throw new EntityNotFoundException();

		CourseLectures lectures = new CourseLectures(lecturesDB.getId(),
				lecturesDB.getLectureIndex(), lecturesDB.getLectureName(),
				lecturesDB.getLectureDescription(),
				lecturesDB.isLectureVisible(), null, null,
				lecturesDB.getLectureKey());
		lectures.setCB(lecturesDB.getCB());
		lectures.setCD(lecturesDB.getCD());
		lectures.setEnabled(lecturesDB.isEnabled());
		
		Map<String, Object> filesMap = new HashMap<String, Object>();
		filesMap.put("courseInformation", new ParentNode(lecturesDB.getCourse()
				.getId(), lecturesDB.getCourse().getCourseName()));
		filesMap.put("courseSections", new ParentNode(lecturesDB.getSection()
				.getId(), lecturesDB.getSection().getSectionName()));
		filesMap.put("lectures", lectures);

		return filesMap;

	}

	/*
	 * Update Lectures
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CourseLectures courseLectures)
			throws HibernateException, Exception {
		if (courseLectures == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editCoursesLecturesFormValidation
				.validateForm(courseLectures);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {
			courseLectures.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseLectures.setUB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(courseLectures);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("lecture.update"));
	}

	/*
	 * Delete Lectures
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, CourseLectures.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("lecture.delete"));

	}

}
