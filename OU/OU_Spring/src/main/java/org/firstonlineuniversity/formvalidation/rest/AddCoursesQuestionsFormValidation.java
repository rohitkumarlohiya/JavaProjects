package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.QuizQuestions;
import org.springframework.stereotype.Component;

@Component
public class AddCoursesQuestionsFormValidation {
	public List<Errors> validateForm(QuizQuestions quizQuestions) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (quizQuestions.getQuestionNumber() == 0) {
			errorsList.add(new Errors("questionNumber",
					"Question Number can not be empty !"));
		}

		if (quizQuestions.getQuestionType() == 0) {
			errorsList.add(new Errors("questionType",
					"Question Type can not be empty !"));
		}

		if (quizQuestions.getQuestionText() == null
				|| quizQuestions.getQuestionText().isEmpty()) {
			errorsList.add(new Errors("questionText",
					"Question Text can not be empty !"));
		}
		return errorsList;
	}
}
