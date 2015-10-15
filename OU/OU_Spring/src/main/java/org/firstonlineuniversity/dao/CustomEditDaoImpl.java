package org.firstonlineuniversity.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.models.courses.CourseCategory;
import org.firstonlineuniversity.models.courses.CourseContent;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseLectures;
import org.firstonlineuniversity.models.courses.CourseResources;
import org.firstonlineuniversity.models.courses.CourseSections;
import org.firstonlineuniversity.models.courses.QuizInformation;
import org.firstonlineuniversity.models.courses.QuizQuestions;
import org.firstonlineuniversity.models.courses.QuizQuestionsOptions;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dataDaoEditable")
public class CustomEditDaoImpl implements CustomEditDao {

	static final Logger logger = Logger.getLogger(CustomEditDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public CourseContent getCourseFilesEditableDate(long id) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		CourseContent courseFiles = new CourseContent();
		try {
			Criteria criteria = session.createCriteria(CourseContent.class);
			criteria.add(Restrictions.eq("id", id));

			criteria.createAlias("course", "course");
			criteria.createAlias("lecture", "lecture");

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("fileName"), "fileName")
					.add(Projections.property("fileDisplayName"),
							"fileDisplayName")
					.add(Projections.property("fileType"), "fileType")
					.add(Projections.property("fileDescription"),
							"fileDescription")
					.add(Projections.property("fileSize"), "fileSize")
					.add(Projections.property("fileFormat"), "fileFormat")
					.add(Projections.property("filePath"), "filePath")
					.add(Projections.property("fileIndex"), "fileIndex")
					.add(Projections.property("fileDuration"), "fileDuration")
					.add(Projections.property("contentKey"), "contentKey")
					.add(Projections.property("paid"), "paid")
					.add(Projections.property("course.id"), "courseId")
					.add(Projections.property("course.courseName"),
							"courseName")
					.add(Projections.property("lecture.id"), "lectureId")
					.add(Projections.property("lecture.lectureName"),
							"lectureName")
					.add(Projections.property("CD"), "CD")
					.add(Projections.property("CB"), "CB")
					.add(Projections.property("enabled"), "enabled")
					.add(Projections.property("download"), "download"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			Object[] fieldsArray = list.get(0);

			if (fieldsArray[9] != null)
				courseFiles.setContentKey(fieldsArray[9].toString());

			if (fieldsArray[11] != null && fieldsArray[12] != null)
				courseFiles.setCourse(new CourseInformation(fieldsArray[12]
						.toString(), (long) fieldsArray[11]));
			else
				courseFiles.setCourse(new CourseInformation("", 0));

			if (fieldsArray[3] != null)
				courseFiles.setFileDescription(fieldsArray[3].toString());

			if (fieldsArray[1] != null)
				courseFiles.setFileDisplayName(fieldsArray[1].toString());

			if (fieldsArray[8] != null)
				courseFiles.setFileDuration((short) fieldsArray[8]);

			if (fieldsArray[5] != null)
				courseFiles.setFileFormat(fieldsArray[5].toString());

			if (fieldsArray[7] != null)
				courseFiles.setFileIndex((short) fieldsArray[7]);

			if (fieldsArray[0] != null)
				courseFiles.setFileName(fieldsArray[0].toString());

			if (fieldsArray[6] != null)
				courseFiles.setFilePath(fieldsArray[6].toString());

			if (fieldsArray[4] != null)
				courseFiles.setFileSize((int) fieldsArray[4]);

			if (fieldsArray[2] != null)
				courseFiles.setFileType(fieldsArray[2].toString());

			courseFiles.setId(id);

			if (fieldsArray[13] != null && fieldsArray[14] != null)
				courseFiles.setLecture(new CourseLectures(fieldsArray[14]
						.toString(), (long) fieldsArray[13]));
			else
				courseFiles.setLecture(new CourseLectures("", 0));

			if (fieldsArray[10] != null)
				courseFiles.setPaid((boolean) fieldsArray[10]);

			try {
				courseFiles.setCB((long) fieldsArray[16]);
				courseFiles.setCD((Date) fieldsArray[15]);
				courseFiles.setEnabled((Boolean) fieldsArray[17]);
				courseFiles.setDownload((Boolean) fieldsArray[18]);
			} catch (Exception e) {
			}

			for (Object object : fieldsArray) {
				if (object == null)
					object = "";
			}

			logger.info("Size: " + fieldsArray.length);

			/*
			 * courseFiles = new CourseFiles(id, fieldsArray[0].toString(),
			 * fieldsArray[1].toString(), fieldsArray[2].toString(),
			 * fieldsArray[3].toString(), (int) fieldsArray[4],
			 * fieldsArray[5].toString(), fieldsArray[6].toString(), (short)
			 * fieldsArray[7], (short) fieldsArray[8],
			 * fieldsArray[9].toString(), (boolean) fieldsArray[10], new
			 * CourseInformation(fieldsArray[12].toString(), (long)
			 * fieldsArray[11]), new CourseLectures( fieldsArray[14].toString(),
			 * (long) fieldsArray[13]));
			 */
			tx.commit();
		} finally {

			session.close();
		}
		return courseFiles;
	}

	@Override
	public CourseResources getCourseResourcesEditableDate(long id)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		CourseResources courseResources = new CourseResources();
		try {
			Criteria criteria = session.createCriteria(CourseResources.class);
			criteria.add(Restrictions.eq("id", id));

			criteria.createAlias("course", "course");
			criteria.createAlias("lecture", "lecture");

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("fileName"), "fileName")
					.add(Projections.property("fileDisplayName"),
							"fileDisplayName")
					.add(Projections.property("fileType"), "fileType")
					.add(Projections.property("fileDescription"),
							"fileDescription")
					.add(Projections.property("fileSize"), "fileSize")
					.add(Projections.property("fileFormat"), "fileFormat")
					.add(Projections.property("filePath"), "filePath")
					.add(Projections.property("fileIndex"), "fileIndex")
					.add(Projections.property("course.difficultyLevel"),
							"course.difficultyLevel")
					.add(Projections.property("resourceKey"), "resourceKey")
					.add(Projections.property("paid"), "paid")
					.add(Projections.property("course.id"), "courseId")
					.add(Projections.property("course.courseName"),
							"courseName")
					.add(Projections.property("lecture.id"), "lectureId")
					.add(Projections.property("lecture.lectureName"),
							"lectureName")
					.add(Projections.property("CD"), "CD")
					.add(Projections.property("CB"), "CB")
					.add(Projections.property("enabled"), "enabled")
					.add(Projections.property("download"), "download")
					);

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			Object[] fieldsArray = list.get(0);

			if (fieldsArray[9] != null)
				courseResources.setResourceKey(fieldsArray[9].toString());

			if (fieldsArray[11] != null && fieldsArray[12] != null)
				courseResources.setCourse(new CourseInformation(fieldsArray[12]
						.toString(), (long) fieldsArray[11]));
			else
				courseResources.setCourse(new CourseInformation("", 0));

			if (fieldsArray[3] != null)
				courseResources.setFileDescription(fieldsArray[3].toString());

			if (fieldsArray[1] != null)
				courseResources.setFileDisplayName(fieldsArray[1].toString());

			if (fieldsArray[5] != null)
				courseResources.setFileFormat(fieldsArray[5].toString());

			if (fieldsArray[7] != null)
				courseResources.setFileIndex((short) fieldsArray[7]);

			if (fieldsArray[0] != null)
				courseResources.setFileName(fieldsArray[0].toString());

			if (fieldsArray[6] != null)
				courseResources.setFilePath(fieldsArray[6].toString());

			if (fieldsArray[4] != null)
				courseResources.setFileSize((int) fieldsArray[4]);

			if (fieldsArray[2] != null)
				courseResources.setFileType(fieldsArray[2].toString());

			courseResources.setId(id);

			if (fieldsArray[13] != null && fieldsArray[14] != null)
				courseResources.setLecture(new CourseLectures(fieldsArray[14]
						.toString(), (long) fieldsArray[13]));
			else
				courseResources.setLecture(new CourseLectures("", 0));

			if (fieldsArray[10] != null)
				courseResources.setPromotional((boolean) fieldsArray[10]);
			try {
				courseResources.setCD((Date) fieldsArray[15]);
				courseResources.setCB((long) fieldsArray[16]);
				courseResources.setEnabled((boolean) fieldsArray[17]);
				courseResources.setDownload((boolean) fieldsArray[18]);
			} catch (Exception e) {
				logger.info("CD CD not find : " + e);
			}

			for (Object object : fieldsArray) {
				if (object == null)
					object = "";
			}

			logger.info("Size: " + fieldsArray.length);

			/*
			 * courseFiles = new CourseFiles(id, fieldsArray[0].toString(),
			 * fieldsArray[1].toString(), fieldsArray[2].toString(),
			 * fieldsArray[3].toString(), (int) fieldsArray[4],
			 * fieldsArray[5].toString(), fieldsArray[6].toString(), (short)
			 * fieldsArray[7], (short) fieldsArray[8],
			 * fieldsArray[9].toString(), (boolean) fieldsArray[10], new
			 * CourseInformation(fieldsArray[12].toString(), (long)
			 * fieldsArray[11]), new CourseLectures( fieldsArray[14].toString(),
			 * (long) fieldsArray[13]));
			 */
			tx.commit();
		} finally {

			session.close();
		}
		return courseResources;
	}

	@Override
	public CourseLectures getCourseLecturesEditableData(long id)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		CourseLectures courseLectures = new CourseLectures();
		try {
			Criteria criteria = session.createCriteria(CourseLectures.class);
			criteria.add(Restrictions.eq("id", id));

			criteria.createAlias("course", "course");
			criteria.createAlias("section", "section");

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("lectureIndex"), "lectureIndex")
					.add(Projections.property("lectureName"), "lectureName")
					.add(Projections.property("lectureDescription"),
							"lectureDescription")
					.add(Projections.property("lectureVisible"),
							"lectureVisible")
					.add(Projections.property("lectureKey"), "lectureKey")
					.add(Projections.property("course.id"), "courseId")
					.add(Projections.property("course.courseName"),
							"courseName")
					.add(Projections.property("section.id"), "sectionId")
					.add(Projections.property("section.sectionName"),
							"sectionName")
					.add(Projections.property("CD"), "CD")
					.add(Projections.property("CB"), "CB")
					.add(Projections.property("enabled"), "enabled"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			Object[] fieldsArray = list.get(0);

			courseLectures.setId(id);

			if (fieldsArray[0] != null)
				courseLectures.setLectureIndex((int) fieldsArray[0]);

			if (fieldsArray[1] != null)
				courseLectures.setLectureName(fieldsArray[1].toString());

			if (fieldsArray[2] != null)
				courseLectures.setLectureDescription(fieldsArray[2].toString());

			if (fieldsArray[3] != null)
				courseLectures.setLectureVisible((boolean) fieldsArray[3]);

			if (fieldsArray[4] != null)
				courseLectures.setLectureKey(fieldsArray[4].toString());

			if (fieldsArray[7] != null && fieldsArray[8] != null)
				courseLectures.setSection(new CourseSections(
						(long) fieldsArray[7], fieldsArray[8].toString()));
			else
				courseLectures.setSection(new CourseSections(0, ""));

			if (fieldsArray[5] != null && fieldsArray[6] != null)
				courseLectures.setCourse(new CourseInformation(fieldsArray[6]
						.toString(), (long) fieldsArray[5]));
			else
				courseLectures.setCourse(new CourseInformation("", 0));

			try {
				courseLectures.setCD((Date) fieldsArray[9]);
				courseLectures.setCB((Long) fieldsArray[10]);
				courseLectures.setEnabled((Boolean) fieldsArray[11]);
			} catch (Exception e) {
				logger.info("Unable to fetch CD and CB: " + e);
			}
			for (Object object : fieldsArray) {
				if (object == null)
					object = "";
			}

			logger.info("Size: " + fieldsArray.length);

			/*
			 * courseFiles = new CourseFiles(id, fieldsArray[0].toString(),
			 * fieldsArray[1].toString(), fieldsArray[2].toString(),
			 * fieldsArray[3].toString(), (int) fieldsArray[4],
			 * fieldsArray[5].toString(), fieldsArray[6].toString(), (short)
			 * fieldsArray[7], (short) fieldsArray[8],
			 * fieldsArray[9].toString(), (boolean) fieldsArray[10], new
			 * CourseInformation(fieldsArray[12].toString(), (long)
			 * fieldsArray[11]), new CourseLectures( fieldsArray[14].toString(),
			 * (long) fieldsArray[13]));
			 */
			tx.commit();
		} finally {

			session.close();
		}
		return courseLectures;
	}

	@Override
	public CourseInformation getCoursesEditableData(long id) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		CourseInformation courseInformation = new CourseInformation();
		try {
			Criteria criteria = session.createCriteria(CourseInformation.class);
			criteria.add(Restrictions.eq("id", id));

			criteria.createAlias("courseCategory", "courseCategory");

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("courseCategory.id"),
							"categoryId")
					.add(Projections.property("courseCategory.categoryName"),
							"categoryName"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			Object[] fieldsArray = list.get(0);

			if (fieldsArray[0] != null && fieldsArray[1] != null)
				courseInformation.setCourseCategory(new CourseCategory(
						fieldsArray[1].toString(), (long) fieldsArray[0]));
			else
				courseInformation.setCourseCategory(new CourseCategory("", 0));

			for (Object object : fieldsArray) {
				if (object == null)
					object = "";
			}

			logger.info("Size: " + fieldsArray.length);

			/*
			 * courseFiles = new CourseFiles(id, fieldsArray[0].toString(),
			 * fieldsArray[1].toString(), fieldsArray[2].toString(),
			 * fieldsArray[3].toString(), (int) fieldsArray[4],
			 * fieldsArray[5].toString(), fieldsArray[6].toString(), (short)
			 * fieldsArray[7], (short) fieldsArray[8],
			 * fieldsArray[9].toString(), (boolean) fieldsArray[10], new
			 * CourseInformation(fieldsArray[12].toString(), (long)
			 * fieldsArray[11]), new CourseLectures( fieldsArray[14].toString(),
			 * (long) fieldsArray[13]));
			 */
			tx.commit();
		} finally {

			session.close();
		}
		return courseInformation;
	}

	@Override
	public QuizInformation getQuizEditableData(long id) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		QuizInformation quizInformation = new QuizInformation();
		try {
			Criteria criteria = session.createCriteria(QuizInformation.class);
			criteria.add(Restrictions.eq("id", id));

			criteria.createAlias("course", "course");
			criteria.createAlias("section", "section");
			criteria.createAlias("lecture", "lecture");

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("course.id"), "courseId")
					.add(Projections.property("course.courseName"),
							"courseName")
					.add(Projections.property("section.id"), "sectionId")
					.add(Projections.property("section.sectionName"),
							"sectionName")
					.add(Projections.property("lecture.id"), "lectureId")
					.add(Projections.property("lecture.lectureName"),
							"lectureName"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			if (list == null)
				logger.info("list is null !");
			else
				logger.info("list size: " + list.size());

			Object[] fieldsArray = list.get(0);

			if (fieldsArray[0] != null && fieldsArray[1] != null)
				quizInformation.setCourse(new CourseInformation(fieldsArray[1]
						.toString(), (long) fieldsArray[0]));
			else
				quizInformation.setCourse(new CourseInformation("", 0));

			if (fieldsArray[2] != null && fieldsArray[3] != null)
				quizInformation.setSection(new CourseSections(
						(long) fieldsArray[2], fieldsArray[3].toString()));
			else
				quizInformation.setSection(new CourseSections(0, ""));

			if (fieldsArray[4] != null && fieldsArray[5] != null)
				quizInformation.setLecture(new CourseLectures(fieldsArray[5]
						.toString(), (long) fieldsArray[4]));
			else
				quizInformation.setLecture(new CourseLectures("", 0));

			for (Object object : fieldsArray) {
				if (object == null)
					object = "";
			}

			logger.info("Size: " + fieldsArray.length);

			/*
			 * courseFiles = new CourseFiles(id, fieldsArray[0].toString(),
			 * fieldsArray[1].toString(), fieldsArray[2].toString(),
			 * fieldsArray[3].toString(), (int) fieldsArray[4],
			 * fieldsArray[5].toString(), fieldsArray[6].toString(), (short)
			 * fieldsArray[7], (short) fieldsArray[8],
			 * fieldsArray[9].toString(), (boolean) fieldsArray[10], new
			 * CourseInformation(fieldsArray[12].toString(), (long)
			 * fieldsArray[11]), new CourseLectures( fieldsArray[14].toString(),
			 * (long) fieldsArray[13]));
			 */
			tx.commit();
		} finally {

			session.close();
		}
		return quizInformation;
	}

	@Override
	public QuizQuestions getQuizQuestionsEditableData(long id) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		QuizQuestions quizQuestions = new QuizQuestions();
		try {
			Criteria criteria = session.createCriteria(QuizQuestions.class);
			criteria.add(Restrictions.eq("id", id));

			criteria.createAlias("quizInformation", "quizInformation");

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("questionNumber"),
							"questionNumber")
					.add(Projections.property("questionText"), "questionText")
					.add(Projections.property("questionHint"), "questionHint")
					.add(Projections.property("correctAnswer"), "correctAnswer")
					.add(Projections.property("questionType"), "questionType")
					.add(Projections.property("grade"), "grade")
					.add(Projections.property("panalty"), "panalty")
					.add(Projections.property("imageLink"), "imageLink")
					.add(Projections.property("enabled"), "enabled")
					.add(Projections.property("quizInformation.id"),
							"quizInformationId")
					.add(Projections.property("quizInformation.quizName"),
							"quizInformationName")
					.add(Projections.property("id"), "id"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			if (list == null)
				logger.info("list is null !");
			else
				logger.info("list size: " + list.size());

			if (list == null || list.size() == 0)
				throw new EntityNotFoundException();

			Object[] fieldsArray = list.get(0);

			if (fieldsArray[0] != null)
				quizQuestions.setQuestionNumber((int) fieldsArray[0]);
			else
				quizQuestions.setQuestionNumber(0);

			if (fieldsArray[1] != null)
				quizQuestions.setQuestionText((String) fieldsArray[1]);
			else
				quizQuestions.setQuestionText("");

			if (fieldsArray[2] != null)
				quizQuestions.setQuestionHint((String) fieldsArray[2]);
			else
				quizQuestions.setQuestionHint("");

			if (fieldsArray[3] != null)
				quizQuestions.setCorrectAnswer((String) fieldsArray[3]);
			else
				quizQuestions.setCorrectAnswer("");

			if (fieldsArray[4] != null)
				quizQuestions.setQuestionType((int) fieldsArray[4]);
			else
				quizQuestions.setQuestionType(0);

			if (fieldsArray[5] != null)
				quizQuestions.setGrade((double) fieldsArray[5]);
			else
				quizQuestions.setGrade(0.0);

			if (fieldsArray[6] != null)
				quizQuestions.setPanalty((double) fieldsArray[6]);
			else
				quizQuestions.setPanalty(0.0);

			if (fieldsArray[7] != null)
				quizQuestions.setImageLink((String) fieldsArray[7]);
			else
				quizQuestions.setImageLink("");

			if (fieldsArray[8] != null)
				quizQuestions.setEnabled((boolean) fieldsArray[8]);
			else
				quizQuestions.setEnabled(false);

			QuizInformation quizInformation = new QuizInformation();

			if (fieldsArray[9] != null && fieldsArray[10] != null) {
				quizInformation.setId((long) fieldsArray[9]);
				quizInformation.setQuizName((String) fieldsArray[10]);
			} else {
				quizInformation.setId(0l);
				quizInformation.setQuizName("");
			}
			if (fieldsArray[11] != null)
				quizQuestions.setId((long) fieldsArray[11]);

			quizQuestions.setQuizInformation(quizInformation);
			logger.info("Size: " + fieldsArray.length);
			tx.commit();
		} finally {

			session.close();
		}
		return quizQuestions;
	}

	@Override
	public QuizQuestionsOptions getQuizQuestionsOptionsEditableData(long id)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		QuizQuestionsOptions quizQuestionsOptions = new QuizQuestionsOptions();
		try {
			Criteria criteria = session
					.createCriteria(QuizQuestionsOptions.class);
			criteria.add(Restrictions.eq("id", id));

			criteria.createAlias("quizInformation", "quizInformation");
			criteria.createAlias("quizQuestions", "quizQuestions");

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("quizInformation.id"),
							"quizInformationId")
					.add(Projections.property("quizInformation.quizName"),
							"quizInformationName")
					.add(Projections.property("quizQuestions.id"),
							"quizQuestionsId")
					.add(Projections.property("quizQuestions.questionText"),
							"quizQuestionsName"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			if (list == null)
				logger.info("list is null !");
			else
				logger.info("list size: " + list.size());

			if (list == null || list.size() == 0)
				throw new EntityNotFoundException();

			Object[] fieldsArray = list.get(0);

			QuizInformation quizInformation = new QuizInformation();
			QuizQuestions quizQuestions = new QuizQuestions();

			if (fieldsArray[0] != null && fieldsArray[1] != null) {
				quizInformation.setId((long) fieldsArray[0]);
				quizInformation.setQuizName((String) fieldsArray[1]);
			} else {
				quizInformation.setId(0l);
				quizInformation.setQuizName("");
			}

			if (fieldsArray[2] != null && fieldsArray[3] != null) {
				quizQuestions.setId((long) fieldsArray[2]);
				quizQuestions.setQuestionText((String) fieldsArray[3]);
			} else {
				quizQuestions.setId(0l);
				quizQuestions.setQuestionText("");
			}

			quizQuestionsOptions.setQuizInformation(quizInformation);
			quizQuestionsOptions.setQuizQuestions(quizQuestions);
			logger.info("Size: " + fieldsArray.length);
			tx.commit();
		} finally {

			session.close();
		}
		return quizQuestionsOptions;
	}
}
