package org.firstonlineuniversity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddCoursesQuizFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditCoursesQuizFormValidation;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.ParentNode;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseLectures;
import org.firstonlineuniversity.models.courses.CourseSections;
import org.firstonlineuniversity.models.courses.QuizInformation;
import org.firstonlineuniversity.models.courses.QuizQuestions;
import org.firstonlineuniversity.models.courses.QuizQuestionsOptions;
import org.firstonlineuniversity.services.CustomEditableService;
import org.firstonlineuniversity.services.CustomServices;
import org.firstonlineuniversity.services.DataServices;
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
@RequestMapping("/api/users/courses/quiz")
@PropertySource("classpath:message.properties")
public class QuizUserControllerRest {
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

	static final Logger logger = Logger.getLogger(QuizUserControllerRest.class);

	/*
	 * Quiz with all questions and options
	 */
	@RequestMapping(value = "/quiz-all/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getFullQuiz(@PathVariable long id)
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
		quiz.setUB(quizID.getUB());
		quiz.setMaxAttempts(quizID.getMaxAttempts());
		quiz.setPassingCriteria(quizID.getPassingCriteria());

		List<QuizQuestions> quizQuestionsDBList = customServices
				.questionsListByQuizId(quiz.getId());

		List<QuizQuestions> quizQuestionsList = new ArrayList<QuizQuestions>();

		for (QuizQuestions quizQuestionsDB : quizQuestionsDBList) {

			List<QuizQuestionsOptions> quizQuestionsOptionsListDB = customServices
					.optionsListByQuestionId(quizQuestionsDB.getId());
			Set<QuizQuestionsOptions> quizQuestionsOptionsList = new HashSet<QuizQuestionsOptions>();

			QuizQuestions quizQuestions = new QuizQuestions(
					quizQuestionsDB.getId(),
					quizQuestionsDB.getQuestionNumber(),
					quizQuestionsDB.getQuestionText(),
					quizQuestionsDB.getQuestionHint(),
					quizQuestionsDB.getCorrectAnswer(),
					quizQuestionsDB.getQuestionType(),
					quizQuestionsDB.getGrade(), quizQuestionsDB.getPanalty(),
					quizQuestionsDB.getImageLink(),
					quizQuestionsDB.isEnabled(), null, null);
			quizQuestions.setCB(quizQuestionsDB.getCB());
			quizQuestions.setCD(quizQuestionsDB.getCD());

			if (quizQuestionsOptionsListDB != null) {
				for (QuizQuestionsOptions choiceHintDb : quizQuestionsOptionsListDB) {
					QuizQuestionsOptions choiceHint = new QuizQuestionsOptions(
							choiceHintDb.getId(),
							choiceHintDb.getChoiceNumber(),
							choiceHintDb.getChoiceText(),
							choiceHintDb.getChoiceHint(),
							choiceHintDb.getAnswerText(),
							choiceHintDb.isCorrectAnswer(), null, null);
					choiceHint.setCB(choiceHintDb.getCB());
					choiceHint.setCD(choiceHintDb.getCD());
					quizQuestionsOptionsList.add(choiceHint);
				}
				quizQuestions.setQuizQuestionsOptions(quizQuestionsOptionsList);
			}
			quizQuestionsList.add(quizQuestions);
		}
		CourseInformation courseInformation = quizID.getCourse();
		CourseSections courseSections = quizID.getSection();
		CourseLectures courseLectures = quizID.getLecture();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quizQuestionsList", quizQuestionsList);
		map.put("quizInformation", quiz);

		map.put("course", new ParentNode(courseInformation.getId(),
				courseInformation.getCourseName()));
		map.put("section", new ParentNode(courseSections.getId(),
				courseSections.getSectionName()));
		map.put("lecture", new ParentNode(courseLectures.getId(),
				courseLectures.getLectureName()));
		return map;
	}
}
