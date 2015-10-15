package org.firstonlineuniversity.models.courses;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_QUIZ_ATTEMPT_DETAILS", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuizAttemptDetails extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ATTEMPT_ID", nullable = false)
	private Long attemptId;

	@Column(name = "ANSWER_TEXT", length = 200, nullable = true)
	private String answerText;

	@Column(name = "CHOICE_ID", nullable = false)
	private Long choiceId;

	@Column(name = "CHOICE_NUMBER", nullable = false)
	private Long choiceNumber;

	@Column(name = "CHOICE_TEXT", length = 200, nullable = false)
	private String choiceTest;

	@Column(name = "CORRECT_ANSWER", nullable = false)
	private Boolean correctAnswer;

	@Column(name = "SELECTED_ANSWER", nullable = true)
	private Boolean selectedAnswer;

	@Column(name = "GRADE", nullable = false)
	private Double grade;

	@Column(name = "QUESTION_ID", nullable = false)
	private Long questionId;

	@Column(name = "QUIZ_ID", nullable = false)
	private Long quizId;

	@Column(name = "USER_RESPONSE", length = 200, nullable = false)
	private String userResponse;

	public QuizAttemptDetails(Long attemptId, String answerText,
			Long choiceId, Long choiceNumber, String choiceTest,
			Boolean correctAnswer, Boolean selectedAnswer, Double grade,
			Long questionId, Long quizId, String userResponse) {
		super();
		this.attemptId = attemptId;
		this.answerText = answerText;
		this.choiceId = choiceId;
		this.choiceNumber = choiceNumber;
		this.choiceTest = choiceTest;
		this.correctAnswer = correctAnswer;
		this.selectedAnswer = selectedAnswer;
		this.grade = grade;
		this.questionId = questionId;
		this.quizId = quizId;
		this.userResponse = userResponse;
	}

	public QuizAttemptDetails() {
		super();
	}

	public Long getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Long getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(Long choiceId) {
		this.choiceId = choiceId;
	}

	public Long getChoiceNumber() {
		return choiceNumber;
	}

	public void setChoiceNumber(Long choiceNumber) {
		this.choiceNumber = choiceNumber;
	}

	public String getChoiceTest() {
		return choiceTest;
	}

	public void setChoiceTest(String choiceTest) {
		this.choiceTest = choiceTest;
	}

	public Boolean getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Boolean getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Boolean selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public String getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(String userResponse) {
		this.userResponse = userResponse;
	}
}
