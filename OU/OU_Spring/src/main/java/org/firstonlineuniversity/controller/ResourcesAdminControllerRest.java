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
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.formvalidation.rest.AddCoursesResoursesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditCourseResourcesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.ParentNode;
import org.firstonlineuniversity.models.courses.CourseResources;
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
@RequestMapping("/api/coursesadmin/courses/resources")
@PropertySource("classpath:message.properties")
public class ResourcesAdminControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	AddCoursesResoursesFormValidation addCoursesResoursesFormValidation;

	@Autowired
	EditCourseResourcesFormValidation editCourseResourcesFormValidation;

	@Autowired
	CustomEditableService customEditableService;

	static final Logger logger = Logger
			.getLogger(ResourcesAdminControllerRest.class);

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CourseResources courseResources)
			throws Exception {
		if (courseResources == null)
			throw new EntityNotFoundException();

		List<Errors> errorsList = addCoursesResoursesFormValidation
				.validateForm(courseResources);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);
		}
		try {
			courseResources.setCD(new Date());
			courseResources.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseResources.setUB(customUser.getId());
			courseResources.setCB(customUser.getId());
			courseResources.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(courseResources);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.resourses.add"));

	}

	/*
	 * Edit Resources
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> edit(@PathVariable long id)
			throws HibernateException, Exception {

		CourseResources resourcesDB = customEditableService
				.getCourseResourcesEditableDate(id);
		if (resourcesDB == null)
			throw new EntityNotFoundException();

		CourseResources resources = new CourseResources(resourcesDB.getId(),
				resourcesDB.getFileName(), resourcesDB.getFileDisplayName(),
				resourcesDB.getFileType(), resourcesDB.getFileDescription(),
				resourcesDB.getFileSize(), resourcesDB.getFileFormat(),
				resourcesDB.getFilePath(), resourcesDB.getFileIndex(), null,
				null, resourcesDB.getResourceKey());
		resources.setCB(resourcesDB.getCB());
		resources.setCD(resourcesDB.getCD());
		
		resources.setDownload(resourcesDB.isDownload());

		resources.setEnabled(resourcesDB.isEnabled());

		Map<String, Object> filesMap = new HashMap<String, Object>();
		filesMap.put("courseInformation", new ParentNode(resourcesDB
				.getCourse().getId(), resourcesDB.getCourse().getCourseName()));
		filesMap.put("courseLectures", new ParentNode(resourcesDB.getLecture()
				.getId(), resourcesDB.getLecture().getLectureName()));
		filesMap.put("courseResources", resources);

		return filesMap;

	}

	/*
	 * Update Resourses
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CourseResources courseResources) throws Exception {
		if (courseResources == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editCourseResourcesFormValidation
				.validateForm(courseResources);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);
		}
		try {

			courseResources.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseResources.setUB(customUser.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(courseResources);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("course.update"));
	}

	/*
	 * Delete Resourses
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) {
		if (id == 0)
			throw new EntityNotFoundException();

		try {
			dataServices.deleteEntity(id, CourseResources.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("resourses.delete"));

	}

}
