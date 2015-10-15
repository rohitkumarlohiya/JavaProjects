package org.firstonlineuniversity.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.QuizAttemptDetails;
import org.firstonlineuniversity.models.courses.QuizAttempts;
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
@RequestMapping("/api/users/quiz/attempts")
@PropertySource("classpath:message.properties")
public class QuizAttemptsController {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger
			.getLogger(ContentAdminControllerRest.class);

	/*
	 * Add QuizAttempt
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody QuizAttempts quizAttempts)
			throws HibernateException, Exception {

		if (quizAttempts == null)
			throw new EmptyFormElementsException();

		try {
			quizAttempts.setCD(new Date());
			quizAttempts.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quizAttempts.setUB(customUser.getId());
			quizAttempts.setCB(customUser.getId());
			quizAttempts.setAccountId(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(quizAttempts);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.at.quiz.add"));

	}

	/*
	 * Quiz attempts by quizId
	 */
	@RequestMapping(value = "/{quizId}", method = RequestMethod.GET)
	@ResponseBody
	public List<QuizAttempts> getQuizAttempts(@PathVariable long quizId)
			throws HibernateException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<QuizAttempts> attempts = customServices.quizAttemptList(
				customUser.getId(), quizId);

		return attempts;

	}
	
	/*
	 * Delete QuizAttempt
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, QuizAttempts.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("quiz.attempt.delete"));

	}
}
