package org.firstonlineuniversity.controller;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.NotAuthorisedException;
import org.firstonlineuniversity.models.courses.CourseResources;
import org.firstonlineuniversity.service.login.SecurityCheck;
import org.firstonlineuniversity.services.CustomServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/users/courses/resources")
@PropertySource("classpath:message.properties")
public class ResoursesRestController {

	static final Logger logger = Logger
			.getLogger(ResoursesRestController.class);

	@Autowired
	Environment env;

	@Autowired
	CustomServices customServices;

	/*
	 * Get Resources
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CourseResources edit(@PathVariable long id)
			throws HibernateException, Exception {

		CourseResources resourcesDB = customServices.getCourseResourses(id);

		if (resourcesDB == null)
			throw new EntityNotFoundException();

		if (resourcesDB.getCourse() != null)
			logger.info("Is paid : " + resourcesDB.getCourse().isPaidCourse());

		/* check access to course start */

		if (resourcesDB.getCourse().isPaidCourse() && !resourcesDB.isPromotional())
			if (!SecurityCheck.hasRole("ROLE_SUBSCRIBER")
					&& !SecurityCheck.enrolled(resourcesDB.getCourse().getId())) {
				throw new NotAuthorisedException(
						"You are not authorised to view this content !");
			}
		/* check access to course end */
		CourseResources resources = new CourseResources(resourcesDB.getId(),
				resourcesDB.getFileName(), resourcesDB.getFileDisplayName(),
				resourcesDB.getFileType(), resourcesDB.getFileDescription(),
				resourcesDB.getFileSize(), resourcesDB.getFileFormat(),
				resourcesDB.getFilePath(), resourcesDB.getFileIndex(), null,
				null, resourcesDB.getResourceKey());
		return resources;

	}

}
