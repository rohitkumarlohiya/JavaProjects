package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.profiles.Profiles;
import org.springframework.stereotype.Component;

@Component
public class EditProfileFormValidation {

	public List<Errors> validateForm(Profiles profiles) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (profiles.getFirstName() == null
				|| profiles.getFirstName().isEmpty()) {
			errorsList.add(new Errors("firstName",
					"First Name can not be empty !"));
		}

		if (profiles.getLastName() == null || profiles.getLastName().isEmpty()) {
			errorsList.add(new Errors("lastName",
					"Last Name can not be empty !"));
		}
		
		return errorsList;
	}
}
