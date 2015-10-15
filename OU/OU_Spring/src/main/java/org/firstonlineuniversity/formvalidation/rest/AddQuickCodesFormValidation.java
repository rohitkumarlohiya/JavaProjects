package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.QuickCodes;
import org.springframework.stereotype.Component;

@Component
public class AddQuickCodesFormValidation {
	public List<Errors> validateForm(QuickCodes quickCodes) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (quickCodes.getType() == null || quickCodes.getType().isEmpty()) {
			errorsList.add(new Errors("type", "Type can not be empty !"));
		}
		if (quickCodes.getCode() == null || quickCodes.getCode().isEmpty()) {
			errorsList.add(new Errors("code", "Code can not be empty !"));
		}
		if (quickCodes.getMeaning() == null
				|| quickCodes.getMeaning().isEmpty()) {
			errorsList.add(new Errors("meaning", "Meaning can not be empty !"));
		}
		if (quickCodes.getDescription() == null
				|| quickCodes.getDescription().isEmpty()) {
			errorsList.add(new Errors("description",
					"Description can not be empty !"));
		}

		return errorsList;
	}
}
