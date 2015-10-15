package org.firstonlineuniversity.models.helper;

import org.firstonlineuniversity.models.courses.UserProgramCources;
import org.firstonlineuniversity.models.views.CourseCatalogView;

public class UserProgramCoursesWrapper {

	private UserProgramCources userProgramCources;
	private CourseCatalogView courseCatalogView;

	public UserProgramCources getUserProgramCources() {
		return userProgramCources;
	}

	public void setUserProgramCources(UserProgramCources userProgramCources) {
		this.userProgramCources = userProgramCources;
	}

	public CourseCatalogView getCourseCatalogView() {
		return courseCatalogView;
	}

	public void setCourseCatalogView(CourseCatalogView courseCatalogView) {
		this.courseCatalogView = courseCatalogView;
	}

}
