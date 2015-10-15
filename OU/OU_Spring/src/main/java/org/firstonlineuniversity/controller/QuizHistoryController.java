package org.firstonlineuniversity.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.QuizHistory;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CustomEditableService;
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
@RequestMapping("/api/users/quiz/history")
@PropertySource("classpath:message.properties")
public class QuizHistoryController {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomEditableService customEditableService;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger.getLogger(QuizHistoryController.class);

	/*
	 * Add Quiz History
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody QuizHistory quizHistory)
			throws HibernateException, Exception {

		if (quizHistory == null) {
			throw new EmptyFormElementsException();
		}

		try {
			quizHistory.setCD(new Date());
			quizHistory.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quizHistory.setUB(customUser.getId());
			quizHistory.setCB(customUser.getId());
			quizHistory.setAccountId(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(quizHistory);

		return new Status(quizHistory.getId(), HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.history.quiz.add"));

	}

	/*
	 * Edit Quiz History
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public QuizHistory edit(@PathVariable long id) throws HibernateException,
			Exception {
		QuizHistory quizHistory = null;
		try {
			quizHistory = (QuizHistory) dataServices.getEntity(id,
					QuizHistory.class);
		} catch (Exception e) {

		}
		if (quizHistory == null)
			throw new EntityNotFoundException();

		return quizHistory;

	}

	/*
	 * Quiz History on CourseID or QuizID or Both
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<QuizHistory> getFullQuiz(
			@RequestParam(required = false) Long courseId,
			@RequestParam(required = false) Long quizId)
			throws HibernateException, Exception {
		List<QuizHistory> quizHistories = null;

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		try {
			quizHistories = customServices.quizHistoryList(customUser.getId(),
					courseId, quizId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (quizHistories == null)
			throw new ListNotFoundException();
		return quizHistories;
	}

	/*
	 * Update Quiz History
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody QuizHistory quizHistory) throws HibernateException,
			Exception {
		if (quizHistory == null) {
			throw new EmptyFormElementsException();
		}

		try {
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quizHistory.setUB(customUser.getId());
			quizHistory.setUD(new Date());
			quizHistory.setAccountId(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(quizHistory);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("quiz.history.successupdate"));
	}
}
