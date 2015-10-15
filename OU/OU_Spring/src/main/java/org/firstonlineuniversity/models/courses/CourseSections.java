package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Date;
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
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_COURSE_SECTIONS", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourseSections extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SECTION_INDEX", length = 11, nullable = false)
	private int sectionIndex;

	@Column(name = "SECTION_NAME", length = 200, nullable = false, unique = true)
	private String sectionName;

	@Column(name = "SECTION_DESCRIPTION", length = 5000, nullable = false)
	private String sectionDescription;

	@Column(name = "SECTION_VISIBLE", nullable = false)
	private boolean sectionVisible;

	@Column(name = "SECTION_PURPOSE", length = 5000, nullable = true)
	private String sectionPurpose;

	@Column(name = "ACTIVITY_SEQUENCE", length = 200, nullable = true)
	private String activitySequence;

	@Column(name = "SECTION_KEY", length = 200, nullable = true)
	private String sectionKey;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "COURSE_ID")
	private CourseInformation course;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "section")
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CourseLectures> courseLectures;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "section")
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<QuizInformation> quizInformations;

	public CourseSections(long id, int sectionIndex, String sectionName,
			String sectionDescription, boolean sectionVisible,
			String sectionPurpose, String activitySequence, String sectionKey,
			long cB, Date cD) {
		// super();
		this.setId(id);
		this.sectionIndex = sectionIndex;
		this.sectionName = sectionName;
		this.sectionDescription = sectionDescription;
		this.sectionVisible = sectionVisible;
		this.sectionPurpose = sectionPurpose;
		this.activitySequence = activitySequence;
		this.sectionKey = sectionKey;
		this.setCB(cB);
		this.setCD(cD);

	}

	public CourseSections() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseSections(long id, String sectionName) {
		super();
		super.setId(id);
		this.sectionName = sectionName;
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

	public String getSectionDescription() {
		return sectionDescription;
	}

	public void setSectionDescription(String sectionDescription) {
		this.sectionDescription = sectionDescription;
	}

	public boolean isSectionVisible() {
		return sectionVisible;
	}

	public void setSectionVisible(boolean sectionVisible) {
		this.sectionVisible = sectionVisible;
	}

	public String getSectionPurpose() {
		return sectionPurpose;
	}

	public void setSectionPurpose(String sectionPurpose) {
		this.sectionPurpose = sectionPurpose;
	}

	public String getActivitySequence() {
		return activitySequence;
	}

	public void setActivitySequence(String activitySequence) {
		this.activitySequence = activitySequence;
	}

	public CourseInformation getCourse() {
		return course;
	}

	public void setCourse(CourseInformation course) {
		this.course = course;
	}

	public Set<CourseLectures> getCourseLectures() {
		return courseLectures;
	}

	public void setCourseLectures(Set<CourseLectures> courseLectures) {
		this.courseLectures = courseLectures;
	}

	public Set<QuizInformation> getQuizInformations() {
		return quizInformations;
	}

	public void setQuizInformations(Set<QuizInformation> quizInformations) {
		this.quizInformations = quizInformations;
	}

	public String getSectionKey() {
		return sectionKey;
	}

	public void setSectionKey(String sectionKey) {
		this.sectionKey = sectionKey;
	}
}
