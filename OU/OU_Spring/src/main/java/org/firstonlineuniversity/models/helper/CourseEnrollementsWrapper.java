package org.firstonlineuniversity.models.helper;

import org.firstonlineuniversity.models.enrollements.CoursesEnrollements;
import org.firstonlineuniversity.models.views.CourseCatalogView;

public class CourseEnrollementsWrapper {
	private CourseCatalogView courseCatalogView;
	private CoursesEnrollements coursesEnrollements;

	public CourseCatalogView getCourseCatalogView() {
		return courseCatalogView;
	}

	public void setCourseCatalogView(CourseCatalogView courseCatalogView) {
		this.courseCatalogView = courseCatalogView;
	}

	public CoursesEnrollements getCoursesEnrollements() {
		return coursesEnrollements;
	}

	public void setCoursesEnrollements(CoursesEnrollements coursesEnrollements) {
		this.coursesEnrollements = coursesEnrollements;
	}

}
