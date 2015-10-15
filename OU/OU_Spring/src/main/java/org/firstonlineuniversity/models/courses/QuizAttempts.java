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
@Table(name = "UN_QUIZ_ATTEMPTS", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuizAttempts extends AbstractEntity implements Serializable {

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "QUIZ_ID", length = 11, nullable = false)
	private Long quizId;

	@Column(name = "ACCOUNT_ID", length = 11, nullable = false)
	private Long accountId;

	@Column(name = "QUESTION_ID", length = 11, nullable = false)
	private Long questionId;

	@Column(name = "USER_CHOICE_ID", length = 11, nullable = false)
	private Long userChoiceId;

	@Column(name = "MAX_MARKS", nullable = false)
	private double maxMarks;

	@Column(name = "MIN_FRACTION", nullable = false)
	private double minFraction;

	@Column(name = "MAX_FRACTION", nullable = false)
	private double maxFraction;

	@Column(name = "QUESTION", length = 2000, nullable = true)
	private String question;

	@Column(name = "RIGHT_ANSWER", length = 2000, nullable = true)
	private String rightAnswer;

	@Column(name = "RESPONSE", length = 2000, nullable = true)
	private String response;

	@Column(name = "FEEDBACK", length = 2000, nullable = true)
	private String feedback;

	@Column(name = "ATTEMPT_DATE", nullable = false)
	private Date attemptDate;

	@Column(name = "ATTEMPT_NUMBER", nullable = true)
	private int attemptNumber;

	@Column(name = "CORRECT", nullable = true)
	private boolean correct;

	@Column(name = "QUESTION_TYPE", nullable = true)
	private int questionType;

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

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getUserChoiceId() {
		return userChoiceId;
	}

	public void setUserChoiceId(Long userChoiceId) {
		this.userChoiceId = userChoiceId;
	}

	public double getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}

	public double getMinFraction() {
		return minFraction;
	}

	public void setMinFraction(double minFraction) {
		this.minFraction = minFraction;
	}

	public double getMaxFraction() {
		return maxFraction;
	}

	public void setMaxFraction(double maxFraction) {
		this.maxFraction = maxFraction;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getAttemptDate() {
		return attemptDate;
	}

	public void setAttemptDate(Date attemptDate) {
		this.attemptDate = attemptDate;
	}

	public int getAttemptNumber() {
		return attemptNumber;
	}

	public void setAttemptNumber(int attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
}
