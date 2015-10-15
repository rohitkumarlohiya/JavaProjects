package org.firstonlineuniversity.dao;

import java.util.List;
import java.util.UUID;

import org.firstonlineuniversity.models.userquestions.UserQuestionOptions;
import org.firstonlineuniversity.models.userquestions.UserResponseComments;

public interface UserQuestionsCassandraDao {
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

	public void deleteUserResponseComments(UUID responseid,
			UUID commentid);

}
