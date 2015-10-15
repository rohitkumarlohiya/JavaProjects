package org.firstonlineuniversity.utils;

import org.firstonlineuniversity.models.login.Accounts;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordMatcher implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Accounts accounts = (Accounts) arg0;
		if (!accounts.getPassword().equals(accounts.getConfirmPassword())) {
			arg1.rejectValue("confirmPassword", "password.mismatch",
					"Password does not match");
		}

	}

}
