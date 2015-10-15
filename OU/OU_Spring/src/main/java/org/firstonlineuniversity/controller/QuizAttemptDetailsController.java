package org.firstonlineuniversity.controller;

import java.util.Date;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.QuizAttemptDetails;
import org.firstonlineuniversity.models.courses.QuizAttempts;
import org.firstonlineuniversity.models.courses.QuizHistory;
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
@RequestMapping("/api/users/quiz-attempt-details")
@PropertySource("classpath:message.properties")
public class QuizAttemptDetailsController {

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger
			.getLogger(QuizAttemptDetailsController.class);

	/*
	 * Add QuizAttemptDetails
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody QuizAttemptDetails quizAttemptDetails)
			throws HibernateException, Exception {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		try {
			quizAttemptDetails.setCD(new Date());
			quizAttemptDetails.setUD(new Date());
			quizAttemptDetails.setUB(customUser.getId());
			quizAttemptDetails.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		dataServices.addEntity(quizAttemptDetails);

		return new Status(quizAttemptDetails.getId(), "200",
				"quiz.attempt.details.create");
	}

	/*
	 * Edit QuizAttemptDetails
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public QuizAttemptDetails payment(@PathVariable long id)
			throws HibernateException, Exception {

		logger.info("ID: " + id);

		QuizAttemptDetails quizAttemptDetailsDb = (QuizAttemptDetails) dataServices
				.getEntity(id, QuizAttemptDetails.class);

		if (quizAttemptDetailsDb == null)
			throw new EntityNotFoundException();
		return quizAttemptDetailsDb;
	}

	/*
	 * Update QuizAttemptDetails
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status edit(@RequestBody QuizAttemptDetails quizAttemptDetails)
			throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		try {
			quizAttemptDetails.setUB(customUser.getId());
			quizAttemptDetails.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		dataServices.updateEntity(quizAttemptDetails);

		return new Status(quizAttemptDetails.getId(), "200",
				"quiz.attempt.details.update");
	}

	/*
	 * Delete QuizAttemptDetails
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, QuizAttemptDetails.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("quiz.attempt.details.delete"));

	}

	/*
	 * Delete QuizInformation
	 */
	@RequestMapping(value = "/review/{quizId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<Object, Object> review(@PathVariable long quizId)
			throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		class Mapping {
			private Object object;
			private List<QuizAttemptDetails> attemptDetails;

			public Object getObject() {
				return object;
			}

			public void setObject(Object object) {
				this.object = object;
			}

			public List<QuizAttemptDetails> getAttemptDetails() {
				return attemptDetails;
			}

			public void setAttemptDetails(
					List<QuizAttemptDetails> attemptDetails) {
				this.attemptDetails = attemptDetails;
			}
		}
		if (quizId == 0)
			throw new EntityNotFoundException();

		Map<Object, Object> map = new LinkedHashMap<Object, Object>();
		List<QuizHistory> histories = customServices
				.quizHistoryByAccountAndQuizId(quizId, customUser.getId());
		if (histories != null)
			map.put("hostory", histories);
		else
			map.put("hostory", "");

		List<QuizAttempts> attempts = customServices.quizAttemptByQuizId(
				quizId, customUser.getId());

		if (attempts != null) {
			for (QuizAttempts attempt : attempts) {
				Mapping mapping = new Mapping();
				mapping.setObject(attempt);
				List<QuizAttemptDetails> attemptDetails = customServices
						.quizAttemptDetailsByQuizId(attempt.getId());
				if (attemptDetails != null) {
					mapping.setAttemptDetails(attemptDetails);
				}
				map.put("attempts", mapping);
			}
		} else {
			map.put("attempts", "");
		}
		return map;

	}

}
