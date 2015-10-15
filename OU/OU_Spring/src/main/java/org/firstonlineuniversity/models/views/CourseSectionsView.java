package org.firstonlineuniversity.models.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UN_COURSE_SECTIONS_V", catalog = "ED")
public class CourseSectionsView {

	@Column(name = "COURSE_ID")
	private long courseId;

	@Column(name = "COURSE_NAME")
	private String courseName;

	@Id
	@Column(name = "SECTION_ID")
	private long sectionId;

	@Column(name = "SECTION_INDEX")
	private int sectionIndex;

	@Column(name = "SECTION_NAME")
	private String sectionName;

	@Column(name = "SECTION_DESCRIPTION")
	private String sectionDescription;

	@Column(name = "SECTION_PURPOSE")
	private String sectionPurpose;

	@Column(name = "SECTION_VISIBLE")
	private boolean sectionVisible;

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

	public String getSectionDescription() {
		return sectionDescription;
	}

	public void setSectionDescription(String sectionDescription) {
		this.sectionDescription = sectionDescription;
	}

	public String getSectionPurpose() {
		return sectionPurpose;
	}

	public void setSectionPurpose(String sectionPurpose) {
		this.sectionPurpose = sectionPurpose;
	}

	public boolean isSectionVisible() {
		return sectionVisible;
	}

	public void setSectionVisible(boolean sectionVisible) {
		this.sectionVisible = sectionVisible;
	}
}
