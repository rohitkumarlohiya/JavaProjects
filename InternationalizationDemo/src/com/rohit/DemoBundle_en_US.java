package com.rohit;

import java.util.ListResourceBundle;

public class DemoBundle_en_US extends ListResourceBundle {
	
	static final Object[][] contents = {
		{"USER_NAME","username"},
		{"PASSWORD","password"},
		{"SIGN_IN","signIn"}
		
	};

	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
