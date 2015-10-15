package org.firstonlineuniversity.services;

import java.util.List;
import java.util.UUID;

import org.firstonlineuniversity.models.notes.UserNotes;
import org.firstonlineuniversity.models.userquestions.UserQuestionOptions;
import org.firstonlineuniversity.models.userquestions.UserQuestions;
import org.firstonlineuniversity.models.userquestions.UserResponseComments;

public interface CassandraService {
	public void addUserNotes(UserNotes userNotes);

	public List<UserNotes> getUserNotes(long accountId, long courseId);

	public UserNotes getUserNotesById(long accountId, long courseId,
			UUID notesID);

	public void updateUserNotes(long accountId, long courseId, UUID notesID,
			UserNotes userNotes);

	public void deleteUserNotes(long accountId, long courseId, UUID notesID);

	public void addUserQuestions(UserQuestions userQuestions);

	public List<UserQuestions> getUserQuestions(long accountId, long courseId);

	public UserQuestions getUserQuestionsById(long accountId, long courseId,
			UUID questionID);

	public void updateUserQuestions(UserQuestions userNotes, long accountId);

	public void deleteUserQuestions(long accountId, long courseId,
			UUID questionID);

	public void addUserOptions(UserQuestionOptions userQuestionOptions);

	public List<UserQuestionOptions> getUserOptions(long accountId,
			UUID questionId);

	public UserQuestionOptions getUserOptionsById(long accountId,
			UUID questionId, UUID optionID);

	public void updateUserOptions(UserQuestionOptions userQuestionOptions);

	public void deleteUserResponse(long accountId, UUID questionId,
			UUID optionID);

	public void addUserOptionsComments(UserResponseComments userResponseComments);

	public List<UserResponseComments> getUserResponseComments(UUID responseid,
			UUID commentid);

	public UserResponseComments getUserResponseCommentsById(UUID responseid,
			UUID commentid);

	public void updateUserResponseComments(
			UserResponseComments userResponseComments);

	public void deleteUserResponseComments(UUID responseid, UUID commentid);
}
