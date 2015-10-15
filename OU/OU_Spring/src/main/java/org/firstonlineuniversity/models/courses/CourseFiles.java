package org.firstonlineuniversity.models.courses;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_COURSE_CONTENTS", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourseFiles extends AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "FILE_NAME", length = 200, nullable = false, unique=true)
	private String fileName;
	
	@Column(name = "FILE_DISPLAY_NAME", length = 200, nullable = false)
	private String fileDisplayName;

	@Column(name = "FILE_TYPE", length = 80, nullable = false)
	private String fileType;

	@Column(name = "FILE_DESCRIPTION", length = 1000, nullable = false)
	private String fileDescription;

	@Column(name = "FILE_SIZE", length = 11, nullable = false)
	private int fileSize;

	@Column(name = "FILE_FORMAT", length = 20, nullable = false)
	private String fileFormat;

	@Column(name = "FILE_PATH", length = 200, nullable = false, unique=true)
	private String filePath;

	@Column(name = "FILE_INDEX", length = 200, nullable = false)
	private short fileIndex;

	@Column(name = "FILE_DURATION", length = 4, nullable = false)
	private short fileDuration;
	
	@Column(name = "CONTENT_KEY", length = 200, nullable = true, unique=true)
	private String contentKey;
	
	@Column(name = "PAID", nullable = true)
	private boolean paid;

	public CourseFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseFiles(long id,String fileName, String fileDisplayName,
			String fileType, String fileDescription, int fileSize,
			String fileFormat, String filePath, short fileIndex,
			short fileDuration, CourseInformation course, CourseLectures lecture,String contentKey) {
		super();
		super.setId(id);
		this.fileName = fileName;
		this.fileDisplayName = fileDisplayName;
		this.fileType = fileType;
		this.fileDescription = fileDescription;
		this.fileSize = fileSize;
		this.fileFormat = fileFormat;
		this.filePath = filePath;
		this.fileIndex = fileIndex;
		this.fileDuration = fileDuration;
		this.course = course;
		this.lecture = lecture;
		this.contentKey = contentKey;
	}
	
	public CourseFiles(String fileName, String fileDisplayName,
			String fileType, String fileDescription, int fileSize,
			String fileFormat, String filePath, short fileIndex,
			short fileDuration, CourseInformation course, CourseLectures lecture,String contentKey) {
		super();
		this.fileName = fileName;
		this.fileDisplayName = fileDisplayName;
		this.fileType = fileType;
		this.fileDescription = fileDescription;
		this.fileSize = fileSize;
		this.fileFormat = fileFormat;
		this.filePath = filePath;
		this.fileIndex = fileIndex;
		this.fileDuration = fileDuration;
		this.course = course;
		this.lecture = lecture;
		this.contentKey = contentKey;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private CourseInformation course;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private CourseLectures lecture;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public short getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(short fileIndex) {
		this.fileIndex = fileIndex;
	}

	public short getFileDuration() {
		return fileDuration;
	}

	public void setFileDuration(short fileDuration) {
		this.fileDuration = fileDuration;
	}

	public CourseInformation getCourse() {
		return course;
	}

	public void setCourse(CourseInformation course) {
		this.course = course;
	}

	public CourseLectures getLecture() {
		return lecture;
	}

	public void setLecture(CourseLectures lecture) {
		this.lecture = lecture;
	}

	public String getFileDisplayName() {
		return fileDisplayName;
	}

	public void setFileDisplayName(String fileDisplayName) {
		this.fileDisplayName = fileDisplayName;
	}

	public String getContentKey() {
		return contentKey;
	}
	
	public void setContentKey(String contentKey) {
		this.contentKey = contentKey;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
