package org.firstonlineuniversity.models.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UN_COURSE_LECTURES_V", catalog = "ED")
public class CourseLecturesView {
	@Column(name = "COURSE_ID")
	private long courseId;

	@Column(name = "COURSE_NAME")
	private String courseName;

	@Column(name = "SECTION_ID")
	private long sectionId;

	@Column(name = "SECTION_INDEX")
	private int sectionIndex;

	@Column(name = "SECTION_NAME")
	private String sectionName;

	@Id
	@Column(name = "LECTURE_ID")
	private long lectureId;

	@Column(name = "LECTURE_INDEX")
	private int lectureIndex;

	@Column(name = "LECTURE_NAME")
	private String lectureName;

	@Column(name = "LECTURE_DESCRIPTION")
	private String lectureDescription;

	@Column(name = "LECTURE_VISIBLE")
	private boolean lectureVisible;

	@Column(name = "LECTURE_KEY")
	private String lectureKey;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public long getSectionId() {
		return sectionId;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

	public int getSectionIndex() {
		return sectionIndex;
	}

	public void setSectionIndex(int sectionIndex) {
		this.sectionIndex = sectionIndex;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public long getLectureId() {
		return lectureId;
	}

	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}

	public int getLectureIndex() {
		return lectureIndex;
	}

	public void setLectureIndex(int lectureIndex) {
		this.lectureIndex = lectureIndex;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getLectureDescription() {
		return lectureDescription;
	}

	public void setLectureDescription(String lectureDescription) {
		this.lectureDescription = lectureDescription;
	}

	public boolean isLectureVisible() {
		return lectureVisible;
	}

	public void setLectureVisible(boolean lectureVisible) {
		this.lectureVisible = lectureVisible;
	}

	public String getLectureKey() {
		return lectureKey;
	}

	public void setLectureKey(String lectureKey) {
		this.lectureKey = lectureKey;
	}
}
