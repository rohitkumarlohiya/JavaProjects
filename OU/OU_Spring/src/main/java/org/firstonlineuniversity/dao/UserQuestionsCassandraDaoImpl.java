package org.firstonlineuniversity.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.conf.CassandraConnector;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.models.userquestions.UserQuestionOptions;
import org.firstonlineuniversity.models.userquestions.UserResponseComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;

@Repository
public class UserQuestionsCassandraDaoImpl implements UserQuestionsCassandraDao {

	private final Logger log = Logger
			.getLogger(UserQuestionsCassandraDaoImpl.class);
	private Session session;

	@Autowired
	private CassandraConnector cassandraConn;

	private void setCassandraSession() {
		session = cassandraConn.getSession();
	}

	@PostConstruct
	public void init() {
		setCassandraSession();
	}

	@Override
	public void addUserOptions(UserQuestionOptions userQuestionOptions) {
		String query = "insert into ed.userresponses(accountid,questionid,responseid,accepted,anynomous,downvote,enabled,name,"
				+ "responsetext,updatedate,upvote)" + " values("
				+ userQuestionOptions.getAccountId()
				+ ","
				+ userQuestionOptions.getQuestionId()
				+ ","
				+ userQuestionOptions.getResponseId()
				+ ","
				+ userQuestionOptions.isAccepted()
				+ ","
				+ userQuestionOptions.isAnonymous()
				+ ","
				+ userQuestionOptions.getDownvote()
				+ ","
				+ userQuestionOptions.isEnabled()
				+ ",'"
				+ userQuestionOptions.getName()
				+ "','"
				+ userQuestionOptions.getResponseText()
				+ "',"
				+ new Date().getTime()
				+ ","
				+ userQuestionOptions.getUpvote()
				+ ");";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

	}

	@Override
	public List<UserQuestionOptions> getUserOptions(long accountId,
			UUID questionId) {
		String query = "SELECT * FROM ed.userresponses where questionid="
				+ questionId;

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		List<UserQuestionOptions> list = new ArrayList<UserQuestionOptions>();
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}

		if (resultSet != null) {
			Iterator<Row> iterator = resultSet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				UserQuestionOptions reportsData = mapToUserResponses(row);
				list.add(reportsData);
			}
		}

		return list;
	}

	@Override
	public UserQuestionOptions getUserOptionsById(long accountId,
			UUID questionId, UUID optionID) {
		String query = "SELECT * FROM ed.userresponses where accountid = "
				+ accountId;

		query += " and questionid=" + questionId;

		query += " and responseid=" + optionID;

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}
		UserQuestionOptions reportsData = null;
		if (resultSet != null) {
			Iterator<Row> iterator = resultSet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				reportsData = mapToUserResponses(row);
			}
		}

		return reportsData;
	}

	@Override
	public void updateUserOptions(UserQuestionOptions userQuestionOptions) {

		String query = "update ed.userresponses set accepted="
				+ userQuestionOptions.isAccepted() + ", anynomous="
				+ userQuestionOptions.isAnonymous() + "," + " downvote="
				+ userQuestionOptions.getDownvote() + ",enabled="
				+ userQuestionOptions.isEnabled() + ", responsetext='"
				+ userQuestionOptions.getResponseText() + "'," + " updatedate="
				+ new Date().getTime() + "," + " upvote="
				+ userQuestionOptions.getUpvote() + " where accountid = "
				+ userQuestionOptions.getAccountId() + ""
				+ " and questionid =  " + userQuestionOptions.getQuestionId()
				+ " and responseid =  " + userQuestionOptions.getResponseId()
				+ "";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception", e);
			throw new SomethingWentWrongException();
		}
	}

	@Override
	public void deleteUserResponse(long accountId, UUID questionId,
			UUID optionID) {
		String query = "delete from ed.userresponses " + "where accountid = "
				+ accountId + "" + " and questionid =  " + questionId
				+ " and responseid =  " + optionID + "";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {

			log.error("Session Execute Exception");
			throw new SomethingWentWrongException();
		}

	}

	private UserQuestionOptions mapToUserResponses(Row row) {
		UserQuestionOptions userQuestionOptions = new UserQuestionOptions();
		userQuestionOptions.setAccountId(row.getLong("accountid"));
		userQuestionOptions.setName(row.getString("name"));
		userQuestionOptions.setQuestionId(row.getUUID("questionid"));
		userQuestionOptions.setResponseId(row.getUUID("responseid"));
		userQuestionOptions.setAccepted(row.getBool("accepted"));
		userQuestionOptions.setAnonymous(row.getBool("anynomous"));
		userQuestionOptions.setDownvote(row.getInt("downvote"));
		userQuestionOptions.setEnabled(row.getBool("enabled"));
		userQuestionOptions.setResponseText(row.getString("responsetext"));
		userQuestionOptions.setUpdatedDate(row.getDate("updatedate"));
		userQuestionOptions.setUpvote(row.getInt("upvote"));
		return userQuestionOptions;
	}

	@Override
	public void addUserOptionsComments(UserResponseComments userResponseComments) {
		String query = "insert into ed.userresponsecomments(accountid,responseid,commentid,anynomous,commenttext,enabled,updatedate)"
				+ " values("
				+ userResponseComments.getAccountId()
				+ ","
				+ userResponseComments.getResponseid()
				+ ","
				+ userResponseComments.getCommentid()
				+ ","
				+ userResponseComments.isAnynomous()
				+ ",'"
				+ userResponseComments.getCommenttext()
				+ "',"
				+ userResponseComments.isEnabled()
				+ ","
				+ new Date().getTime()
				+ ");";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

	}

	@Override
	public List<UserResponseComments> getUserResponseComments(UUID responseid,
			UUID commentid) {
		String query = "SELECT * FROM ed.userresponsecomments where responseid = "
				+ responseid;

		if (commentid != null)
			query += " and commentid=" + commentid;

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		List<UserResponseComments> list = new ArrayList<UserResponseComments>();
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}

		if (resultSet != null) {
			Iterator<Row> iterator = resultSet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				UserResponseComments reportsData = mapToUserResponseComments(row);
				list.add(reportsData);
			}
		}

		return list;
	}

	@Override
	public UserResponseComments getUserResponseCommentsById(UUID responseid,
			UUID commentid) {
		String query = "SELECT * FROM ed.userresponsecomments where responseid = "
				+ responseid;
		query += " and commentid=" + commentid;

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}
		UserResponseComments reportsData = null;
		if (resultSet != null) {
			Iterator<Row> iterator = resultSet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				reportsData = mapToUserResponseComments(row);
			}
		}

		return reportsData;
	}

	@Override
	public void updateUserResponseComments(
			UserResponseComments userResponseComments) {

		String query = "update ed.userresponsecomments set anynomous="
				+ userResponseComments.isAnynomous() + ", commenttext='"
				+ userResponseComments.getCommenttext() + "'," + " enabled="
				+ userResponseComments.isEnabled() + ",updatedate="
				+ new Date().getTime() + " where responseid="
				+ userResponseComments.getResponseid() + " and commentid="
				+ userResponseComments.getCommentid();

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception", e);
			throw new SomethingWentWrongException();
		}
	}

	@Override
	public void deleteUserResponseComments(UUID responseid, UUID commentid) {
		String query = "delete from ed.userresponsecomments "
				+ "where responseid = " + responseid + ""
				+ " and commentid =  " + commentid + "";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {

			log.error("Session Execute Exception");
			throw new SomethingWentWrongException();
		}

	}

	private UserResponseComments mapToUserResponseComments(Row row) {
		UserResponseComments userResponseComments = new UserResponseComments();
		userResponseComments.setAnynomous(row.getBool("anynomous"));
		userResponseComments.setName("name");
		userResponseComments.setCommentid(row.getUUID("commentid"));
		userResponseComments.setCommenttext(row.getString("commenttext"));
		userResponseComments.setEnabled(row.getBool("enabled"));
		userResponseComments.setResponseid(row.getUUID("responseid"));
		userResponseComments.setUpdatedDate(row.getDate("updatedate"));
		userResponseComments.setAccountId(row.getLong("accountid"));
		return userResponseComments;
	}
}