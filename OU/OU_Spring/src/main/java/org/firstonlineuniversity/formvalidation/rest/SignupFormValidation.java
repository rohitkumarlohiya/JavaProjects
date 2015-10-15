package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.firstonlineuniversity.models.login.Accounts;
import org.springframework.stereotype.Component;

@Component
public class SignupFormValidation {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "[a-zA-Z]+";
	String MOBILE_PATTERN = "[0-9]{10}";

	public List<Errors> validateForm(Accounts accounts) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (accounts.getFirstName() == null
				|| accounts.getFirstName().isEmpty()) {
			errorsList.add(new Errors("firstName",
					"First Name can not be empty !"));
		}

		if (accounts.getLastName() == null || accounts.getLastName().isEmpty()) {
			errorsList.add(new Errors("lastName",
					"Last Name can not be empty !"));
		}

		if (accounts.getPassword() == null || accounts.getPassword().isEmpty()) {
			errorsList
					.add(new Errors("password", "Password can not be empty !"));
		} else if (accounts.getConfirmPassword() == null
				|| accounts.getConfirmPassword().isEmpty()) {
			errorsList.add(new Errors("confirmPassword",
					"Confirm Password can not be empty !"));

		} else if (!accounts.getConfirmPassword()
				.equals(accounts.getPassword())) {
			errorsList.add(new Errors("confirmPassword",
					"Password and Confirm Password does not match !"));
		}

		if (accounts.getAccountEmail() == null
				|| accounts.getAccountEmail().isEmpty()) {
			errorsList.add(new Errors("email", "Email can not be empty !"));

		} else {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(accounts.getAccountEmail());
			if (!matcher.matches()) {
				errorsList.add(new Errors("email",
						"Please enter a valid email address !"));
			}
		}

		return errorsList;
	}
}
