package org.firstonlineuniversity.models.helper;

import java.util.List;

import org.firstonlineuniversity.models.courses.UserPrograms;

public class UserProgramWrapper {
	private List<UserProgramCoursesWrapper> userProgramCoursesWrapper;
	private UserPrograms userPrograms;

	public List<UserProgramCoursesWrapper> getUserProgramCoursesWrapper() {
		return userProgramCoursesWrapper;
	}

	public void setUserProgramCoursesWrapper(
			List<UserProgramCoursesWrapper> userProgramCoursesWrapper) {
		this.userProgramCoursesWrapper = userProgramCoursesWrapper;
	}

	public UserProgramWrapper(UserPrograms userPrograms) {
		super();
		this.userPrograms = userPrograms;
	}

	public UserPrograms getUserPrograms() {
		return userPrograms;
	}

	public void setUserPrograms(UserPrograms userPrograms) {
		this.userPrograms = userPrograms;
	}

}
