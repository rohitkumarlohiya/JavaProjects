package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.QuizInformation;
import org.springframework.stereotype.Component;

@Component
public class AddCoursesQuizFormValidation {
	public List<Errors> validateForm(QuizInformation quizInformation) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (quizInformation.getCourse()==null || quizInformation.getCourse().getId() == 0) {
			errorsList
					.add(new Errors("course", "Course id can not be empty !"));
		}

		return errorsList;
	}
}
