package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_QUIZ_QUESTIONS", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuizQuestions extends AbstractEntity implements Serializable {

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "QUESTION_NUMBER", nullable = false)
	private int questionNumber;

	@Column(name = "QUESTION_TEXT", length = 2000, nullable = false)
	private String questionText;

	@Column(name = "QUESTION_HINT", length = 2000, nullable = true)
	private String questionHint;

	@Column(name = "CORRECT_ANSWER", length = 200, nullable = true)
	private String correctAnswer;

	@Column(name = "QUESTION_TYPE", nullable = false)
	private int questionType;

	@Column(name = "GRADE", nullable = false)
	private double grade;

	@Column(name = "PANALTY", nullable = false)
	private double panalty;

	@Column(name = "IMAGE_LINK", length = 255, nullable = true)
	private String imageLink;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "QUIZ_ID")
	private QuizInformation quizInformation;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<QuizQuestionsOptions> quizQuestionsOptions;

	public QuizQuestions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizQuestions(long id, int questionNumber, String questionText,
			String questionHint, String correctAnswer, int questionType,
			double grade, double panalty, String imageLink, boolean enabled,
			QuizInformation quizInformation,
			Set<QuizQuestionsOptions> quizQuestionsOptions) {
		super();
		this.setId(id);
		this.questionNumber = questionNumber;
		this.questionText = questionText;
		this.questionHint = questionHint;
		this.correctAnswer = correctAnswer;
		this.questionType = questionType;
		this.grade = grade;
		this.panalty = panalty;
		this.imageLink = imageLink;
		this.setEnabled(enabled);
		this.quizInformation = quizInformation;
		this.quizQuestionsOptions = quizQuestionsOptions;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionHint() {
		return questionHint;
	}

	public void setQuestionHint(String questionHint) {
		this.questionHint = questionHint;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public double getPanalty() {
		return panalty;
	}

	public void setPanalty(double panalty) {
		this.panalty = panalty;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public QuizInformation getQuizInformation() {
		return quizInformation;
	}

	public void setQuizInformation(QuizInformation quizInformation) {
		this.quizInformation = quizInformation;
	}

	public Set<QuizQuestionsOptions> getQuizQuestionsOptions() {
		return quizQuestionsOptions;
	}

	public void setQuizQuestionsOptions(
			Set<QuizQuestionsOptions> quizQuestionsOptions) {
		this.quizQuestionsOptions = quizQuestionsOptions;
	}
}
