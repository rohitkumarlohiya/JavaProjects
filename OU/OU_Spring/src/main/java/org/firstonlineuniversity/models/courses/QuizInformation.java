package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
@Table(name = "UN_QUIZ_INFORMATION", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuizInformation extends AbstractEntity implements Serializable {

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "QUIZ_NAME", length = 255, nullable = true)
	private String quizName;

	@Column(name = "QUIZ_DESCRIPTION", length = 2000, nullable = false)
	private String quizDescription;

	@Column(name = "INITIAL_MESSAGE", length = 2000, nullable = false)
	private String intialMessage;

	@Column(name = "COMPLETION_MESSAGE", length = 2000, nullable = false)
	private String completionMessage;

	@Column(name = "ACCESS_CRITERIA", length = 2000, nullable = false)
	private String accessCriteria;

	@Column(name = "OPEN_DATE", nullable = false)
	private Date openDate;

	@Column(name = "CLOSE_DATE", nullable = false)
	private Date closeDate;

	@Column(name = "MAX_ATTEMPTS", nullable = false)
	private short maxAttempts;

	@Column(name = "MAX_TIME", nullable = false)
	private short maxTime;

	@Column(name = "REVIEW_QUIZ", nullable = false)
	private boolean reviewQuiz;

	@Column(name = "SHUFFLE_QUESTIONS", nullable = false)
	private boolean shuffleQuestions;

	@Column(name = "SHUFFLE_ANSWERS", nullable = false)
	private boolean shuffleANSWERS;

	@Column(name = "GRADE_METHOD", nullable = false)
	private short gradeMethod;

	@Column(name = "PANALITY_METHOD", nullable = false)
	private short panalityMethod;

	@Column(name = "PASS_GRADE", nullable = false)
	private int passGrade;

	@Column(name = "FEEDBACK_TYPE", nullable = false)
	private int feedbackType;

	@Column(name = "DISABLE_RESULT", nullable = false)
	private boolean disableResult;

	@Column(name = "ACTIVE_FLAG", nullable = false)
	private boolean activeFlag;
	
	@Column(name = "PASSING_CRITERIA", nullable = false)
	private double passingCriteria;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "COURSE_ID")
	private CourseInformation course;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "SECTION_ID")
	private CourseSections section;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "LECTURE_ID")
	private CourseLectures lecture;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<QuizQuestions> quizQuestions;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<QuizQuestionsOptions> quizQuestionsOptions;
	
	public QuizInformation(long id, String quizName, String quizDescription,
			String intialMessage, String completionMessage,
			String accessCriteria, Date openDate, Date closeDate,
			short maxAttempts, short maxTime, boolean reviewQuiz,
			boolean shuffleQuestions, boolean shuffleANSWERS, short gradeMethod,
			short panalityMethod, int passGrade, int feedbackType,
			boolean disableResult, boolean activeFlag, CourseInformation course,
			CourseSections section, CourseLectures lecture) {
		super();
		this.setId(id);
		this.quizName = quizName;
		this.quizDescription = quizDescription;
		this.intialMessage = intialMessage;
		this.completionMessage = completionMessage;
		this.accessCriteria = accessCriteria;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.maxAttempts = maxAttempts;
		this.maxTime = maxTime;
		this.reviewQuiz = reviewQuiz;
		this.shuffleQuestions = shuffleQuestions;
		this.shuffleANSWERS = shuffleANSWERS;
		this.gradeMethod = gradeMethod;
		this.panalityMethod = panalityMethod;
		this.passGrade = passGrade;
		this.feedbackType = feedbackType;
		this.disableResult = disableResult;
		this.activeFlag = activeFlag;
		this.course = course;
		this.section = section;
		this.lecture = lecture;
	}

	public QuizInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizInformation(String quizName, String quizDescription,
			String intialMessage, String completionMessage,
			String accessCriteria, Date openDate, Date closeDate,
			short maxAttempts, short maxTime, boolean reviewQuiz,
			boolean shuffleQuestions, boolean shuffleANSWERS, short gradeMethod,
			short panalityMethod, int passGrade, int feedbackType,
			boolean disableResult, boolean activeFlag, CourseInformation course,
			CourseSections section, CourseLectures lecture) {
		super();
		this.quizName = quizName;
		this.quizDescription = quizDescription;
		this.intialMessage = intialMessage;
		this.completionMessage = completionMessage;
		this.accessCriteria = accessCriteria;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.maxAttempts = maxAttempts;
		this.maxTime = maxTime;
		this.reviewQuiz = reviewQuiz;
		this.shuffleQuestions = shuffleQuestions;
		this.shuffleANSWERS = shuffleANSWERS;
		this.gradeMethod = gradeMethod;
		this.panalityMethod = panalityMethod;
		this.passGrade = passGrade;
		this.feedbackType = feedbackType;
		this.disableResult = disableResult;
		this.activeFlag = activeFlag;
		this.course = course;
		this.section = section;
		this.lecture = lecture;
	}

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

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
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

	public Set<QuizQuestions> getQuizQuestions() {
		return quizQuestions;
	}

	public void setQuizQuestions(Set<QuizQuestions> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}

	public Set<QuizQuestionsOptions> getQuizQuestionsOptions() {
		return quizQuestionsOptions;
	}

	public void setQuizQuestionsOptions(
			Set<QuizQuestionsOptions> quizQuestionsOptions) {
		this.quizQuestionsOptions = quizQuestionsOptions;
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

	public double getPassingCriteria() {
		return passingCriteria;
	}

	public void setPassingCriteria(double passingCriteria) {
		this.passingCriteria = passingCriteria;
	}
}
