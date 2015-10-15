package org.firstonlineuniversity.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.conf.CassandraConnector;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.models.courses.CourseReviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;

@Repository
public class CourseReviewsDaoImpl implements CourseReviewsDao {

	private final Logger log = Logger.getLogger(CourseReviewsDaoImpl.class);
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
	public void addCourseReviews(CourseReviews courseReviews) {
		String query = "insert into ed.coursereviews(id,name,accountid,courseid,rating,recommand,reviewdate,reviewtext,reviewtitle)  values("
				+ courseReviews.getId()
				+ ",'"
				+ courseReviews.getName()
				+ "',"
				+ courseReviews.getAccountId()
				+ ","
				+ courseReviews.getCourseId()
				+ ","
				+ courseReviews.getRating()
				+ ","
				+ courseReviews.isRecommend()
				+ ","
				+ courseReviews.getReviewDate().getTime()
				+ ",'"
				+ courseReviews.getReviewText()
				+ "','"
				+ courseReviews.getReviewTitle() + "');";

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
	public List<CourseReviews> getCourseReviews(Long accountId, Long courseId) {
		String query = "SELECT * FROM ed.coursereviews where courseid="
				+ courseId;

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		List<CourseReviews> list = new ArrayList<CourseReviews>();
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}

		if (resultSet != null) {
			Iterator<Row> iterator = resultSet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				CourseReviews courseReviews = mapToCourseReviews(row);
				list.add(courseReviews);
			}
		}

		return list;
	}

	@Override
	public CourseReviews getCourseReviewsById(Long courseId, UUID reviewId) {
		String query = "SELECT * FROM ed.coursereviews where courseid="
				+ courseId;

		if (reviewId != null)
			query += " and id=" + reviewId;

		CourseReviews courseReviews = null;

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		ResultSet resultSet = null;
		try {
			resultSet = session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
		}

		if (resultSet != null) {
			Iterator<Row> iterator = resultSet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				courseReviews = mapToCourseReviews(row);
			}
		}

		return courseReviews;
	}

	@Override
	public void updateCourseReviews(CourseReviews courseReviews) {

		String query = "update ed.coursereviews set rating="
				+ courseReviews.getRating() + ", recommand="
				+ courseReviews.isRecommend() + "," + " reviewdate="
				+ courseReviews.getReviewDate().getTime() + ",reviewtext='"
				+ courseReviews.getReviewText() + "', reviewtitle='"
				+ courseReviews.getReviewTitle() + "'  where courseid = "
				+ courseReviews.getCourseId() + "" + " and id =  "
				+ courseReviews.getId() + "";

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
	public void deleteCourseReviews(Long courseId, UUID reviewId) {
		String query = "delete from ed.coursereviews " + " where courseid = "
				+ courseId + "" + " and id =  " + reviewId + "";

		log.info(query);
		Statement stmt = new SimpleStatement(query);

		try {
			session.execute(stmt);
		} catch (Exception e) {
			log.error("Session Execute Exception");
			e.printStackTrace();
		}

	}

	private CourseReviews mapToCourseReviews(Row row) {
		CourseReviews courseReviews = new CourseReviews();
		courseReviews.setId(row.getUUID("id"));
		courseReviews.setAccountId(row.getLong("accountid"));
		courseReviews.setCourseId(row.getLong("courseid"));
		courseReviews.setRating(row.getInt("rating"));
		courseReviews.setRecommend(row.getBool("recommand"));
		courseReviews.setReviewDate(row.getDate("reviewdate"));
		courseReviews.setReviewText(row.getString("reviewtext"));
		courseReviews.setReviewTitle(row.getString("reviewtitle"));
		courseReviews.setName(row.getString("name"));
		return courseReviews;
	}
}
