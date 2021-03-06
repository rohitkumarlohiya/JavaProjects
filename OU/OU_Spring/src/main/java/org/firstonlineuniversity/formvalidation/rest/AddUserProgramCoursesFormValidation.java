package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.UserProgramCources;
import org.springframework.stereotype.Component;

@Component
public class AddUserProgramCoursesFormValidation {
	public List<Errors> validateForm(UserProgramCources userProgramCources) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (userProgramCources.getCourses() == 0) {
			errorsList.add(new Errors("courses.id",
					"Course Id can not be empty !"));
		}
		if (userProgramCources.getUserPrograms() == null) {
			errorsList.add(new Errors("userProgram.id",
					"Program Id can not be empty !"));
		}
		return errorsList;
	}
}
