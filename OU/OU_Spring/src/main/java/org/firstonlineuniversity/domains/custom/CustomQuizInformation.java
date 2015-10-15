package org.firstonlineuniversity.domains.custom;

import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseLectures;
import org.firstonlineuniversity.models.courses.CourseSections;

public class CustomQuizInformation extends AbstractEntity {

	private String quizName;
	private String quizDescription;
	private String intialMessage;
	private String completionMessage;
	private String accessCriteria;
	private String openDate;
	private String closeDate;
	private short maxAttempts;
	private short maxTime;
	private boolean reviewQuiz;
	private boolean shuffleQuestions;
	private boolean shuffleANSWERS;
	private short gradeMethod;
	private short panalityMethod;
	private int passGrade;
	private int feedbackType;
	private boolean disableResult;
	private boolean activeFlag;
	private CourseInformation course;
	private CourseSections section;
	private CourseLectures lecture;

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getQuizDescription() {
		return quizDescription;
	}

	public void setQuizDescription(String quizDescription) {
		this.quizDescription = quizDescription;
	}

	public String getIntialMessage() {
		return intialMessage;
	}

	public void setIntialMessage(String intialMessage) {
		this.intialMessage = intialMessage;
	}

	public String getCompletionMessage() {
		return completionMessage;
	}

	public void setCompletionMessage(String completionMessage) {
		this.completionMessage = completionMessage;
	}

	public String getAccessCriteria() {
		return accessCriteria;
	}

	public void setAccessCriteria(String accessCriteria) {
		this.accessCriteria = accessCriteria;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public short getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(short maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public short getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(short maxTime) {
		this.maxTime = maxTime;
	}

	public short getGradeMethod() {
		return gradeMethod;
	}

	public void setGradeMethod(short gradeMethod) {
		this.gradeMethod = gradeMethod;
	}

	public short getPanalityMethod() {
		return panalityMethod;
	}

	public void setPanalityMethod(short panalityMethod) {
		this.panalityMethod = panalityMethod;
	}

	public int getPassGrade() {
		return passGrade;
	}

	public void setPassGrade(int passGrade) {
		this.passGrade = passGrade;
	}

	public int getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(int feedbackType) {
		this.feedbackType = feedbackType;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public CourseInformation getCourse() {
		return course;
	}

	public void setCourse(CourseInformation course) {
		this.course = course;
	}

	public CourseSections getSection() {
		return section;
	}

	public void setSection(CourseSections section) {
		this.section = section;
	}

	public CourseLectures getLecture() {
		return lecture;
	}

	public void setLecture(CourseLectures lecture) {
		this.lecture = lecture;
	}

	public boolean isReviewQuiz() {
		return reviewQuiz;
	}

	public void setReviewQuiz(boolean reviewQuiz) {
		this.reviewQuiz = reviewQuiz;
	}

	public boolean isShuffleQuestions() {
		return shuffleQuestions;
	}

	public void setShuffleQuestions(boolean shuffleQuestions) {
		this.shuffleQuestions = shuffleQuestions;
	}

	public boolean isShuffleANSWERS() {
		return shuffleANSWERS;
	}

	public void setShuffleANSWERS(boolean shuffleANSWERS) {
		this.shuffleANSWERS = shuffleANSWERS;
	}

	public boolean isDisableResult() {
		return disableResult;
	}

	public void setDisableResult(boolean disableResult) {
		this.disableResult = disableResult;
	}
}
