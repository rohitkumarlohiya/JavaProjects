package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Set;

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
@Table(name = "UN_COURSE_LECTURES", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourseLectures extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "LECTURE_INDEX", length = 11, nullable = false, unique = true)
	private int lectureIndex;

	@Column(name = "LECTURE_NAME", length = 200, nullable = false)
	private String lectureName;

	@Column(name = "LECTURE_DESCRIPTION", length = 5000, nullable = true)
	private String lectureDescription;

	@Column(name = "LECTURE_VISIBLE", nullable = false)
	private boolean lectureVisible;

	@Column(name = "LECTURE_KEY", length = 200, nullable = true)
	private String lectureKey;

	public CourseLectures() {
		super();
	}

	public CourseLectures(String lectureName, long id) {
		super();
		this.lectureName = lectureName;
		super.setId(id);
	}

	public CourseLectures(long id, int lectureIndex, String lectureName,
			String lectureDescription, boolean lectureVisible,
			CourseInformation course, CourseSections section, String lectureKey) {
		super();
		this.setId(id);
		this.lectureIndex = lectureIndex;
		this.lectureName = lectureName;
		this.lectureDescription = lectureDescription;
		this.lectureVisible = lectureVisible;
		this.course = course;
		this.section = section;
		this.lectureKey = lectureKey;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "COURSE_ID")
	private CourseInformation course;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	 @JoinColumn(name="SECTION_ID")
	private CourseSections section;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lecture")
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CourseContent> courseFiles;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lecture")
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CourseResources> courseResources;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lecture")
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<QuizInformation> quizInformations;

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

	public Set<CourseContent> getCourseFiles() {
		return courseFiles;
	}

	public void setCourseFiles(Set<CourseContent> courseFiles) {
		this.courseFiles = courseFiles;
	}

	public Set<CourseResources> getCourseResources() {
		return courseResources;
	}

	public void setCourseResources(Set<CourseResources> courseResources) {
		this.courseResources = courseResources;
	}

	public Set<QuizInformation> getQuizInformations() {
		return quizInformations;
	}

	public void setQuizInformations(Set<QuizInformation> quizInformations) {
		this.quizInformations = quizInformations;
	}

	public String getLectureKey() {
		return lectureKey;
	}

	public void setLectureKey(String lectureKey) {
		this.lectureKey = lectureKey;
	}
}
