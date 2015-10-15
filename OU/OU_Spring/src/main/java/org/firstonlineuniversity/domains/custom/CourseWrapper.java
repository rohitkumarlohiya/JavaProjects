package org.firstonlineuniversity.domains.custom;

public class CourseWrapper {
	private long id;
	private String courseName;

	public CourseWrapper(long id, String courseName) {
		super();
		this.id = id;
		this.courseName = courseName;
	}

	public CourseWrapper() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
