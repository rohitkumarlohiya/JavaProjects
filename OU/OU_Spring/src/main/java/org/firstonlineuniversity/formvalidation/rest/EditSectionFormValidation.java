package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.CourseSections;
import org.springframework.stereotype.Component;

@Component
public class EditSectionFormValidation {
	public List<Errors> validateForm(CourseSections courseSections) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (courseSections.getId() == 0) {
			errorsList.add(new Errors("id", "Id can not be empty !"));
		}
		if (courseSections.getSectionIndex() == 0) {
			errorsList.add(new Errors("sectionIndex",
					"Section Index can not be empty !"));
		}
		if (courseSections.getSectionName() == null
				|| courseSections.getSectionName().isEmpty()) {
			errorsList.add(new Errors("sectionName",
					"Section Name can not be empty !"));
		}
		if (courseSections.getSectionDescription() == null
				|| courseSections.getSectionDescription().isEmpty()) {
			errorsList.add(new Errors("sectionDescription",
					"Section Description can not be empty !"));
		}
		return errorsList;
	}
}
