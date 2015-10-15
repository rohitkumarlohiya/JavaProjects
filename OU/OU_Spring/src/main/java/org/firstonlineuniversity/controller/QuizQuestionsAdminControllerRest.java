package org.firstonlineuniversity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddCoursesQuestionsFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditCoursesQuestionsFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.ParentNode;
import org.firstonlineuniversity.models.courses.QuizQuestions;
import org.firstonlineuniversity.models.courses.QuizQuestionsOptions;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/coursesadmin/courses/quiz/questions")
@PropertySource("classpath:message.properties")
public class QuizQuestionsAdminControllerRest {

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomEditableService customEditableService;

	@Autowired
	CustomServices customServices;

	@Autowired
	AddCoursesQuestionsFormValidation addCoursesQuestionsFormValidation;

	@Autowired
	EditCoursesQuestionsFormValidation editCoursesQuestionsFormValidation;

	static final Logger logger = Logger
			.getLogger(QuizQuestionsAdminControllerRest.class);

	/*
	 * Add Quiz Questions
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody QuizQuestions quizQuestions)
			throws HibernateException, Exception {

		if (quizQuestions == null) {
			throw new EmptyFormElementsException();
		}

		try {
			quizQuestions.setCD(new Date());
			quizQuestions.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quizQuestions.setUB(customUser.getId());
			quizQuestions.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Errors> errorsList = addCoursesQuestionsFormValidation
				.validateForm(quizQuestions);

		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}

		dataServices.addEntity(quizQuestions);

		return new Status(quizQuestions.getId(),
				HttpStatus.ACCEPTED.toString(),
				env.getProperty("quiz.questions.add"));

	}

	/*
	 * Edit Quiz Questions
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> edit(@PathVariable long id)
			throws HibernateException, Exception {

		QuizQuestions quizQuestionsDB = customEditableService
				.getQuizQuestionsEditableData(id);

		if (quizQuestionsDB == null)
			throw new EntityNotFoundException();

		List<QuizQuestionsOptions> quizQuestionsOptionsListDB = customServices
				.optionsListByQuestionId(quizQuestionsDB.getId());
		List<QuizQuestionsOptions> quizQuestionsOptionsList = new ArrayList<QuizQuestionsOptions>();

		Map<String, Object> map = new HashMap<String, Object>();

		QuizQuestions quizQuestions = new QuizQuestions(
				quizQuestionsDB.getId(), quizQuestionsDB.getQuestionNumber(),
				quizQuestionsDB.getQuestionText(),
				quizQuestionsDB.getQuestionHint(),
				quizQuestionsDB.getCorrectAnswer(),
				quizQuestionsDB.getQuestionType(), quizQuestionsDB.getGrade(),
				quizQuestionsDB.getPanalty(), quizQuestionsDB.getImageLink(),
				quizQuestionsDB.isEnabled(), null, null);

		if (quizQuestionsOptionsListDB != null) {
			for (QuizQuestionsOptions choiceHintDb : quizQuestionsOptionsListDB) {
				QuizQuestionsOptions choiceHint = new QuizQuestionsOptions(
						choiceHintDb.getId(), choiceHintDb.getChoiceNumber(),
						choiceHintDb.getChoiceText(),
						choiceHintDb.getChoiceHint(),
						choiceHintDb.getAnswerText(),
						choiceHintDb.isCorrectAnswer(), null, null);
				quizQuestionsOptionsList.add(choiceHint);
			}
		}

		map.put("quizQuestions", quizQuestions);
		map.put("quizInformation", new ParentNode(quizQuestionsDB
				.getQuizInformation().getId(), quizQuestionsDB
				.getQuizInformation().getQuizName()));
		map.put("quizOptions", quizQuestionsOptionsList);

		return map;
	}

	/*
	 * Update Quiz Questions
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody QuizQuestions quizQuestions)
			throws HibernateException, Exception {
		if (quizQuestions == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editCoursesQuestionsFormValidation
				.validateForm(quizQuestions);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {

			quizQuestions.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quizQuestions.setUB(customUser.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(quizQuestions);

		return new Status(quizQuestions.getId(), HttpStatus.OK.toString(),
				env.getProperty("quiz.successupdate"));
	}

	/*
	 * Delete Quiz Questions
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, QuizQuestions.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("quiz.delete"));

	}

}
