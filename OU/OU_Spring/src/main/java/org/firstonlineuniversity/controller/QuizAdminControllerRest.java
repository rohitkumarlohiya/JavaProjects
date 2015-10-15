package org.firstonlineuniversity.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.domains.custom.CustomQuizInformation;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddCoursesQuizFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditCoursesQuizFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.ParentNode;
import org.firstonlineuniversity.models.courses.QuizAttemptDetails;
import org.firstonlineuniversity.models.courses.QuizAttempts;
import org.firstonlineuniversity.models.courses.QuizHistory;
import org.firstonlineuniversity.models.courses.QuizInformation;
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
@RequestMapping("/api/coursesadmin/courses/quiz")
@PropertySource("classpath:message.properties")
public class QuizAdminControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	AddCoursesQuizFormValidation addQuizFilesFormValidation;

	@Autowired
	EditCoursesQuizFormValidation editQuizFilesFormValidation;

	@Autowired
	CustomEditableService customEditableService;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger
			.getLogger(QuizAdminControllerRest.class);

	/*
	 * Add QuizInformation
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CustomQuizInformation quizID)
			throws HibernateException, Exception {

		if (quizID == null) {
			throw new EmptyFormElementsException();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

		Date stDate = null;
		Date enDate = null;
		try {
			stDate = formatter.parse(quizID.getOpenDate());
			enDate = formatter.parse(quizID.getCloseDate());
		} catch (Exception e) {
			e.printStackTrace();
		}

		QuizInformation quizInformation = new QuizInformation(
				quizID.getQuizName(), quizID.getQuizDescription(),
				quizID.getIntialMessage(), quizID.getCompletionMessage(),
				quizID.getAccessCriteria(), stDate, enDate,
				quizID.getMaxAttempts(), quizID.getMaxTime(),
				quizID.isReviewQuiz(), quizID.isShuffleQuestions(),
				quizID.isShuffleQuestions(), quizID.getGradeMethod(),
				quizID.getPanalityMethod(), quizID.getPassGrade(),
				quizID.getFeedbackType(), quizID.isDisableResult(),
				quizID.isActiveFlag(), quizID.getCourse(), quizID.getSection(),
				quizID.getLecture());

		List<Errors> errorsList = addQuizFilesFormValidation
				.validateForm(quizInformation);

		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {
			quizInformation.setCD(new Date());
			quizInformation.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quizInformation.setUB(customUser.getId());
			quizInformation.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(quizInformation);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.quiz.add"));

	}

	/*
	 * Edit QuizInformation
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> edit(@PathVariable long id)
			throws HibernateException, Exception {

		QuizInformation quizID = (QuizInformation) dataServices.getEntity(id,
				QuizInformation.class);
		if (quizID == null)
			throw new EntityNotFoundException();

		QuizInformation quiz = new QuizInformation(quizID.getId(),
				quizID.getQuizName(), quizID.getQuizDescription(),
				quizID.getIntialMessage(), quizID.getCompletionMessage(),
				quizID.getAccessCriteria(), quizID.getOpenDate(),
				quizID.getCloseDate(), quizID.getMaxAttempts(),
				quizID.getMaxTime(), quizID.isReviewQuiz(),
				quizID.isShuffleQuestions(), quizID.isShuffleQuestions(),
				quizID.getGradeMethod(), quizID.getPanalityMethod(),
				quizID.getPassGrade(), quizID.getFeedbackType(),
				quizID.isDisableResult(), quizID.isActiveFlag(), null, null,
				null);
		quiz.setCB(quizID.getCB());
		quiz.setCD(quizID.getCD());
		quiz.setEnabled(quizID.isEnabled());
		quiz.setMaxAttempts(quizID.getMaxAttempts());
		quiz.setPassingCriteria(quizID.getPassingCriteria());
		Map<String, Object> map = new HashMap<String, Object>();
		QuizInformation quizDB = null;
		try {
			quizDB = customEditableService.getQuizEditableData(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (quizDB != null) {
			map.put("courseInformation", new ParentNode(quizDB.getCourse()
					.getId(), quizDB.getCourse().getCourseName()));
			map.put("courseLectures", new ParentNode(quizDB.getLecture()
					.getId(), quizDB.getLecture().getLectureName()));
			map.put("courseSections", new ParentNode(quizDB.getSection()
					.getId(), quizDB.getSection().getSectionName()));
		}

		map.put("quizInformation", quiz);
		return map;

	}

	/*
	 * Update QuizInformation
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CustomQuizInformation quizID)
			throws HibernateException, Exception {
		if (quizID == null) {
			throw new EmptyFormElementsException();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String dateInString = "7-Jun-2013";

		Date stDate = null;
		Date enDate = null;
		try {
			stDate = formatter.parse(dateInString);
			enDate = formatter.parse(dateInString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		QuizInformation quizInformation = new QuizInformation(quizID.getId(),
				quizID.getQuizName(), quizID.getQuizDescription(),
				quizID.getIntialMessage(), quizID.getCompletionMessage(),
				quizID.getAccessCriteria(), stDate, enDate,
				quizID.getMaxAttempts(), quizID.getMaxTime(),
				quizID.isReviewQuiz(), quizID.isShuffleQuestions(),
				quizID.isShuffleQuestions(), quizID.getGradeMethod(),
				quizID.getPanalityMethod(), quizID.getPassGrade(),
				quizID.getFeedbackType(), quizID.isDisableResult(),
				quizID.isActiveFlag(), quizID.getCourse(), quizID.getSection(),
				quizID.getLecture());

		List<Errors> errorsList = editQuizFilesFormValidation
				.validateForm(quizInformation);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			quizInformation.setUB(customUser.getId());
			quizInformation.setUD(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(quizInformation);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("quiz.successupdate"));
	}

	/*
	 * Delete QuizInformation
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		customServices.deleteQuiz(id);
		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.quiz.delete"));

	}

	/*
	 * Delete QuizInformation
	 */
	@RequestMapping(value = "/review/{quizId}/{accountId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<Object, Object> review(@PathVariable long quizId,
			@PathVariable Long accountId) throws HibernateException, Exception {

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
			public void setAttemptDetails(List<QuizAttemptDetails> attemptDetails) {
				this.attemptDetails = attemptDetails;
			}
		}
		if (quizId == 0)
			throw new EntityNotFoundException();

		Map<Object, Object> map = new LinkedHashMap<Object, Object>();
		List<QuizHistory> histories = customServices
				.quizHistoryByAccountAndQuizId(quizId, accountId);
		if (histories != null)
			map.put("hostory", histories);
		else
			map.put("hostory", "");

		List<QuizAttempts> attempts = customServices.quizAttemptByQuizId(
				quizId, accountId);

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
