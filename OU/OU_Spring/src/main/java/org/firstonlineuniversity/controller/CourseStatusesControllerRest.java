package org.firstonlineuniversity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseStatuses;
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
@RequestMapping("/api/coursesadmin/courses/statuses")
@PropertySource("classpath:message.properties")
public class CourseStatusesControllerRest {

	public static final String ROOT_URI = "/api/categories";

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger.getLogger(CoursesControllerRest.class);

	/*
	 * Add Course Statuses
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CourseStatuses courseStatuses)
			throws HibernateException, Exception {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		try {
			courseStatuses.setAccountId(customUser.getId());
			courseStatuses.setCB(courseStatuses.getId());
			courseStatuses.setCD(new Date());
			courseStatuses.setSubmissionDate(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(courseStatuses);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.statuses.add"));

	}

	/*
	 * Get Course Statuses
	 */
	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	@ResponseBody
	public List<AbstractEntity> get(@PathVariable Long courseId)
			throws HibernateException, Exception {
		List<AbstractEntity> courseStatusesListDb = customServices
				.courseStatuses(courseId);
		List<AbstractEntity> courseStatuses = new ArrayList<AbstractEntity>();

		for (AbstractEntity ae : courseStatusesListDb) {
			CourseStatuses courseStatusesDb = (CourseStatuses) ae;

			CourseStatuses courseStatus = new CourseStatuses(null,
					courseStatusesDb.getAccountId(),
					courseStatusesDb.getStatusIndex(),
					courseStatusesDb.getCourseStatus(),
					courseStatusesDb.getSubmissionDate(),
					courseStatusesDb.getSubmissionComment(),
					courseStatusesDb.getApprovalComment(),
					courseStatusesDb.getApproverId());
			courseStatuses.add(courseStatus);
		}
		return courseStatuses;
	}
}
