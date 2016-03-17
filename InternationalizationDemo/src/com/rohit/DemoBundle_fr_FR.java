package com.rohit;

import java.util.ListResourceBundle;

public class DemoBundle_fr_FR extends ListResourceBundle {
	
	static final Object[][] contents = {
		{"USER_NAME","nom d'utilisateur"},
		{"PASSWORD","password in france"},
		{"SIGN_IN","signIn in france"}
		
	};

	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
