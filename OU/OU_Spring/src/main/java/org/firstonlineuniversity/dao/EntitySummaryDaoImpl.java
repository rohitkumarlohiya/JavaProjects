package org.firstonlineuniversity.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.NotAProvider;
import org.firstonlineuniversity.models.courses.CourseContent;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseLectures;
import org.firstonlineuniversity.models.courses.CourseProviders;
import org.firstonlineuniversity.models.courses.CourseResources;
import org.firstonlineuniversity.models.courses.CourseSections;
import org.firstonlineuniversity.models.courses.Providers;
import org.firstonlineuniversity.models.courses.QuizInformation;
import org.firstonlineuniversity.models.courses.QuizQuestions;
import org.firstonlineuniversity.models.views.CourseContentsView;
import org.firstonlineuniversity.models.views.CourseLecturesQuiz;
import org.firstonlineuniversity.models.views.CourseLecturesView;
import org.firstonlineuniversity.models.views.CourseResourcesView;
import org.firstonlineuniversity.models.views.CourseSectionsQuiz;
import org.firstonlineuniversity.models.views.CourseSectionsView;
import org.firstonlineuniversity.security.oauth.SecurityUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EntitySummaryDaoImpl implements EntitySummaryDao {

	static final Logger logger = Logger.getLogger(EntitySummaryDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public List<Map<String, Object>> coursesSummaryList(long id)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Map<String, Object>> coursesList = new ArrayList<Map<String, Object>>();
		List<CourseProviders> courseProvidersList = new ArrayList<CourseProviders>();
		try {
			boolean isAdmin = true;
			Criteria criteria = session.createCriteria(CourseInformation.class);
			if (!SecurityUtils.hasRole("ROLE_ADMIN")) {
				isAdmin = false;
				CustomUser customUser = (CustomUser) SecurityContextHolder
						.getContext().getAuthentication().getPrincipal();

				Long userId = customUser.getId();

				Criteria providerCriteria = session
						.createCriteria(Providers.class);
				providerCriteria.createAlias("accounts", "accounts");

				List<Providers> providerList = providerCriteria.add(
						Restrictions.eq("accounts.id", userId)).list();

				if (providerList == null || providerList.size() < 1)
					throw new NotAProvider();

				long providerId = providerList.get(0).getId();

				Criteria courseProviderCriteria = session
						.createCriteria(CourseProviders.class);
				courseProviderCriteria.createAlias("providers", "providers");
				courseProvidersList = courseProviderCriteria.add(
						Restrictions.eq("providers.id", providerId)).list();
			}

			criteria.createAlias("courseCategory", "courseCategory");

			if (id != 0)
				criteria.add(Restrictions.eq("courseCategory.id", id));

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.distinct(Projections.property("id")))
					.add(Projections.property("courseName"), "courseName")
					.add(Projections.property("courseCategory.id"),
							"courseCategoryId")
					.add(Projections.property("courseCategory.categoryName"),
							"courseCategoryName")
					.add(Projections.property("courseKey"), "courseKey")
					.add(Projections.property("enabled"), "enabled")
					.add(Projections.property("enabled"), "enabled"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();
			logger.info("Sixe: " + list.size());
			List<Long> courseIds = new ArrayList<Long>();
			for (Object[] fieldsArray : list) {

				Map<String, Object> courseInformation = new HashMap<String, Object>();

				if (fieldsArray[0] != null)
					courseInformation.put("courseId", fieldsArray[0]);
				else
					courseInformation.put("courseId", "");

				if (fieldsArray[1] != null)
					courseInformation.put("courseName", fieldsArray[1]);
				else
					courseInformation.put("courseName", "");

				if (fieldsArray[2] != null)
					courseInformation.put("courseCategoryId", fieldsArray[2]);
				else
					courseInformation.put("courseCategoryId", "");

				if (fieldsArray[3] != null)
					courseInformation.put("courseCategoryName", fieldsArray[3]);
				else
					courseInformation.put("courseCategoryName", "");

				if (fieldsArray[4] != null)
					courseInformation.put("courseKey", fieldsArray[4]);
				else
					courseInformation.put("courseKey", "");

				if (fieldsArray[5] != null)
					courseInformation.put("enabled", fieldsArray[5]);
				else
					courseInformation.put("enabled", "");

				if (!isAdmin) {
					for (CourseProviders cp : courseProvidersList) {
						if (cp.getCourses() == (Long) courseInformation
								.get("courseId")
								&& !courseIds.contains((long) courseInformation
										.get("courseId"))) {
							courseIds.add((long) courseInformation
									.get("courseId"));
							coursesList.add(courseInformation);
						}
					}
				} else {
					if (courseIds.contains((long) courseInformation
							.get("courseId")))
						continue;
					courseIds.add((long) courseInformation.get("courseId"));
					coursesList.add(courseInformation);
				}
			}
			logger.info("Size: " + coursesList.size());
			tx.commit();
		} finally {

			session.close();
		}
		return coursesList;
	}

	@Override
	public List<Map<String, Object>> coursesSectionSummaryList(long courseId)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Map<String, Object>> sectionsList = new ArrayList<Map<String, Object>>();
		try {
			Criteria criteria = session.createCriteria(CourseSections.class);
			// criteria.add(Restrictions.eq("enabled", true));
			criteria.addOrder(Order.asc("sectionIndex"));
			logger.info("courseId: " + courseId);

			criteria.createAlias("course", "course");

			CourseInformation courseInformation = new CourseInformation();
			courseInformation.setId(courseId);

			criteria.add(Restrictions.eq("course", courseInformation));

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("course.id"), "courseId")
					.add(Projections.property("course.courseName"),
							"courseName")
					.add(Projections.property("id"), "sectionId")
					.add(Projections.property("sectionName"), "sectionName")
					.add(Projections.property("sectionIndex"), "sectionIndex")
					.add(Projections.property("sectionVisible"),
							"sectionVisible")
					.add(Projections.property("sectionKey"), "sectionKey")
					.add(Projections.property("enabled"), "enabled")

			);

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			for (Object[] fieldsArray : list) {

				Map<String, Object> sections = new HashMap<String, Object>();

				if (fieldsArray[0] != null)
					sections.put("courseId", fieldsArray[0]);
				else
					sections.put("courseId", "");

				if (fieldsArray[1] != null)
					sections.put("courseName", fieldsArray[1]);
				else
					sections.put("courseName", "");

				if (fieldsArray[2] != null)
					sections.put("sectionId", fieldsArray[2]);
				else
					sections.put("sectionId", "");

				if (fieldsArray[3] != null)
					sections.put("sectionName", fieldsArray[3]);
				else
					sections.put("sectionName", "");

				if (fieldsArray[4] != null)
					sections.put("sectionIndex", fieldsArray[4]);
				else
					sections.put("sectionIndex", "");

				if (fieldsArray[5] != null)
					sections.put("sectionVisible", fieldsArray[5]);
				else
					sections.put("sectionVisible", "");

				if (fieldsArray[6] != null)
					sections.put("sectionKey", fieldsArray[6]);
				else
					sections.put("sectionKey", "");

				if (fieldsArray[7] != null)
					sections.put("enabled", fieldsArray[7]);
				else
					sections.put("enabled", "");

				sectionsList.add(sections);
			}

			logger.info("Size: " + sectionsList.size());
			tx.commit();
		} finally {

			session.close();
		}
		return sectionsList;
	}

	@Override
	public List<Map<String, Object>> coursesLectureSummaryList(long courseId)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Map<String, Object>> coursesList = new ArrayList<Map<String, Object>>();
		try {
			Criteria criteria = session.createCriteria(CourseLectures.class);
			// criteria.add(Restrictions.eq("enabled", true));
			criteria.addOrder(Order.asc("lectureIndex"));
			criteria.createAlias("course", "course");
			criteria.createAlias("section", "section");

			if (courseId != 0)
				criteria.add(Restrictions.eq("course.id", courseId));

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("course.id"), "courseId")
					.add(Projections.property("course.courseName"),
							"courseName")
					.add(Projections.property("section.id"), "sectionId")
					.add(Projections.property("section.sectionName"),
							"sectionName")
					.add(Projections.property("id"), "lectureId")
					.add(Projections.property("lectureName"), "lectureName")
					.add(Projections.property("lectureIndex"), "lectureIndex")
					.add(Projections.property("lectureVisible"),
							"lectureVisible")
					.add(Projections.property("lectureKey"), "lectureKey")
					.add(Projections.property("enabled"), "enabled")

			);

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			for (Object[] fieldsArray : list) {

				Map<String, Object> courseInformation = new HashMap<String, Object>();

				if (fieldsArray[0] != null)
					courseInformation.put("courseId", fieldsArray[0]);
				else
					courseInformation.put("courseId", "");

				if (fieldsArray[1] != null)
					courseInformation.put("courseName", fieldsArray[1]);
				else
					courseInformation.put("courseName", "");

				if (fieldsArray[2] != null)
					courseInformation.put("sectionId", fieldsArray[2]);
				else
					courseInformation.put("sectionId", "");

				if (fieldsArray[3] != null)
					courseInformation.put("sectionName", fieldsArray[3]);
				else
					courseInformation.put("sectionName", "");

				if (fieldsArray[4] != null)
					courseInformation.put("lectureId", fieldsArray[4]);
				else
					courseInformation.put("lectureId", "");

				if (fieldsArray[5] != null)
					courseInformation.put("lectureName", fieldsArray[5]);
				else
					courseInformation.put("lectureName", "");

				if (fieldsArray[6] != null)
					courseInformation.put("lectureIndex", fieldsArray[6]);
				else
					courseInformation.put("lectureIndex", "");

				if (fieldsArray[7] != null)
					courseInformation.put("lectureVisible", fieldsArray[7]);
				else
					courseInformation.put("lectureVisible", "");

				if (fieldsArray[8] != null)
					courseInformation.put("lectureKey", fieldsArray[8]);
				else
					courseInformation.put("lectureKey", "");
				if (fieldsArray[9] != null)
					courseInformation.put("enabled", fieldsArray[9]);
				else
					courseInformation.put("enabled", "");

				coursesList.add(courseInformation);
			}

			logger.info("Size: " + coursesList.size());
			tx.commit();
		} finally {

			session.close();
		}
		return coursesList;
	}

	@Override
	public List<Map<String, Object>> coursesContentsSummaryList(long courseId)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Map<String, Object>> coursesList = new ArrayList<Map<String, Object>>();
		try {
			Criteria criteria = session.createCriteria(CourseContent.class);
			// criteria.add(Restrictions.eq("enabled", true));
			criteria.addOrder(Order.asc("fileIndex"));
			criteria.createAlias("course", "course");
			criteria.createAlias("lecture", "lecture");

			if (courseId != 0)
				criteria.add(Restrictions.eq("course.id", courseId));

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("course.id"), "courseId")
					.add(Projections.property("course.courseName"),
							"courseName")
					.add(Projections.property("lecture.id"), "lectureId")
					.add(Projections.property("lecture.lectureName"),
							"lectureName")
					.add(Projections.property("id"), "contentId")
					.add(Projections.property("fileName"), "contentName")
					.add(Projections.property("fileType"), "contentType")
					.add(Projections.property("fileDuration"),
							"contentDuration")
					.add(Projections.property("contentKey"), "contentKey")
					.add(Projections.property("enabled"), "enabled")
					.add(Projections.property("fileIndex"), "fileIndex")

			);

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			for (Object[] fieldsArray : list) {

				Map<String, Object> courseInformation = new HashMap<String, Object>();

				if (fieldsArray[0] != null)
					courseInformation.put("courseId", fieldsArray[0]);
				else
					courseInformation.put("courseId", "");

				if (fieldsArray[1] != null)
					courseInformation.put("courseName", fieldsArray[1]);
				else
					courseInformation.put("courseName", "");

				if (fieldsArray[2] != null)
					courseInformation.put("lectureId", fieldsArray[2]);
				else
					courseInformation.put("lectureId", "");

				if (fieldsArray[3] != null)
					courseInformation.put("lectureName", fieldsArray[3]);
				else
					courseInformation.put("lectureName", "");

				if (fieldsArray[4] != null)
					courseInformation.put("contentId", fieldsArray[4]);
				else
					courseInformation.put("contentId", "");

				if (fieldsArray[5] != null)
					courseInformation.put("contentName", fieldsArray[5]);
				else
					courseInformation.put("contentName", "");

				if (fieldsArray[6] != null)
					courseInformation.put("contentType", fieldsArray[6]);
				else
					courseInformation.put("contentType", "");

				if (fieldsArray[7] != null)
					courseInformation.put("contentDuration", fieldsArray[7]);
				else
					courseInformation.put("contentDuration", "");

				if (fieldsArray[8] != null)
					courseInformation.put("contentKey", fieldsArray[8]);
				else
					courseInformation.put("contentKey", "");
				if (fieldsArray[9] != null)
					courseInformation.put("enabled", fieldsArray[9]);
				else
					courseInformation.put("enabled", "");

				if (fieldsArray[10] != null)
					courseInformation.put("fileIndex", fieldsArray[10]);
				else
					courseInformation.put("fileIndex", "");

				coursesList.add(courseInformation);
			}

			logger.info("Size: " + coursesList.size());
			tx.commit();
		} finally {

			session.close();
		}
		return coursesList;
	}

	@Override
	public List<Map<String, Object>> coursesResourcesSummaryList(long courseId)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Map<String, Object>> coursesList = new ArrayList<Map<String, Object>>();
		try {
			Criteria criteria = session.createCriteria(CourseResources.class);
			// criteria.add(Restrictions.eq("enabled", true));
			criteria.addOrder(Order.asc("fileIndex"));
			criteria.createAlias("course", "course");
			criteria.createAlias("lecture", "lecture");

			if (courseId != 0)
				criteria.add(Restrictions.eq("course.id", courseId));

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("course.id"), "courseId")
					.add(Projections.property("course.courseName"),
							"courseName")
					.add(Projections.property("lecture.id"), "lectureId")
					.add(Projections.property("lecture.lectureName"),
							"lectureName")
					.add(Projections.property("id"), "resourceId")
					.add(Projections.property("fileName"), "resourceName")
					.add(Projections.property("fileType"), "resourceType")
					.add(Projections.property("resourceKey"), "resourceKey")
					.add(Projections.property("enabled"), "enabled")
					.add(Projections.property("fileIndex"), "fileIndex"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			for (Object[] fieldsArray : list) {

				Map<String, Object> courseInformation = new HashMap<String, Object>();

				if (fieldsArray[0] != null)
					courseInformation.put("courseId", fieldsArray[0]);
				else
					courseInformation.put("courseId", "");

				if (fieldsArray[1] != null)
					courseInformation.put("courseName", fieldsArray[1]);
				else
					courseInformation.put("courseName", "");

				if (fieldsArray[2] != null)
					courseInformation.put("lectureId", fieldsArray[2]);
				else
					courseInformation.put("lectureId", "");

				if (fieldsArray[3] != null)
					courseInformation.put("lectureName", fieldsArray[3]);
				else
					courseInformation.put("lectureName", "");

				if (fieldsArray[4] != null)
					courseInformation.put("resourceId", fieldsArray[4]);
				else
					courseInformation.put("resourceId", "");

				if (fieldsArray[5] != null)
					courseInformation.put("resourceName", fieldsArray[5]);
				else
					courseInformation.put("resourceName", "");

				if (fieldsArray[6] != null)
					courseInformation.put("resourceType", fieldsArray[6]);
				else
					courseInformation.put("resourceType", "");

				if (fieldsArray[7] != null)
					courseInformation.put("resourceKey", fieldsArray[7]);
				else
					courseInformation.put("resourceKey", "");
				if (fieldsArray[8] != null)
					courseInformation.put("enabled", fieldsArray[8]);
				else
					courseInformation.put("enabled", "");
				if (fieldsArray[9] != null)
					courseInformation.put("fileIndex", fieldsArray[9]);
				else
					courseInformation.put("fileIndex", "");
				coursesList.add(courseInformation);
			}

			logger.info("Size: " + coursesList.size());
			tx.commit();
		} finally {

			session.close();
		}
		return coursesList;
	}

	@Override
	public List<Map<String, Object>> coursesQuizSummaryList(long courseId)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Map<String, Object>> coursesList = new ArrayList<Map<String, Object>>();
		try {
			Criteria criteria = session.createCriteria(QuizInformation.class);
			criteria.createAlias("course", "course");
			criteria.createAlias("lecture", "lecture");
			criteria.createAlias("section", "section");

			if (courseId != 0)
				criteria.add(Restrictions.eq("course.id", courseId));

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("course.id"), "courseId")
					.add(Projections.property("course.courseName"),
							"courseName")
					.add(Projections.property("lecture.id"), "lectureId")
					.add(Projections.property("lecture.lectureName"),
							"lectureName")
					.add(Projections.property("section.id"), "sectionId")
					.add(Projections.property("section.sectionName"),
							"sectionName")
					.add(Projections.property("quizName"), "quizName")
					.add(Projections.property("activeFlag"), "activeFlag")
					.add(Projections.property("id"), "id")
					.add(Projections.property("enabled"), "enabled"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			for (Object[] fieldsArray : list) {

				Map<String, Object> courseInformation = new HashMap<String, Object>();

				if (fieldsArray[0] != null)
					courseInformation.put("courseId", fieldsArray[0]);
				else
					courseInformation.put("courseId", "");

				if (fieldsArray[1] != null)
					courseInformation.put("courseName", fieldsArray[1]);
				else
					courseInformation.put("courseName", "");

				if (fieldsArray[2] != null)
					courseInformation.put("lectureId", fieldsArray[2]);
				else
					courseInformation.put("lectureId", "");

				if (fieldsArray[3] != null)
					courseInformation.put("lectureName", fieldsArray[3]);
				else
					courseInformation.put("lectureName", "");

				if (fieldsArray[4] != null)
					courseInformation.put("sectionId", fieldsArray[4]);
				else
					courseInformation.put("sectionId", "");

				if (fieldsArray[5] != null)
					courseInformation.put("sectionName", fieldsArray[5]);
				else
					courseInformation.put("sectionName", "");

				if (fieldsArray[6] != null)
					courseInformation.put("quizName", fieldsArray[6]);
				else
					courseInformation.put("quizName", "");

				if (fieldsArray[7] != null)
					courseInformation.put("activeFlag", fieldsArray[7]);
				else
					courseInformation.put("activeFlag", "");
				if (fieldsArray[8] != null)
					courseInformation.put("id", fieldsArray[8]);
				else
					courseInformation.put("id", "");

				if (fieldsArray[9] != null)
					courseInformation.put("enabled", fieldsArray[9]);
				else
					courseInformation.put("enabled", "");

				coursesList.add(courseInformation);
			}

			logger.info("Size: " + coursesList.size());
			tx.commit();
		} finally {

			session.close();
		}
		return coursesList;
	}

	public List<Map<String, Object>> coursesQuizQuestionsSummaryList(long quizId)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Map<String, Object>> coursesList = new ArrayList<Map<String, Object>>();
		try {
			Criteria criteria = session.createCriteria(QuizQuestions.class);
			criteria.createAlias("quizInformation", "quizInformation");

			if (quizId != 0)
				criteria.add(Restrictions.eq("quizInformation.id", quizId));

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.property("quizInformation.id"),
							"quizInformationId")
					.add(Projections.property("quizInformation.quizName"),
							"quizName")
					.add(Projections.property("questionNumber"),
							"questionNumber")
					.add(Projections.property("questionType"), "questionType")
					.add(Projections.property("questionText"), "questionText")
					.add(Projections.property("enabled"), "enabled")
					.add(Projections.property("id"), "id"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			for (Object[] fieldsArray : list) {

				Map<String, Object> courseInformation = new HashMap<String, Object>();

				if (fieldsArray[0] != null)
					courseInformation.put("quizInformationId", fieldsArray[0]);
				else
					courseInformation.put("quizInformationId", "");

				if (fieldsArray[1] != null)
					courseInformation.put("quizName", fieldsArray[1]);
				else
					courseInformation.put("quizName", "");

				if (fieldsArray[2] != null)
					courseInformation.put("questionNumber", fieldsArray[2]);
				else
					courseInformation.put("questionNumber", "");

				if (fieldsArray[3] != null)
					courseInformation.put("questionType", fieldsArray[3]);
				else
					courseInformation.put("questionType", "");

				if (fieldsArray[4] != null)
					courseInformation.put("enabled", fieldsArray[4]);
				else
					courseInformation.put("enabled", "");

				if (fieldsArray[5] != null)
					courseInformation.put("quesId", fieldsArray[6]);
				else
					courseInformation.put("quesId", 0);

				coursesList.add(courseInformation);
			}

			logger.info("Size: " + coursesList.size());
			tx.commit();
		} finally {

			session.close();
		}
		return coursesList;
	}

	@Override
	public Map<String, Object> courseHeirarchy(long courseId) throws Exception {

		Map<String, Object> courseInformationMap = new HashMap<String, Object>();

		List<CourseSectionsView> courseSectionsViews = new ArrayList<CourseSectionsView>();
		List<CourseSectionsQuiz> courseSectionsQuizsViews = new ArrayList<CourseSectionsQuiz>();
		List<CourseLecturesView> courseLecturesViews = new ArrayList<CourseLecturesView>();
		List<CourseLecturesQuiz> courseLecturesQuizsViews = new ArrayList<CourseLecturesQuiz>();
		List<CourseContentsView> courseContentsViews = new ArrayList<CourseContentsView>();
		List<CourseResourcesView> courseResourcesViews = new ArrayList<CourseResourcesView>();

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			Criteria courseSectionsCriteria = session
					.createCriteria(CourseSectionsView.class);
			courseSectionsCriteria.add(Restrictions.eq("courseId", courseId));
			courseSectionsViews = courseSectionsCriteria.setCacheable(true)
					.list();

			Criteria courseSectionsQuizsCriteria = session
					.createCriteria(CourseSectionsQuiz.class);
			courseSectionsQuizsCriteria.add(Restrictions.eq("courseId",
					courseId));
			courseSectionsQuizsViews = courseSectionsQuizsCriteria
					.setCacheable(true).list();

			Criteria courseLecturesCriteria = session
					.createCriteria(CourseLecturesView.class);
			courseLecturesCriteria.add(Restrictions.eq("courseId", courseId));
			courseLecturesViews = courseLecturesCriteria.setCacheable(true)
					.list();

			Criteria courseLecturesQuizsCriteria = session
					.createCriteria(CourseLecturesQuiz.class);
			courseLecturesQuizsCriteria.add(Restrictions.eq("courseId",
					courseId));
			courseLecturesQuizsViews = courseLecturesQuizsCriteria
					.setCacheable(true).list();

			Criteria courseContentsCriteria = session
					.createCriteria(CourseContentsView.class);
			courseContentsCriteria.add(Restrictions.eq("courseId", courseId));
			courseContentsViews = courseContentsCriteria.setCacheable(true)
					.list();

			Criteria courseResourcesCriteria = session
					.createCriteria(CourseResourcesView.class);
			courseResourcesCriteria.add(Restrictions.eq("courseId", courseId));
			courseResourcesViews = courseResourcesCriteria.setCacheable(true)
					.list();
			tx.commit();
		} finally {

			session.close();
		}
		List<Object> courseSectionsList = new ArrayList<Object>();
		for (CourseSectionsView courseSectionsView : courseSectionsViews) {
			Map<String, Object> courseSectionsMap = new LinkedHashMap<String, Object>();
			courseSectionsMap.put("courseId", courseSectionsView.getCourseId());
			courseSectionsMap.put("courseName",
					courseSectionsView.getCourseName());
			courseSectionsMap.put("sectionId",
					courseSectionsView.getSectionId());
			courseSectionsMap.put("sectionName",
					courseSectionsView.getSectionName());
			courseSectionsMap.put("sectionIndex",
					courseSectionsView.getSectionIndex());

			long sectionId = courseSectionsView.getSectionId();
			List<Object> courseLecturesList = new ArrayList<Object>();
			for (CourseLecturesView courseLecturesView : courseLecturesViews) {
				if (courseLecturesView.getSectionId() == sectionId) {
					Map<String, Object> courseLecturesMap = new LinkedHashMap<String, Object>();
					courseLecturesMap.put("courseId",
							courseLecturesView.getCourseId());
					courseLecturesMap.put("courseName",
							courseLecturesView.getCourseName());
					courseLecturesMap.put("sectionId",
							courseLecturesView.getSectionId());
					courseLecturesMap.put("sectionName",
							courseLecturesView.getSectionName());
					courseLecturesMap.put("sectionIndex",
							courseLecturesView.getSectionIndex());
					courseLecturesMap.put("lectureId",
							courseLecturesView.getLectureId());
					courseLecturesMap.put("lectureName",
							courseLecturesView.getLectureName());
					courseLecturesMap.put("lectureIndex",
							courseLecturesView.getLectureIndex());
					courseLecturesList.add(courseLecturesMap);

					List<Object> courseContentList = new ArrayList<Object>();
					for (CourseContentsView courseContentsView : courseContentsViews) {
						if (courseContentsView.getLectureId() == courseLecturesView
								.getLectureId()) {
							Map<String, Object> courseContentsMap = new LinkedHashMap<String, Object>();
							courseContentsMap.put("courseId",
									courseContentsView.getCourseId());
							courseContentsMap.put("courseName",
									courseContentsView.getCourseName());
							courseContentsMap.put("sectionId",
									courseContentsView.getSectionId());
							courseContentsMap.put("sectionName",
									courseContentsView.getSectionName());
							courseContentsMap.put("sectionIndex",
									courseContentsView.getSectionIndex());
							courseContentsMap.put("lectureId",
									courseContentsView.getLectureId());
							courseContentsMap.put("lectureName",
									courseContentsView.getLectureName());
							courseContentsMap.put("lectureIndex",
									courseContentsView.getLectureIndex());

							courseContentsMap.put("contentId",
									courseContentsView.getContentId());
							courseContentsMap.put("contentName",
									courseContentsView.getContentName());
							courseContentsMap.put("contentIndex",
									courseContentsView.getContentIndex());
							courseContentsMap.put("download",
									courseContentsView.isDownload());
							courseContentList.add(courseContentsMap);
						}
					}
					courseLecturesMap.put("contentsList", courseContentList);
					List<Object> courseResourcesList = new ArrayList<Object>();
					for (CourseResourcesView courseResourcesView : courseResourcesViews) {
						if (courseResourcesView.getLectureId() == courseLecturesView
								.getLectureId()) {
							Map<String, Object> courseResourcesMap = new LinkedHashMap<String, Object>();
							courseResourcesMap.put("courseId",
									courseResourcesView.getCourseId());
							courseResourcesMap.put("courseName",
									courseResourcesView.getCourseName());
							courseResourcesMap.put("sectionId",
									courseResourcesView.getSectionId());
							courseResourcesMap.put("sectionName",
									courseResourcesView.getSectionName());
							courseResourcesMap.put("sectionIndex",
									courseResourcesView.getSectionIndex());
							courseResourcesMap.put("lectureId",
									courseResourcesView.getLectureId());
							courseResourcesMap.put("lectureName",
									courseResourcesView.getLectureName());
							courseResourcesMap.put("lectureIndex",
									courseResourcesView.getLectureIndex());

							courseResourcesMap.put("contentId",
									courseResourcesView.getResourceId());
							courseResourcesMap.put("contentName",
									courseResourcesView.getResourceName());
							courseResourcesMap.put("contentIndex",
									courseResourcesView.getResourceIndex());
							courseResourcesMap.put("download",
									courseResourcesView.isDownload());

							courseResourcesList.add(courseResourcesMap);
						}
					}
					courseLecturesMap.put("resourcesList", courseResourcesList);
					List<Object> courseLectureQuizList = new ArrayList<Object>();
					for (CourseLecturesQuiz courseLecturesQuiz : courseLecturesQuizsViews) {
						if (courseLecturesQuiz.getLectureId() == courseLecturesView
								.getLectureId()) {
							Map<String, Object> courseLecturesQuizMap = new LinkedHashMap<String, Object>();
							courseLecturesQuizMap.put("courseId",
									courseLecturesQuiz.getCourseId());
							courseLecturesQuizMap.put("courseName",
									courseLecturesQuiz.getCourseName());
							courseLecturesQuizMap.put("sectionId",
									courseLecturesQuiz.getSectionId());
							courseLecturesQuizMap.put("sectionName",
									courseLecturesQuiz.getSectionName());
							courseLecturesQuizMap.put("sectionIndex",
									courseLecturesQuiz.getSectionIndex());
							courseLecturesQuizMap.put("lectureId",
									courseLecturesQuiz.getLectureId());
							courseLecturesQuizMap.put("lectureName",
									courseLecturesQuiz.getLectureName());
							courseLecturesQuizMap.put("lectureIndex",
									courseLecturesQuiz.getLectureIndex());

							courseLecturesQuizMap.put("quiztId",
									courseLecturesQuiz.getQuizId());
							courseLecturesQuizMap.put("quizName",
									courseLecturesQuiz.getQuizName());
							courseLecturesQuizMap.put("quizIndex",
									courseLecturesQuiz.getQuizIndex());
							courseLectureQuizList.add(courseLecturesQuizMap);

						}
					}
					courseLecturesMap.put("lectureQuizList",
							courseLectureQuizList);
				}
				courseSectionsMap.put("lecturesList", courseLecturesList);
			}
			List<Object> courseSectionQuizList = new ArrayList<Object>();
			for (CourseSectionsQuiz courseSectionsQuiz : courseSectionsQuizsViews) {
				if (courseSectionsQuiz.getSectionId() == courseSectionsView
						.getSectionId()) {
					Map<String, Object> courseSectionQuizMap = new LinkedHashMap<String, Object>();
					courseSectionQuizMap.put("courseId",
							courseSectionsQuiz.getCourseId());
					courseSectionQuizMap.put("courseName",
							courseSectionsQuiz.getCourseName());
					courseSectionQuizMap.put("sectionId",
							courseSectionsQuiz.getSectionId());
					courseSectionQuizMap.put("sectionName",
							courseSectionsQuiz.getSectionName());
					courseSectionQuizMap.put("sectionIndex",
							courseSectionsQuiz.getSectionIndex());

					courseSectionQuizMap.put("contentId",
							courseSectionsQuiz.getQuizId());
					courseSectionQuizMap.put("contentName",
							courseSectionsQuiz.getQuizName());
					courseSectionQuizMap.put("contentIndex",
							courseSectionsQuiz.getQuizIndex());

					courseSectionQuizList.add(courseSectionQuizMap);
				}
			}
			courseSectionsMap.put("sectionsQuizList", courseSectionQuizList);
			courseSectionsList.add(courseSectionsMap);
		}
		courseInformationMap.put("courseSectionsList", courseSectionsList);
		return courseInformationMap;
	}

	@Override
	public Map<String, Object> courseHeirarchyFull(long courseId)
			throws Exception {

		Map<String, Object> courseInformationMap = new HashMap<String, Object>();

		List<CourseSectionsView> courseSectionsViews = new ArrayList<CourseSectionsView>();
		List<CourseSectionsQuiz> courseSectionsQuizsViews = new ArrayList<CourseSectionsQuiz>();
		List<CourseLecturesView> courseLecturesViews = new ArrayList<CourseLecturesView>();
		List<CourseLecturesQuiz> courseLecturesQuizsViews = new ArrayList<CourseLecturesQuiz>();
		List<CourseContentsView> courseContentsViews = new ArrayList<CourseContentsView>();
		List<CourseResourcesView> courseResourcesViews = new ArrayList<CourseResourcesView>();

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			Criteria courseSectionsCriteria = session.createCriteria(
					CourseSectionsView.class).addOrder(
					Order.asc("sectionIndex"));

			courseSectionsCriteria.add(Restrictions.eq("courseId", courseId));
			courseSectionsViews = courseSectionsCriteria.setCacheable(true)
					.list();

			Criteria courseSectionsQuizsCriteria = session.createCriteria(
					CourseSectionsQuiz.class).addOrder(Order.asc("quizIndex"));

			courseSectionsQuizsCriteria.add(Restrictions.eq("courseId",
					courseId));
			courseSectionsQuizsViews = courseSectionsQuizsCriteria
					.setCacheable(true).list();

			Criteria courseLecturesCriteria = session.createCriteria(
					CourseLecturesView.class).addOrder(
					Order.asc("lectureIndex"));
			courseLecturesCriteria.add(Restrictions.eq("courseId", courseId));
			courseLecturesViews = courseLecturesCriteria.setCacheable(true)
					.list();

			Criteria courseLecturesQuizsCriteria = session
					.createCriteria(CourseLecturesQuiz.class);
			courseLecturesQuizsCriteria.add(
					Restrictions.eq("courseId", courseId)).addOrder(
					Order.asc("quizIndex"));
			courseLecturesQuizsViews = courseLecturesQuizsCriteria
					.setCacheable(true).list();

			Criteria courseContentsCriteria = session.createCriteria(
					CourseContentsView.class).addOrder(
					Order.asc("contentIndex"));
			courseContentsCriteria.add(Restrictions.eq("courseId", courseId));
			courseContentsViews = courseContentsCriteria.setCacheable(true)
					.list();

			Criteria courseResourcesCriteria = session.createCriteria(
					CourseResourcesView.class).addOrder(
					Order.asc("resourceIndex"));
			courseResourcesCriteria.add(Restrictions.eq("courseId", courseId));
			courseResourcesViews = courseResourcesCriteria.setCacheable(true)
					.list();
			tx.commit();
		} finally {
			session.close();
		}
		List<Object> courseSectionsList = new ArrayList<Object>();
		for (CourseSectionsView courseSectionsView : courseSectionsViews) {
			Map<String, Object> courseSectionsMap = new LinkedHashMap<String, Object>();
			courseSectionsMap.put("courseId", courseSectionsView.getCourseId());
			courseSectionsMap.put("courseName",
					courseSectionsView.getCourseName());
			courseSectionsMap.put("sectionId",
					courseSectionsView.getSectionId());
			courseSectionsMap.put("sectionName",
					courseSectionsView.getSectionName());
			courseSectionsMap.put("sectionIndex",
					courseSectionsView.getSectionIndex());

			courseSectionsMap.put("sectionDescription",
					courseSectionsView.getSectionDescription());
			courseSectionsMap.put("sectionPurpose",
					courseSectionsView.getSectionPurpose());
			courseSectionsMap.put("sectionVisible",
					courseSectionsView.isSectionVisible());

			long sectionId = courseSectionsView.getSectionId();
			List<Object> courseLecturesList = new ArrayList<Object>();
			for (CourseLecturesView courseLecturesView : courseLecturesViews) {
				if (courseLecturesView.getSectionId() == sectionId) {
					Map<String, Object> courseLecturesMap = new LinkedHashMap<String, Object>();
					courseLecturesMap.put("courseId",
							courseLecturesView.getCourseId());
					courseLecturesMap.put("courseName",
							courseLecturesView.getCourseName());
					courseLecturesMap.put("sectionId",
							courseLecturesView.getSectionId());
					courseLecturesMap.put("sectionName",
							courseLecturesView.getSectionName());
					courseLecturesMap.put("sectionIndex",
							courseLecturesView.getSectionIndex());
					courseLecturesMap.put("lectureId",
							courseLecturesView.getLectureId());
					courseLecturesMap.put("lectureName",
							courseLecturesView.getLectureName());
					courseLecturesMap.put("lectureIndex",
							courseLecturesView.getLectureIndex());

					courseLecturesMap.put("lectureDescription",
							courseLecturesView.getLectureDescription());
					courseLecturesMap.put("lectureVisible",
							courseLecturesView.isLectureVisible());
					courseLecturesMap.put("lectureKey",
							courseLecturesView.getLectureKey());

					courseLecturesList.add(courseLecturesMap);

					List<Object> courseContentList = new ArrayList<Object>();
					for (CourseContentsView courseContentsView : courseContentsViews) {
						if (courseContentsView.getLectureId() == courseLecturesView
								.getLectureId()) {
							Map<String, Object> courseContentsMap = new LinkedHashMap<String, Object>();
							courseContentsMap.put("courseId",
									courseContentsView.getCourseId());
							courseContentsMap.put("courseName",
									courseContentsView.getCourseName());
							courseContentsMap.put("sectionId",
									courseContentsView.getSectionId());
							courseContentsMap.put("sectionName",
									courseContentsView.getSectionName());
							courseContentsMap.put("sectionIndex",
									courseContentsView.getSectionIndex());
							courseContentsMap.put("lectureId",
									courseContentsView.getLectureId());
							courseContentsMap.put("lectureName",
									courseContentsView.getLectureName());
							courseContentsMap.put("lectureIndex",
									courseContentsView.getLectureIndex());

							courseContentsMap.put("contentId",
									courseContentsView.getContentId());
							courseContentsMap.put("contentName",
									courseContentsView.getContentName());
							courseContentsMap.put("contentIndex",
									courseContentsView.getContentIndex());

							courseContentsMap.put("contentType",
									courseContentsView.getContentType());
							courseContentsMap.put("contentDescription",
									courseContentsView.getContentDescription());
							courseContentsMap.put("fileName",
									courseContentsView.getFileName());
							courseContentsMap.put("filePath",
									courseContentsView.getFilePath());
							courseContentsMap.put("fileFormat",
									courseContentsView.getFileFormat());
							courseContentsMap.put("fileSize",
									courseContentsView.getFileSize());
							courseContentsMap.put("paid",
									courseContentsView.isPaid());
							courseContentsMap.put("download",
									courseContentsView.isDownload());

							courseContentList.add(courseContentsMap);
						}
					}
					courseLecturesMap.put("contentsList", courseContentList);
					List<Object> courseResourcesList = new ArrayList<Object>();
					for (CourseResourcesView courseResourcesView : courseResourcesViews) {
						if (courseResourcesView.getLectureId() == courseLecturesView
								.getLectureId()) {
							Map<String, Object> courseResourcesMap = new LinkedHashMap<String, Object>();
							courseResourcesMap.put("courseId",
									courseResourcesView.getCourseId());
							courseResourcesMap.put("courseName",
									courseResourcesView.getCourseName());
							courseResourcesMap.put("sectionId",
									courseResourcesView.getSectionId());
							courseResourcesMap.put("sectionName",
									courseResourcesView.getSectionName());
							courseResourcesMap.put("sectionIndex",
									courseResourcesView.getSectionIndex());
							courseResourcesMap.put("lectureId",
									courseResourcesView.getLectureId());
							courseResourcesMap.put("lectureName",
									courseResourcesView.getLectureName());
							courseResourcesMap.put("lectureIndex",
									courseResourcesView.getLectureIndex());

							courseResourcesMap.put("resourceId",
									courseResourcesView.getResourceId());
							courseResourcesMap.put("resourceName",
									courseResourcesView.getResourceName());
							courseResourcesMap.put("resourceIndex",
									courseResourcesView.getResourceIndex());

							courseResourcesMap.put("resourceType",
									courseResourcesView.getResourceType());
							courseResourcesMap.put("resourceDescription",
									courseResourcesView
											.getResourceDescription());
							courseResourcesMap.put("fileName",
									courseResourcesView.getFileName());
							courseResourcesMap.put("filePath",
									courseResourcesView.getFilePath());
							courseResourcesMap.put("fileFormat",
									courseResourcesView.getFileFormat());
							courseResourcesMap.put("fileSize",
									courseResourcesView.getFileSize());
							courseResourcesMap.put("paid",
									courseResourcesView.isPaid());
							courseResourcesMap.put("promotional",
									courseResourcesView.isPromotional());
							courseResourcesMap.put("download",
									courseResourcesView.isDownload());
							courseResourcesList.add(courseResourcesMap);
						}
					}
					courseLecturesMap.put("resourcesList", courseResourcesList);
					List<Object> courseLectureQuizList = new ArrayList<Object>();
					for (CourseLecturesQuiz courseLecturesQuiz : courseLecturesQuizsViews) {
						if (courseLecturesQuiz.getLectureId() == courseLecturesView
								.getLectureId()) {
							Map<String, Object> courseLecturesQuizMap = new LinkedHashMap<String, Object>();
							courseLecturesQuizMap.put("courseId",
									courseLecturesQuiz.getCourseId());
							courseLecturesQuizMap.put("courseName",
									courseLecturesQuiz.getCourseName());
							courseLecturesQuizMap.put("sectionId",
									courseLecturesQuiz.getSectionId());
							courseLecturesQuizMap.put("sectionName",
									courseLecturesQuiz.getSectionName());
							courseLecturesQuizMap.put("sectionIndex",
									courseLecturesQuiz.getSectionIndex());
							courseLecturesQuizMap.put("lectureId",
									courseLecturesQuiz.getLectureId());
							courseLecturesQuizMap.put("lectureName",
									courseLecturesQuiz.getLectureName());
							courseLecturesQuizMap.put("lectureIndex",
									courseLecturesQuiz.getLectureIndex());

							courseLecturesQuizMap.put("quiztId",
									courseLecturesQuiz.getQuizId());
							courseLecturesQuizMap.put("quizName",
									courseLecturesQuiz.getQuizName());
							courseLecturesQuizMap.put("quizIndex",
									courseLecturesQuiz.getQuizIndex());
							courseLectureQuizList.add(courseLecturesQuizMap);

						}
					}
					courseLecturesMap.put("lectureQuizList",
							courseLectureQuizList);
				}
				courseSectionsMap.put("lecturesList", courseLecturesList);
			}
			List<Object> courseSectionQuizList = new ArrayList<Object>();
			for (CourseSectionsQuiz courseSectionsQuiz : courseSectionsQuizsViews) {
				if (courseSectionsQuiz.getSectionId() == courseSectionsView
						.getSectionId()) {
					Map<String, Object> courseSectionQuizMap = new LinkedHashMap<String, Object>();
					courseSectionQuizMap.put("courseId",
							courseSectionsQuiz.getCourseId());
					courseSectionQuizMap.put("courseName",
							courseSectionsQuiz.getCourseName());
					courseSectionQuizMap.put("sectionId",
							courseSectionsQuiz.getSectionId());
					courseSectionQuizMap.put("sectionName",
							courseSectionsQuiz.getSectionName());
					courseSectionQuizMap.put("sectionIndex",
							courseSectionsQuiz.getSectionIndex());

					courseSectionQuizMap.put("contentId",
							courseSectionsQuiz.getQuizId());
					courseSectionQuizMap.put("contentName",
							courseSectionsQuiz.getQuizName());
					courseSectionQuizMap.put("contentIndex",
							courseSectionsQuiz.getQuizIndex());

					courseSectionQuizList.add(courseSectionQuizMap);
				}
			}
			courseSectionsMap.put("sectionsQuizList", courseSectionQuizList);
			courseSectionsList.add(courseSectionsMap);
		}
		courseInformationMap.put("courseSectionsList", courseSectionsList);
		return courseInformationMap;
	}
}
