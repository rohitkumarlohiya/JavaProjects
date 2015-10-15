package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.QuizQuestionsOptions;
import org.springframework.stereotype.Component;

@Component
public class EditQuizChoicesFormValidation {
	public List<Errors> validateForm(QuizQuestionsOptions quizQuestionsOptions) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (quizQuestionsOptions.getId() == 0) {
			errorsList.add(new Errors("id", "Id can not be empty !"));
		}

		if (quizQuestionsOptions.getChoiceNumber() == 0) {
			errorsList.add(new Errors("choiceNumber",
					"Choice Number can not be empty !"));
		}

		if (quizQuestionsOptions.getAnswerText() == null
				|| quizQuestionsOptions.getAnswerText().isEmpty()) {
			errorsList.add(new Errors("answerText",
					"Answer Text can not be empty !"));
		}

		if (quizQuestionsOptions.getChoiceHint() == null
				|| quizQuestionsOptions.getChoiceHint().isEmpty()) {
			errorsList.add(new Errors("choiceHint",
					"Choice hint can not be empty !"));
		}

		if (quizQuestionsOptions.getChoiceText() == null
				|| quizQuestionsOptions.getChoiceText().isEmpty()) {
			errorsList.add(new Errors("choiceText",
					"Choice text can not be empty !"));
		}

		return errorsList;
	}

}
