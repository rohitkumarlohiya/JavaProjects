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
import org.firstonlineuniversity.formvalidation.rest.AddQuizChoicesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditQuizChoicesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.ParentNode;
import org.firstonlineuniversity.models.courses.QuizQuestionsOptions;
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
@RequestMapping("/api/coursesadmin/courses/quiz/questions/choices")
@PropertySource("classpath:message.properties")
public class QuizChoicesAdminControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomEditableService customEditableService;

	@Autowired
	AddQuizChoicesFormValidation addQuizChoicesFormValidation;

	@Autowired
	EditQuizChoicesFormValidation editQuizChoicesFormValidation;

	static final Logger logger = Logger
			.getLogger(QuizChoicesAdminControllerRest.class);

	/*
	 * Add Quiz Questions
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody QuizQuestionsOptions quizQuestionsOptions)
			throws HibernateException, Exception {

		if (quizQuestionsOptions == null) {
			throw new EmptyFormElementsException();
		}

		try {
			quizQuestionsOptions.setCD(new Date());
			quizQuestionsOptions.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quizQuestionsOptions.setUB(customUser.getId());
			quizQuestionsOptions.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Errors> errorsList = addQuizChoicesFormValidation
				.validateForm(quizQuestionsOptions);

		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}

		dataServices.addEntity(quizQuestionsOptions);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("quiz.questions.options.add"));

	}

	/*
	 * Edit Quiz Questions
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> edit(@PathVariable long id)
			throws HibernateException, Exception {

		QuizQuestionsOptions questionsOptionsDb = (QuizQuestionsOptions) dataServices
				.getEntityByIdByHQL("QuizQuestionsOptions", id);

		boolean correctAnsswr = questionsOptionsDb.isCorrectAnswer();

		QuizQuestionsOptions questionsOptionsEd = customEditableService
				.getQuizQuestionsOptionsEditableData(id);

		Map<String, Object> map = new HashMap<String, Object>();

		if (questionsOptionsDb == null)
			throw new EntityNotFoundException();

		QuizQuestionsOptions questionsOptions = new QuizQuestionsOptions(
				questionsOptionsDb.getId(),
				questionsOptionsDb.getChoiceNumber(),
				questionsOptionsDb.getChoiceText(),
				questionsOptionsDb.getChoiceHint(),
				questionsOptionsDb.getAnswerText(), correctAnsswr, null, null);

		map.put("quizQuestionsOptions", questionsOptions);
		map.put("quizQuestions", new ParentNode(questionsOptionsEd
				.getQuizQuestions().getId(), questionsOptionsEd
				.getQuizQuestions().getQuestionText()));
		map.put("quizInformation", new ParentNode(questionsOptionsEd
				.getQuizInformation().getId(), questionsOptionsEd
				.getQuizInformation().getQuizName()));
		return map;

	}

	/*
	 * Update Quiz Questions
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody QuizQuestionsOptions quizQuestionsOptions)
			throws HibernateException, Exception {
		if (quizQuestionsOptions == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editQuizChoicesFormValidation
				.validateForm(quizQuestionsOptions);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {

			quizQuestionsOptions.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quizQuestionsOptions.setUB(customUser.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(quizQuestionsOptions);

		return new Status(quizQuestionsOptions.getId(),
				HttpStatus.OK.toString(),
				env.getProperty("quiz.options.successupdate"));
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

		dataServices.deleteEntity(id, QuizQuestionsOptions.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("quiz.options.delete"));

	}
}
