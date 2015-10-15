package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.CourseProviders;

public class AddCourseProvidersFormValidation {
	public List<Errors> validateForm(CourseProviders courseProviders) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (courseProviders.getProviders() == null) {
			errorsList.add(new Errors("id", "Providers id can not be empty !"));
		}
		return errorsList;
	}
}
