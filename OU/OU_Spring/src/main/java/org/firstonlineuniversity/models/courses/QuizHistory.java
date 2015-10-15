package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_QUIZ_HISTORY", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuizHistory extends AbstractEntity implements Serializable {

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "QUIZ_ID", length = 11, nullable = true)
	private Long quizId;

	@Column(name = "ACCOUNT_ID", length = 11, nullable = true)
	private Long accountId;

	@Column(name = "COURSE_ID", length = 11, nullable = true)
	private Long courseId;

	@Column(name = "ATTEMPT_NUMBER", length = 11, nullable = true)
	private int attemptNumber;

	@Column(name = "QUIZ_START_TIME", nullable = true)
	private Date quizStartTime;

	@Column(name = "QUIZ_FINISH_TIME", nullable = true)
	private Date quizFinishTime;

	@Column(name = "STATUS", length = 16, nullable = true)
	private String status;

	@Column(name = "STATUS_CHECK_TIME", nullable = true)
	private Date statusCheckTime;

	@Column(name = "CORRECT_ANSWERS", length = 11, nullable = true)
	private int correctAnswers;

	@Column(name = "UN_ATTEMPTED_QUESTIONS", length = 11, nullable = true)
	private int unattemptedQuestions;

	@Column(name = "TOTAL_QUESTIONS", length = 11, nullable = true)
	private int totalQuestions;

	@Column(name = "QUIZ_REVIEW", length = 2000, nullable = true)
	private String quizReview;

	@Column(name = "QUIZ_DURATION", length = 80, nullable = true)
	private String response;

	@Column(name = "PASSED", length = 80, nullable = true)
	private boolean passed;

	@Column(name = "quizGrade", nullable = true)
	private double quizGrade;

	public double getQuizGrade() {
		return quizGrade;
	}

	public void setQuizGrade(double quizGrade) {
		this.quizGrade = quizGrade;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public int getAttemptNumber() {
		return attemptNumber;
	}

	public void setAttemptNumber(int attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	public Date getQuizStartTime() {
		return quizStartTime;
	}

	public void setQuizStartTime(Date quizStartTime) {
		this.quizStartTime = quizStartTime;
	}

	public Date getQuizFinishTime() {
		return quizFinishTime;
	}

	public void setQuizFinishTime(Date quizFinishTime) {
		this.quizFinishTime = quizFinishTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusCheckTime() {
		return statusCheckTime;
	}

	public void setStatusCheckTime(Date statusCheckTime) {
		this.statusCheckTime = statusCheckTime;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public int getUnattemptedQuestions() {
		return unattemptedQuestions;
	}

	public void setUnattemptedQuestions(int unattemptedQuestions) {
		this.unattemptedQuestions = unattemptedQuestions;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public String getQuizReview() {
		return quizReview;
	}

	public void setQuizReview(String quizReview) {
		this.quizReview = quizReview;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public double getQuizGrades() {
		return quizGrades;
	}

	public void setQuizGrades(double quizGrades) {
		this.quizGrades = quizGrades;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	@Column(name = "QUIZ_GRADES", nullable = true)
	private double quizGrades;

}
