package org.firstonlineuniversity.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.conf.CassandraConnector;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.models.notes.UserNotes;
import org.firstonlineuniversity.models.userquestions.UserQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;

@Repository
public class CassandraDaoImpl implements CassandraDao {

	private final Logger log = Logger.getLogger(CassandraDaoImpl.class);
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

	/*
	 * User Notes List
	 */
	@Override
	public List<UserNotes> getUserNotes(long accountId, long courseId) {
		String query = "SELECT * FROM ed.un_usernotes where accountid = "
				+ accountId;

		if (courseId != 0)
			query += " and courseid=" + courseId;

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		List<UserNotes> list = new ArrayList<UserNotes>();
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}

		if (resultSet != null) {
			Iterator<Row> iterator = resultSet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				UserNotes reportsData = mapToUserNotes(row);
				list.add(reportsData);
			}
		}

		return list;
	}

	private UserNotes mapToUserNotes(Row row) {
		UserNotes userNotes = new UserNotes();
		userNotes.setAccountId(row.getLong("accountid"));
		userNotes.setCourseId(row.getLong("courseid"));
		userNotes.setNotesContext(row.getString("notescontext"));
		userNotes.setNotesId(row.getUUID("notesId"));
		userNotes.setNotesIndex(row.getInt("notesIndex"));
		userNotes.setNotesTags(row.getString("notestags"));
		userNotes.setNotesText(row.getString("notestext"));
		userNotes.setNotesTitle(row.getString("notestitle"));
		userNotes.setUpdatedDate(row.getDate("updatedate"));
		userNotes.setCourseName(row.getString("coursename"));
		userNotes.setSectionId(row.getLong("sectionid"));
		userNotes.setSectionName(row.getString("sectionname"));
		userNotes.setLectureId(row.getLong("lectureid"));
		userNotes.setLectureName(row.getString("lecturename"));

		return userNotes;
	}

	private UserQuestions mapToUserQuestions(Row row) {
		UserQuestions userQuestions = new UserQuestions();
		userQuestions.setAccountId(row.getLong("accountid"));
		userQuestions.setCourseId(row.getLong("courseid"));
		userQuestions.setQuestionId(row.getUUID("questionid"));
		userQuestions.setAccountname(row.getString("accountname"));
		userQuestions.setQuestionDetails(row.getString("questiondetails"));
		userQuestions.setQuestionTags(row.getSet("questiontags", String.class));
		userQuestions.setQuestionText(row.getString("questiontext"));
		userQuestions.setEnabled(row.getBool("enabled"));
		userQuestions.setUpdatedDate(row.getDate("updatedate"));
		userQuestions.setWantAnswer(row.getInt("wantanswers"));
		userQuestions.setSectionId(row.getLong("sectionid"));
		userQuestions.setSectionname(row.getString("sectionname"));
		userQuestions.setLectureId(row.getLong("lectureid"));
		userQuestions.setLecturename(row.getString("lecturename"));

		return userQuestions;
	}

	/*
	 * User single note by id - Edit API
	 */
	@Override
	public UserNotes getUserNotesById(long accountId, long courseId,
			UUID notesID) {
		UserNotes userNotes = null;
		String query = "SELECT * FROM ed.un_usernotes where accountid = "
				+ accountId;
		query += " and courseid=" + courseId;
		query += " and notesid=" + notesID;

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		List<UserNotes> list = new ArrayList<UserNotes>();
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}

		if (resultSet == null)
			throw new EntityNotFoundException();

		Iterator<Row> iterator = resultSet.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			UserNotes reportsData = mapToUserNotes(row);
			list.add(reportsData);
		}

		if (list != null && list.size() > 0)
			userNotes = list.get(0);

		return userNotes;
	}

	/*
	 * User Notes Update
	 */
	@Override
	public void updateUserNotes(long accountId, long courseId, UUID notesID,
			UserNotes userNotes) {

		String query = "update ed.un_usernotes set notescontext='"
				+ userNotes.getNotesContext() + "', notesindex="
				+ userNotes.getNotesIndex() + "," + " notestags='"
				+ userNotes.getNotesTags() + "',updatedate="
				+ new Date().getTime() + ", notestext='"
				+ userNotes.getNotesText() + "'," + " notestitle='"
				+ userNotes.getNotesTitle() + "' where accountid = "
				+ accountId + "" + " and courseid =  " + courseId
				+ " and notesid =  " + notesID + "";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception", e);
			throw new SomethingWentWrongException();
		}
	}

	/*
	 * Delete user notes by id
	 */
	@Override
	public void deleteUserNotes(long accountId, long courseId, UUID notesID) {
		String query = "delete from ed.un_usernotes " + "where accountid = "
				+ accountId + "" + " and courseid =  " + courseId
				+ " and notesid =  " + notesID + "";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {

			log.error("Session Execute Exception");
			throw new SomethingWentWrongException();
		}

	}

	@Override
	public List<UserQuestions> getUserQuestions(long accountId, long courseId) {
		String query = "SELECT * FROM ed.userquestions where courseid = "
				+ courseId;

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		List<UserQuestions> list = new ArrayList<UserQuestions>();
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}

		if (resultSet != null) {
			Iterator<Row> iterator = resultSet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				UserQuestions userQuestions = mapToUserQuestions(row);
				list.add(userQuestions);
			}
		}

		return list;
	}

	@Override
	public UserQuestions getUserQuestionsById(long accountId, long courseId,
			UUID questionID) {
		UserQuestions userQuestion = null;
		String query = "SELECT * FROM ed.userquestions where courseid = "
				+ courseId + " and questionid =  " + questionID + "";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		List<UserQuestions> list = new ArrayList<UserQuestions>();
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}

		if (resultSet == null)
			throw new EntityNotFoundException();

		Iterator<Row> iterator = resultSet.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			UserQuestions userQuestions = mapToUserQuestions(row);
			list.add(userQuestions);
		}

		if (list != null && list.size() > 0)
			userQuestion = list.get(0);

		return userQuestion;
	}

	@Override
	public void updateUserQuestions(UserQuestions userNotes, long accountId) {
		String query = "update ed.userquestions set enabled = "
				+ userNotes.isEnabled() + ", questiontext = '"
				+ userNotes.getQuestionText() + "', questionTags="
				+ userNotes.getTags() + "," + "updatedate='"
				+ new Date().getTime() + "',wantanswers="
				+ userNotes.getWantAnswer() + ", questiondetails='"
				+ userNotes.getQuestionDetails() + "', accountname = '"
				+ userNotes.getAccountname() + "' where courseid = "
				+ userNotes.getCourseId() + " and questionid=" + ""
				+ userNotes.getQuestionId() + "";

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
	public void deleteUserQuestions(long accountId, long courseId,
			UUID questionID) {
		String query = "delete from ed.userquestions " + "where courseid =  "
				+ courseId + " and questionid =  " + questionID + "";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {

			log.error("Session Execute Exception");
			throw new SomethingWentWrongException();
		}

	}

	@Override
	public void addUserNotes(UserNotes userNotes) {
		String query = "insert into ed.un_usernotes(accountid,courseid,coursename,sectionid,sectionname,lectureid,lecturename,"
				+ "notesid,notescontext,notesindex,notestags,notestext,notestitle,updatedate)"
				+ " values("
				+ userNotes.getAccountId()
				+ ","
				+ userNotes.getCourseId()
				+ ",'"
				+ userNotes.getCourseName()
				+ "',"
				+ userNotes.getSectionId()
				+ ",'"
				+ userNotes.getSectionName()
				+ "',"
				+ userNotes.getLectureId()
				+ ",'"
				+ userNotes.getLectureName()
				+ "',"
				+ userNotes.getNotesId()
				+ ",'"
				+ userNotes.getNotesContext()
				+ "',"
				+ userNotes.getNotesIndex()
				+ ",'"
				+ userNotes.getNotesTags()
				+ "','"
				+ userNotes.getNotesText()
				+ "','"
				+ userNotes.getNotesTitle()
				+ "','"
				+ new Date().getTime()
				+ "');";

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
	public void addUserQuestions(UserQuestions userQuestions) {
		String query = "insert into ed.userquestions(courseid,questionid,sectionid,lectureid,accountid,accountname,sectionname,lecturename,enabled,questiondetails,questiontags,"
				+ "questiontext,updatedate,wantanswers)" + " values("
				+ userQuestions.getCourseId()
				+ ","
				+ userQuestions.getQuestionId()
				+ ","
				+ userQuestions.getSectionId()
				+ ","
				+ userQuestions.getLectureId()
				+ ","
				+ userQuestions.getAccountId()
				+ ",'"
				+ userQuestions.getAccountname()
				+ "','"
				+ userQuestions.getSectionname()
				+ "','"
				+ userQuestions.getLecturename()
				+ "',"
				+ userQuestions.isEnabled()
				+ ",'"
				+ userQuestions.getQuestionDetails()
				+ "',"
				+ userQuestions.getTags()
				+ ",'"
				+ userQuestions.getQuestionText()
				+ "',"
				+ new Date().getTime()
				+ "," + userQuestions.getWantAnswer() + ");";

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

}
