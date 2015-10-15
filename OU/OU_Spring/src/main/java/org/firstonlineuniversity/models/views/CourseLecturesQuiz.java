package org.firstonlineuniversity.models.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UN_COURSE_LECTURE_QUIZ_V", catalog = "ED")
public class CourseLecturesQuiz {
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

	@Column(name = "LECTURE_ID")
	private long lectureId;

	@Column(name = "LECTURE_INDEX")
	private int lectureIndex;

	@Column(name = "LECTURE_NAME")
	private String lectureName;

	@Id
	@Column(name = "QUIZ_ID")
	private long quizId;

	@Column(name = "QUIZ_INDEX")
	private int quizIndex;

	@Column(name = "QUIZ_NAME")
	private String quizName;

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

	public long getQuizId() {
		return quizId;
	}

	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}

	public int getQuizIndex() {
		return quizIndex;
	}

	public void setQuizIndex(int quizIndex) {
		this.quizIndex = quizIndex;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	 
	
}
