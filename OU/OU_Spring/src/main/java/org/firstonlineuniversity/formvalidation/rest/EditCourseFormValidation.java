package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.CourseInformation;
import org.springframework.stereotype.Component;

@Component
public class EditCourseFormValidation {
	public List<Errors> validateForm(CourseInformation courseInformation) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (courseInformation.getId() == 0) {
			errorsList.add(new Errors("id", "Id can not be empty !"));
		}
		if (courseInformation.getCourseCategory() == null
				|| courseInformation.getCourseCategory().getId() == 0) {
			errorsList
					.add(new Errors("courseCategory", "Id can not be empty !"));
		}
		return errorsList;
	}
}
