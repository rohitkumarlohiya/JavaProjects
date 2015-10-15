package org.firstonlineuniversity.models.courses;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_QUIZ_QUESTION_CHOICES", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuizQuestionsOptions extends AbstractEntity implements
		Serializable {

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CHOICE_NUMBER", nullable = false)
	private int choiceNumber;

	@Column(name = "CHOICE_TEXT", length = 2000, nullable = false)
	private String choiceText;

	@Column(name = "CHOICE_HINT", length = 1000, nullable = true)
	private String choiceHint;

	@Column(name = "ANSWER_TEXT", length = 2000, nullable = true)
	private String answerText;

	@Column(name = "IS_CORRECT_ANSWER", nullable = true)
	private boolean correctAnswer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "QUIZ_ID")
	private QuizInformation quizInformation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "QUESTION_ID")
	private QuizQuestions quizQuestions;

	public QuizQuestionsOptions(long id, int choiceNumber, String choiceText,
			String choiceHint, String answerText, boolean isCorrectAnswer,
			QuizInformation quizInformation, QuizQuestions quizQuestions) {
		super();
		this.choiceNumber = choiceNumber;
		this.choiceText = choiceText;
		this.choiceHint = choiceHint;
		this.answerText = answerText;
		this.quizInformation = quizInformation;
		this.quizQuestions = quizQuestions;
		this.correctAnswer = isCorrectAnswer;
		this.setId(id);
	}

	public QuizQuestionsOptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getChoiceNumber() {
		return choiceNumber;
	}

	public void setChoiceNumber(int choiceNumber) {
		this.choiceNumber = choiceNumber;
	}

	public String getChoiceText() {
		return choiceText;
	}

	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}

	public String getChoiceHint() {
		return choiceHint;
	}

	public void setChoiceHint(String choiceHint) {
		this.choiceHint = choiceHint;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public boolean isCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public QuizInformation getQuizInformation() {
		return quizInformation;
	}

	public void setQuizInformation(QuizInformation quizInformation) {
		this.quizInformation = quizInformation;
	}

	public QuizQuestions getQuizQuestions() {
		return quizQuestions;
	}

	public void setQuizQuestions(QuizQuestions quizQuestions) {
		this.quizQuestions = quizQuestions;
	}
}
