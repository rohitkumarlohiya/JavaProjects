package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.Providers;
import org.springframework.stereotype.Component;

@Component
public class AddProvidersFormValidation {
	public List<Errors> validateForm(Providers providers) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (providers.getProviderType() == null) {
			errorsList.add(new Errors("providerType",
					"Provider type can not be empty !"));
		}
		if (providers.getProviderName() == null) {
			errorsList.add(new Errors("providerName",
					"Provider name can not be empty !"));
		}
		if (providers.getDesignation() == null) {
			errorsList.add(new Errors("designation",
					"Designation can not be empty !"));
		}
		if (providers.getDepartment() == null) {
			errorsList.add(new Errors("designation",
					"Department can not be empty !"));
		}
		return errorsList;
	}
}
