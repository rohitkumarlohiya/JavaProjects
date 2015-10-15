package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.CourseLectures;
import org.springframework.stereotype.Component;

@Component
public class AddCoursesLecturesFormValidation {
	public List<Errors> validateForm(CourseLectures courseLectures) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (courseLectures.getLectureIndex() == 0) {
			errorsList.add(new Errors("lectureIndex",
					"Lectures Index can not be empty !"));
		}
		if (courseLectures.getLectureName() == null
				|| courseLectures.getLectureName().isEmpty()) {
			errorsList.add(new Errors("lectureName",
					"Lectures Name can not be empty !"));
		}
		if (courseLectures.getLectureDescription() == null
				|| courseLectures.getLectureDescription().isEmpty()) {
			errorsList.add(new Errors("lectureDescription",
					"Lectures Description can not be empty !"));
		}

		if (courseLectures.getCourse().getId() == 0) {
			errorsList
					.add(new Errors("course", "Course Id can not be empty !"));
		}

		return errorsList;
	}
}
