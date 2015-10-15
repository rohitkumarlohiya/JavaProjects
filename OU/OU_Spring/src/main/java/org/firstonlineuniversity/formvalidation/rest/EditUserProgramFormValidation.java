package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.UserPrograms;
import org.springframework.stereotype.Component;

@Component
public class EditUserProgramFormValidation {
	public List<Errors> validateForm(UserPrograms userPrograms) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (userPrograms.getId() == 0) {
			errorsList.add(new Errors("id", "Id can not be empty !"));
		}
		if (userPrograms.getProgramName() == null
				|| userPrograms.getProgramName().isEmpty()) {
			errorsList.add(new Errors("programName",
					"Program name can not be empty !"));
		}
		if (userPrograms.getProgranDescription() == null
				|| userPrograms.getProgranDescription().isEmpty()) {
			errorsList.add(new Errors("programDescription",
					"Program description can not be empty !"));
		}
		return errorsList;
	}
}
