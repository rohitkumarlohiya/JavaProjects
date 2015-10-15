package org.firstonlineuniversity.dao;

import java.util.List;
import java.util.UUID;

import org.firstonlineuniversity.models.notes.UserNotes;
import org.firstonlineuniversity.models.userquestions.UserQuestions;

public interface CassandraDao {
	public void addUserNotes(UserNotes userNotes);
	public List<UserNotes> getUserNotes(long accountId, long courseId);
	public UserNotes getUserNotesById(long accountId, long courseId, UUID notesID);
	public void updateUserNotes(long accountId, long courseId, UUID notesID, UserNotes userNotes);
	public void deleteUserNotes(long accountId, long courseId, UUID notesID);
	
	public void addUserQuestions(UserQuestions userQuestions);
	public List<UserQuestions> getUserQuestions(long accountId, long courseId);
	public UserQuestions getUserQuestionsById(long accountId, long courseId, UUID questionID);
	public void updateUserQuestions(UserQuestions userNotes, long accountId);
	public void deleteUserQuestions(long accountId, long courseId, UUID questionID);
}
