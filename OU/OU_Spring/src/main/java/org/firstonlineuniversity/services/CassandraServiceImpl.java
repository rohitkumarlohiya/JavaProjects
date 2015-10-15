package org.firstonlineuniversity.services;

import java.util.List;
import java.util.UUID;

import org.firstonlineuniversity.dao.CassandraDao;
import org.firstonlineuniversity.dao.UserQuestionsCassandraDao;
import org.firstonlineuniversity.models.notes.UserNotes;
import org.firstonlineuniversity.models.userquestions.UserQuestionOptions;
import org.firstonlineuniversity.models.userquestions.UserQuestions;
import org.firstonlineuniversity.models.userquestions.UserResponseComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CassandraServiceImpl implements CassandraService {

	@Autowired
	CassandraDao cassandraDao;

	@Autowired
	UserQuestionsCassandraDao userQuestionsCassandraDao;

	@Override
	public List<UserNotes> getUserNotes(long accountId, long courseId) {
		return cassandraDao.getUserNotes(accountId, courseId);
	}

	@Override
	public UserNotes getUserNotesById(long accountId, long courseId,
			UUID notesID) {
		return cassandraDao.getUserNotesById(accountId, courseId, notesID);
	}

	@Override
	public void updateUserNotes(long accountId, long courseId, UUID notesID,
			UserNotes userNotes) {
		cassandraDao.updateUserNotes(accountId, courseId, notesID, userNotes);

	}

	@Override
	public void deleteUserNotes(long accountId, long courseId, UUID notesID) {
		cassandraDao.deleteUserNotes(accountId, courseId, notesID);

	}

	@Override
	public List<UserQuestions> getUserQuestions(long accountId, long courseId) {
		// TODO Auto-generated method stub
		return cassandraDao.getUserQuestions(accountId, courseId);
	}

	@Override
	public UserQuestions getUserQuestionsById(long accountId, long courseId,
			UUID questionID) {
		// TODO Auto-generated method stub
		return cassandraDao.getUserQuestionsById(accountId, courseId,
				questionID);
	}

	@Override
	public void updateUserQuestions(UserQuestions userNotes, long accountId) {
		cassandraDao.updateUserQuestions(userNotes, accountId);

	}

	@Override
	public void deleteUserQuestions(long accountId, long courseId,
			UUID questionID) {
		cassandraDao.deleteUserQuestions(accountId, courseId, questionID);

	}

	@Override
	public void addUserNotes(UserNotes userNotes) {
		cassandraDao.addUserNotes(userNotes);

	}

	@Override
	public void addUserOptions(UserQuestionOptions userQuestionOptions) {
		userQuestionsCassandraDao.addUserOptions(userQuestionOptions);
	}

	@Override
	public List<UserQuestionOptions> getUserOptions(long accountId,
			UUID questionId) {
		return userQuestionsCassandraDao.getUserOptions(accountId, questionId);
	}

	@Override
	public UserQuestionOptions getUserOptionsById(long accountId,
			UUID questionId, UUID optionID) {
		return userQuestionsCassandraDao.getUserOptionsById(accountId,
				questionId, optionID);
	}

	@Override
	public void updateUserOptions(UserQuestionOptions userQuestionOptions) {
		userQuestionsCassandraDao.updateUserOptions(userQuestionOptions);

	}

	@Override
	public void deleteUserResponse(long accountId, UUID questionId,
			UUID optionID) {
		userQuestionsCassandraDao.deleteUserResponse(accountId, questionId,
				optionID);
	}

	@Override
	public void addUserOptionsComments(UserResponseComments userResponseComments) {
		userQuestionsCassandraDao.addUserOptionsComments(userResponseComments);

	}

	@Override
	public List<UserResponseComments> getUserResponseComments(UUID responseid,
			UUID commentid) {
		return userQuestionsCassandraDao.getUserResponseComments(responseid,
				commentid);
	}

	@Override
	public UserResponseComments getUserResponseCommentsById(UUID responseid,
			UUID commentid) {
		return userQuestionsCassandraDao.getUserResponseCommentsById(
				responseid, commentid);
	}

	@Override
	public void updateUserResponseComments(
			UserResponseComments userResponseComments) {
		userQuestionsCassandraDao
				.updateUserResponseComments(userResponseComments);

	}

	@Override
	public void deleteUserResponseComments(UUID responseid, UUID commentid) {
		userQuestionsCassandraDao.deleteUserResponseComments(responseid,
				commentid);
	}

	@Override
	public void addUserQuestions(UserQuestions userQuestions) {
		cassandraDao.addUserQuestions(userQuestions);
		
	}

}
