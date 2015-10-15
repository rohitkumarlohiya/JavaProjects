package org.firstonlineuniversity.models.courses;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_COURSE_RESOURCES", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourseResources extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FILE_NAME", length = 200, nullable = false, unique = true)
	private String fileName;

	@Column(name = "FILE_DISPLAY_NAME", length = 200, nullable = false)
	private String fileDisplayName;

	@Column(name = "FILE_TYPE", length = 80, nullable = false)
	private String fileType;

	@Column(name = "FILE_DESCRIPTION", length = 5000, nullable = false)
	private String fileDescription;

	@Column(name = "FILE_SIZE", length = 11, nullable = false)
	private int fileSize;

	@Column(name = "FILE_FORMAT", length = 20, nullable = false)
	private String fileFormat;

	@Column(name = "FILE_PATH", length = 200, nullable = false, unique = true)
	private String filePath;

	@Column(name = "FILE_INDEX", length = 200, nullable = false)
	private short fileIndex;

	@Column(name = "PROMOTIONAL", nullable = true)
	private boolean promotional;

	@Column(name = "RESOURCE_KEY", length = 200, nullable = true)
	private String resourceKey;
	
	@Column(name = "DOWNLOAD", nullable = true)
	private boolean download;

	@Column(name = "PAID", nullable = true)
	private boolean paid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "COURSE_ID")
	private CourseInformation course;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "LECTURE_ID")
	private CourseLectures lecture;

	public CourseResources(long id, String fileName, String fileDisplayName,
			String fileType, String fileDescription, int fileSize,
			String fileFormat, String filePath, short fileIndex,
			CourseInformation course, CourseLectures lecture, String resourceKey) {
		super();
		this.fileName = fileName;
		this.fileDisplayName = fileDisplayName;
		this.fileType = fileType;
		this.fileDescription = fileDescription;
		this.fileSize = fileSize;
		this.fileFormat = fileFormat;
		this.filePath = filePath;
		this.fileIndex = fileIndex;
		this.course = course;
		this.lecture = lecture;
		this.resourceKey = resourceKey;
		super.setId(id);
	}

	public CourseResources(Boolean paid, String fileName,
			String fileDisplayName, String fileType, String fileDescription,
			int fileSize, String fileFormat, String filePath, short fileIndex,
			CourseInformation course, CourseLectures lecture) {

		super();
		this.fileName = fileName;
		this.fileDisplayName = fileDisplayName;
		this.fileType = fileType;
		this.fileDescription = fileDescription;
		this.fileSize = fileSize;
		this.fileFormat = fileFormat;
		this.filePath = filePath;
		this.fileIndex = fileIndex;
		this.course = course;
		this.lecture = lecture;
		this.setPaid(paid);
	}

	public CourseResources() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDisplayName() {
		return fileDisplayName;
	}

	public void setFileDisplayName(String fileDisplayName) {
		this.fileDisplayName = fileDisplayName;
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

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public boolean isPromotional() {
		return promotional;
	}

	public void setPromotional(boolean promotional) {
		this.promotional = promotional;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}
}
