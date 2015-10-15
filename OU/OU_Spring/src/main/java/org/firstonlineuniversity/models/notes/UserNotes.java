package org.firstonlineuniversity.models.notes;

import java.util.Date;
import java.util.UUID;

public class UserNotes {

	private UUID notesId;
	private long courseId;
	private String courseName;
	private long sectionId;
	private String sectionName;
	private long lectureId;
	private String lectureName;
	private long accountId;
	private Date updatedDate;
	private int notesIndex;
	private String notesTitle;
	private String notesText;
	private String notesTags;
	private String notesContext;

	public UUID getNotesId() {
		return notesId;
	}

	public void setNotesId(UUID notesId) {
		this.notesId = notesId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getNotesIndex() {
		return notesIndex;
	}

	public void setNotesIndex(int notesIndex) {
		this.notesIndex = notesIndex;
	}

	public String getNotesTitle() {
		return notesTitle;
	}

	public void setNotesTitle(String notesTitle) {
		this.notesTitle = notesTitle;
	}

	public String getNotesText() {
		return notesText;
	}

	public void setNotesText(String notesText) {
		this.notesText = notesText;
	}

	public String getNotesTags() {
		return notesTags;
	}

	public void setNotesTags(String notesTags) {
		this.notesTags = notesTags;
	}

	public String getNotesContext() {
		return notesContext;
	}

	public void setNotesContext(String notesContext) {
		this.notesContext = notesContext;
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

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
}