package org.firstonlineuniversity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseReviews;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.models.userquestions.UserQuestions;
import org.firstonlineuniversity.services.CourseReviewsServices;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.utils.Utils;
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
@RequestMapping("/api/courses/ratings")
@PropertySource("classpath:message.properties")
public class CourseReviewController {
	@Autowired
	Environment env;

	@Autowired
	Utils utils;

	@Autowired
	CourseReviewsServices courseReviewsServices;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	static final Logger logger = Logger.getLogger(CourseReviewController.class);

	/*
	 * Add CourseReviews
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Status add(@RequestBody CourseReviews courseReviews)
			throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Accounts accounts = (Accounts) dataServices.getEntity(
				customUser.getId(), Accounts.class);
		String accountname = "";
		if (accounts.getFirstName() != null)
			accountname += accounts.getFirstName();

		if (accounts.getLastName() != null)
			accountname += " " + accounts.getLastName();

		courseReviews.setName(accountname);

		courseReviews.setAccountId(customUser.getId());
		courseReviews.setId(utils.getUuid());

		courseReviewsServices.addCourseReviews(courseReviews);
		return new Status(HttpStatus.OK.toString(),
				env.getProperty("review.add"));
	}

	/*
	 * CourseReviews List
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(required = false) Long courseId)
			throws HibernateException, Exception {
		List<CourseReviews> list = courseReviewsServices.getCourseReviews(null,
				courseId);

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (list == null)
			throw new ListNotFoundException();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentUserId", customUser.getId());
		map.put("list", list);

		return map;
	}

	/*
	 * Edit CourseReviews
	 */
	@RequestMapping(value = "/{courseId}/{reviewId}", method = RequestMethod.GET)
	@ResponseBody
	public CourseReviews notes(@PathVariable Long courseId,
			@PathVariable UUID reviewId) throws HibernateException, Exception {

		CourseReviews courseReviews = courseReviewsServices
				.getCourseReviewsById(courseId, reviewId);
		if (courseReviews == null)
			throw new EntityNotFoundException();
		return courseReviews;
	}

	/*
	 * Update User Question
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status update(@RequestBody CourseReviews courseReviews)
			throws HibernateException, Exception {
		if (courseReviews == null) {
			throw new EmptyFormElementsException();
		}
		courseReviewsServices.updateCourseReviews(courseReviews);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("review.update"));
	}

	/*
	 * Delete User Question
	 */
	@RequestMapping(value = "/{courseId}/{reviewId}", method = RequestMethod.DELETE)
	public @ResponseBody Status delete(@PathVariable Long courseId,
			@PathVariable UUID reviewId) throws HibernateException, Exception {

		courseReviewsServices.deleteCourseReviews(courseId, reviewId);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("review.delete"));
	}
}
