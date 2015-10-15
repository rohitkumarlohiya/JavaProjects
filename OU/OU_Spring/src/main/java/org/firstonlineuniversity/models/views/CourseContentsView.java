package org.firstonlineuniversity.models.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UN_COURSE_CONTENTS_V", catalog = "ED")
public class CourseContentsView {

	@Column(name = "COURSE_ID")
	private long courseId;

	@Column(name = "COURSE_NAME")
	private String courseName;

	@Column(name = "SECTION_ID")
	private long sectionId;

	@Column(name = "SECTION_INDEX")
	private int sectionIndex;

	@Column(name = "SECTION_NAME")
	private String sectionName;

	@Column(name = "LECTURE_ID")
	private long lectureId;

	@Column(name = "LECTURE_INDEX")
	private int lectureIndex;

	@Column(name = "LECTURE_NAME")
	private String lectureName;

	@Id
	@Column(name = "CONTENT_ID")
	private long contentId;

	@Column(name = "CONTENT_INDEX")
	private int contentIndex;

	@Column(name = "CONTENT_NAME")
	private String contentName;
	
	@Column(name = "CONTENT_TYPE")
	private String contentType;

	@Column(name = "CONTENT_DESCRIPTION")
	private String contentDescription;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_PATH")
	private String filePath;

	@Column(name = "FILE_FORMAT")
	private String fileFormat;

	@Column(name = "FILE_SIZE")
	private int fileSize;

	@Column(name = "PAID")
	private boolean paid;
	
	@Column(name = "DOWNLOAD")
	private boolean download;

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}

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

	public long getLectureId() {
		return lectureId;
	}

	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}

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

	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public int getContentIndex() {
		return contentIndex;
	}

	public void setContentIndex(int contentIndex) {
		this.contentIndex = contentIndex;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
