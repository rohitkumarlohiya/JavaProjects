package org.firstonlineuniversity.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.custom.CourseWrapper;
import org.firstonlineuniversity.exceptions.customexceptions.NotAProvider;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseCategory;
import org.firstonlineuniversity.models.courses.CourseContent;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CoursePrices;
import org.firstonlineuniversity.models.courses.CourseProviders;
import org.firstonlineuniversity.models.courses.CourseResources;
import org.firstonlineuniversity.models.courses.CourseStatuses;
import org.firstonlineuniversity.models.courses.CoursesTags;
import org.firstonlineuniversity.models.courses.Providers;
import org.firstonlineuniversity.models.courses.QuickCodes;
import org.firstonlineuniversity.models.courses.QuizAttemptDetails;
import org.firstonlineuniversity.models.courses.QuizAttempts;
import org.firstonlineuniversity.models.courses.QuizHistory;
import org.firstonlineuniversity.models.courses.QuizInformation;
import org.firstonlineuniversity.models.courses.QuizQuestions;
import org.firstonlineuniversity.models.courses.QuizQuestionsOptions;
import org.firstonlineuniversity.models.courses.Tags;
import org.firstonlineuniversity.models.courses.UserContent;
import org.firstonlineuniversity.models.courses.UserProgramCources;
import org.firstonlineuniversity.models.courses.UserPrograms;
import org.firstonlineuniversity.models.enrollements.CoursesEnrollements;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.login.UserRole;
import org.firstonlineuniversity.models.payment.PaymentTransactions;
import org.firstonlineuniversity.models.profiles.Organization;
import org.firstonlineuniversity.models.views.CourseCatalogView;
import org.firstonlineuniversity.models.views.ProviderSummaryView;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDaoImpl implements CustomDao {

	static final Logger logger = Logger.getLogger(CustomDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public CourseResources getCourseResourses(long id) {
		logger.info("Identifier: " + id);
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		CourseResources courseResources = null;
		try {
			courseResources = (CourseResources) session.get(
					CourseResources.class, new Long(id));
			courseResources.getCourse();
			tx.commit();
		} finally {

			session.close();
		}

		return courseResources;
	}

	@Override
	public AbstractEntity getEdutableData(String entityName, long id) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		AbstractEntity abstractEntity = null;
		try {
			logger.info("entityName: " + entityName + " Id: " + id);

			String hql = "FROM " + entityName + " E WHERE E.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);

			abstractEntity = (AbstractEntity) query.list().get(0);
			tx.commit();
		} finally {

			session.close();
		}
		return abstractEntity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPrograms> getEdutabdleData(Long userId, Long programId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<UserPrograms> list = null;
		try {
			Criteria userProgramCriteriaT = session
					.createCriteria(UserPrograms.class);
			userProgramCriteriaT
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			userProgramCriteriaT.createAlias("accounts", "a");
			userProgramCriteriaT.add(Restrictions.eq("a.id", userId));

			if (programId != null)
				userProgramCriteriaT.add(Restrictions.eq("id", programId));
			list = userProgramCriteriaT.setCacheable(true).list();

			for (UserPrograms abstractEntity : list) {

				Criteria userProgramCriteria = session
						.createCriteria(UserProgramCources.class);
				userProgramCriteria.createAlias("userPrograms", "up");
				userProgramCriteria.add(Restrictions.eq("up.id",
						abstractEntity.getId()));
				List<UserProgramCources> cources = userProgramCriteria
						.setCacheable(true).list();

				Set<UserProgramCources> finalSet = new HashSet<UserProgramCources>();

				finalSet.addAll(cources);
				abstractEntity.setUserProgramCourses(finalSet);
			}
			tx.commit();
		} finally {

			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserContent> getUserDataByUserIdAndContentId(Long userId,
			Long contentId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<UserContent> list = null;
		try {
			Criteria userProgramCriteriaT = session
					.createCriteria(UserContent.class);
			userProgramCriteriaT
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			userProgramCriteriaT.add(Restrictions.eq("accountId", userId));
			userProgramCriteriaT.add(Restrictions.eq("contentId", contentId));

			list = userProgramCriteriaT.setCacheable(true).list();
			tx.commit();
		} finally {

			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserContent> getUserContentByUserIdAndCourseId(Long userId,
			Long courseId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<UserContent> list = null;
		try {
			Criteria userProgramCriteriaT = session
					.createCriteria(UserContent.class);
			userProgramCriteriaT
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			userProgramCriteriaT.add(Restrictions.eq("accountId", userId));
			userProgramCriteriaT.add(Restrictions.eq("courseId", courseId));
			userProgramCriteriaT.addOrder(Order.desc("UD"));

			list = userProgramCriteriaT.setCacheable(true).list();
			tx.commit();
		} finally {

			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCourseContentCountByCourseId(Long courseId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<CourseContent> list = null;
		try {
			Criteria userProgramCriteriaT = session
					.createCriteria(CourseContent.class);
			userProgramCriteriaT
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			userProgramCriteriaT.createAlias("course", "c");
			userProgramCriteriaT.add(Restrictions.eq("c.id", courseId));

			list = userProgramCriteriaT.setCacheable(true).list();
			tx.commit();
		} finally {

			session.close();
		}
		return list.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Providers getProviderByUserId(long userId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Providers> list = null;
		try {
			Criteria userProgramCriteriaT = session
					.createCriteria(Providers.class);
			userProgramCriteriaT
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			userProgramCriteriaT.createAlias("accounts", "a");
			userProgramCriteriaT.add(Restrictions.eq("a.id", userId));

			list = userProgramCriteriaT.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}
		return (list == null || list.size() == 0) ? null : list.get(0);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getFullCourseSingle(long courseId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Map<String, Object> courseDescriptionMap = new HashMap<String, Object>();
		try {
			// course information
			Criteria courseCriteria = session
					.createCriteria(CourseInformation.class);

			CourseInformation courseInformation = (CourseInformation) courseCriteria
					.add(Restrictions.eq("id", courseId)).setCacheable(true)
					.list().get(0);
			Hibernate.initialize(courseInformation.getPrices());
			courseDescriptionMap.put("courseInformation", courseInformation);

			// course category
			courseDescriptionMap.put("courseCategory",
					courseInformation.getCourseCategory());

			// course provider criteria
			Criteria courseProviderCriteria = session
					.createCriteria(CourseProviders.class);

			List<CourseProviders> courseProvidersList = courseProviderCriteria
					.add(Restrictions.eq("courses", courseId))
					.setCacheable(true).list();
			List<Providers> courseProvidersListFinal = new ArrayList<Providers>();
			if (courseProvidersList != null && courseProvidersList.size() > 0) {
				List<Long> uniquIds = new ArrayList<Long>();
				for (CourseProviders courseProviders : courseProvidersList) {

					Providers providersDb = courseProviders.getProviders();
					if (uniquIds.contains(providersDb.getId()))
						continue;
					Providers providers = new Providers(null,
							providersDb.getProviderType(),
							providersDb.getProviderName(),
							providersDb.getExperience(),
							providersDb.getIntroduction(),
							providersDb.getContactId(),
							providersDb.getProviderPhotoLink(),
							providersDb.getDesignation(),
							providersDb.getDepartment(),
							providersDb.isEnabled(), null, null, null, 0);
					providers.setId(providersDb.getId());
					uniquIds.add(providersDb.getId());
					courseProvidersListFinal.add(providers);
				}
				courseDescriptionMap.put("providers", courseProvidersListFinal);
			}

			// course price
			Criteria courseProceCriteria = session
					.createCriteria(CoursePrices.class);
			courseProceCriteria.createAlias("courses", "c");
			List<CoursePrices> coursePricesList = courseProceCriteria
					.add(Restrictions.eq("c.id", courseId)).setCacheable(true)
					.list();
			if (coursePricesList != null && coursePricesList.size() > 0) {

				for (CoursePrices coursePricesDB : courseInformation
						.getPrices()) {
					if (coursePricesDB.isEnabled()) {
						CoursePrices coursePrices = new CoursePrices(
								coursePricesDB.getPriceType(),
								coursePricesDB.getCurrency(),
								coursePricesDB.getPrice(),
								coursePricesDB.getDiscountType(),
								coursePricesDB.getDiscount(),
								coursePricesDB.getDiscountPercent(),
								coursePricesDB.getStartDate(),
								coursePricesDB.getEndDate(), null, null, null);
						courseDescriptionMap.put("coursePrices", coursePrices);
					}
				}
			}

			// organization
			Criteria courseOrgCriteria = session
					.createCriteria(Organization.class);
			List<Organization> courseOrgList = courseOrgCriteria
					.add(Restrictions.eq("id",
							courseInformation.getOrganizationId()))
					.setCacheable(true).list();
			if (courseOrgList != null && courseOrgList.size() > 0) {

				Organization organizationDB = courseOrgList.get(0);
				Organization organization = new Organization(
						organizationDB.getOrganizationType(),
						organizationDB.getOrganizationName(),
						organizationDB.getOrganizationDescription(),
						organizationDB.getPhotoLink(), null, null,
						organizationDB.getCountryCode(),
						organizationDB.isPartnerFlag(),
						organizationDB.getLogoLink());
				organization.setId(organizationDB.getId());

				courseDescriptionMap.put("organization", organization);
			}

			tx.commit();
			courseInformation.getPrices();
		} finally {
			session.close();
		}
		return courseDescriptionMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoursesEnrollements> getEnrolledCoursesByUserId(long userId) {

		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		List<CoursesEnrollements> coursesEnrollements = null;
		try {
			Criteria coursesEnrollementsCriteria = session
					.createCriteria(CoursesEnrollements.class);

			coursesEnrollementsCriteria.createAlias("accounts", "a");
			coursesEnrollementsCriteria.add(Restrictions.eq("a.id", userId));
			coursesEnrollementsCriteria.setResultTransformer(
					Criteria.DISTINCT_ROOT_ENTITY).addOrder(Order.desc("UD"));
			coursesEnrollements = coursesEnrollementsCriteria
					.setCacheable(true).list();

			tx.commit();
		} finally {

			session.close();
		}

		return coursesEnrollements;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseCategory> getCategoryList() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<CourseCategory> list = null;
		try {
			list = session.createCriteria(CourseCategory.class)
					.setCacheable(true).list();
			for (CourseCategory category : list) {
				Hibernate.initialize(category.getCourseInformations());
			}
			tx.commit();
			for (CourseCategory category : list) {
				category.setCourseCount(category.getCourseInformations().size());
			}
		} finally {

			session.close();
		}
		return list;
	}

	@Override
	public CourseCatalogView getCourseCatalogView(Long courseId)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		CourseCatalogView courseCatalogView = null;
		try {
			courseCatalogView = (CourseCatalogView) session
					.createCriteria(CourseCatalogView.class)
					.add(Restrictions.eqOrIsNull("courseId", courseId)).list()
					.get(0);
			tx.commit();
		} finally {
			session.close();
		}
		return courseCatalogView;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tags> getListByLike(String entityName, String[] like,
			String columnName) throws Exception {
		List<Tags> items = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			/*
			 * String str = makeCommaSeparated(like); Query query =
			 * session.createQuery("FROM " + entityName + " item WHERE item." +
			 * columnName + " IN (:ids)"); query.setParameter("ids", str);
			 * logger.info("query : " + query); items = query.list();
			 */

			Criteria criteria = session.createCriteria(Tags.class);
			criteria.add(Restrictions.in("name", Arrays.asList(like)));

			items = criteria.list();
			tx.commit();
		} finally {
			session.close();
		}
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseWrapper> getCourseListByProvider(long accountId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<CourseWrapper> courseInformationList = new ArrayList<CourseWrapper>();
		try {
			Criteria providerCriteria = session.createCriteria(Providers.class);
			providerCriteria.createAlias("accounts", "accounts");

			List<Providers> providerList = providerCriteria.add(
					Restrictions.eq("accounts.id", accountId)).list();

			if (providerList == null || providerList.size() < 1)
				throw new NotAProvider();

			long providerId = providerList.get(0).getId();

			Criteria courseProviderCriteria = session
					.createCriteria(CourseProviders.class);
			courseProviderCriteria.createAlias("providers", "providers");
			List<CourseProviders> courseProvidersList = courseProviderCriteria
					.add(Restrictions.eq("providers.id", providerId)).list();
			for (CourseProviders courseProviders : courseProvidersList) {
				long courseId = courseProviders.getCourses();
				List<CourseInformation> ciList = session
						.createCriteria(CourseInformation.class)
						.add(Restrictions.eq("id", courseId)).list();
				if (ciList != null && ciList.size() > 0)
					courseInformationList.add(new CourseWrapper(ciList.get(0)
							.getId(), ciList.get(0).getCourseName()));
			}
			tx.commit();
		} finally {
			session.close();
		}
		return courseInformationList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tags> tagsByCourseId(long courseId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<CoursesTags> list = null;
		List<Tags> tagsList = new ArrayList<Tags>();
		try {
			String hql = "FROM CoursesTags E WHERE E.courseId = " + courseId;
			Query query = session.createQuery(hql);
			list = query.list();
			if (list != null && list.size() > 0)
				for (CoursesTags ct : list) {
					String hqls = "FROM Tags E WHERE E.id = " + ct.getTagId();
					Query querys = session.createQuery(hqls);
					if (querys.list() != null || querys.list().size() > 0) {
						try {
							tagsList.add((Tags) querys.list().get(0));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			tx.commit();
		} finally {

			session.close();
		}
		return tagsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CoursesTags courseTagsByCourseAndTagId(long courseId, long tagId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<CoursesTags> list = null;
		CoursesTags coursesTags = null;
		try {
			Criteria courseTagCriteria = session
					.createCriteria(CoursesTags.class);

			courseTagCriteria.add(Restrictions.eq("courseId", courseId));
			courseTagCriteria.add(Restrictions.eq("tagId", tagId));

			list = courseTagCriteria.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}
		if (list != null && list.size() > 0)
			coursesTags = list.get(0);

		return coursesTags;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuizQuestionsOptions> optionsListByQuestionId(Long questionId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<QuizQuestionsOptions> list = null;

		try {
			Criteria optionsCriteria = session
					.createCriteria(QuizQuestionsOptions.class);
			optionsCriteria.createAlias("quizQuestions", "qq");
			list = optionsCriteria.add(Restrictions.eq("qq.id", questionId))
					.list();
			tx.commit();
		} finally {

			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuizQuestions> questionsListByQuizId(Long quizId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<QuizQuestions> list = null;

		try {
			Criteria optionsCriteria = session
					.createCriteria(QuizQuestions.class);
			optionsCriteria.createAlias("quizInformation", "qi");
			list = optionsCriteria.add(Restrictions.eq("qi.id", quizId))
					.setCacheable(true).list();
			tx.commit();
		} finally {

			session.close();
		}
		return list;
	}

	@Override
	public boolean deleteQuiz(Long quizId) {

		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		QuizInformation quizInformation = null;
		try {
			quizInformation = (QuizInformation) session.get(
					QuizInformation.class, quizId);
			session.delete(quizInformation);
			tx.commit();
		} finally {

			session.close();
		}

		return true;
	}

	@Override
	public boolean existsCourseKey(String courseKey) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		AbstractEntity abstractEntity = null;
		boolean flag = false;
		try {
			Criteria criteria = session.createCriteria(CourseInformation.class);
			criteria.add(Restrictions.eq("courseKey", courseKey.trim()));
			abstractEntity = (AbstractEntity) criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list().get(0);
			tx.commit();
		} catch (Exception e) {
			logger.info("Course key not found ", e);
		} finally {
			session.close();
		}
		if (abstractEntity != null)
			flag = true;
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AbstractEntity> courseStatuses(Long courseId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<AbstractEntity> abstractEntities = new ArrayList<AbstractEntity>();

		try {
			Criteria criteria = session.createCriteria(CourseStatuses.class);
			criteria.createAlias("course", "course");
			criteria.add(Restrictions.eq("course.id", courseId));
			abstractEntities = criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} catch (Exception e) {
			logger.info("Course status not found ", e);
		} finally {
			session.close();
		}
		return abstractEntities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuizAttempts> quizAttemptList(Long accountId, Long quizId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<QuizAttempts> quizAttempts = new ArrayList<QuizAttempts>();

		try {
			Criteria criteria = session.createCriteria(QuizAttempts.class);
			criteria.add(Restrictions.eq("quizId", quizId));
			criteria.add(Restrictions.eq("accountId", accountId));
			quizAttempts = criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} catch (Exception e) {
			logger.info("Quiz Attempt not found ", e);
		} finally {
			session.close();
		}
		return quizAttempts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuizHistory> quizHistoryList(Long accountId, Long courseId,
			Long quizId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<QuizHistory> quizHistories = new ArrayList<QuizHistory>();

		try {
			Criteria criteria = session.createCriteria(QuizHistory.class);
			criteria.add(Restrictions.eq("accountId", accountId));

			if (quizId != null)
				criteria.add(Restrictions.eq("quizId", quizId));

			if (courseId != null)
				criteria.add(Restrictions.eq("courseId", courseId));

			quizHistories = criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} catch (Exception e) {
			logger.info("Quiz History not found ", e);
		} finally {
			session.close();
		}
		return quizHistories;
	}

	@Override
	public List<Providers> providerListByuAccountId(Long accountId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Providers> providers = new ArrayList<Providers>();

		try {
			Criteria criteria = session.createCriteria(Providers.class);
			criteria.createAlias("accounts", "ac");
			criteria.add(Restrictions.eq("ac.id", accountId));

			providers = criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} catch (Exception e) {
			logger.info("Providers not found ", e);
		} finally {
			session.close();
		}
		return providers;
	}

	@Override
	public List<ProviderSummaryView> providersView() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<ProviderSummaryView> providerSummaryViews = new ArrayList<ProviderSummaryView>();

		try {
			Criteria criteria = session
					.createCriteria(ProviderSummaryView.class);

			providerSummaryViews = criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} catch (Exception e) {
			logger.info("Providers view not found ", e);
		} finally {
			session.close();
		}
		return providerSummaryViews;
	}

	@Override
	public List<CoursePrices> coursePrices(Long courseId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<CoursePrices> coursePrices = new ArrayList<CoursePrices>();

		try {
			Criteria criteria = session.createCriteria(CoursePrices.class);
			criteria.createAlias("courses", "c");
			criteria.add(Restrictions.eq("c.id", courseId));
			coursePrices = criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} catch (Exception e) {
			logger.info("Providers view not found ", e);
		} finally {
			session.close();
		}
		return coursePrices;
	}

	@Override
	public boolean updateRoles(String role, int flag, Long accountId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Session session1 = sessionFactory.openSession();
		Transaction tx1 = session1.beginTransaction();
		List<UserRole> userRoles = new ArrayList<UserRole>();
		UserRole userRole = null;
		try {
			Criteria criteria = session1.createCriteria(UserRole.class);
			criteria.createAlias("accounts", "ac");
			criteria.add(Restrictions.eq("ac.id", accountId));
			userRoles = criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx1.commit();
		} catch (Exception e) {
			logger.info("No Role Find", e);
		} finally {
			session1.close();
		}

		for (UserRole ul : userRoles) {
			if (ul.getRole().equals(role)) {
				userRole = ul;
				break;
			}
		}

		if (flag == 0) {

			try {
				if (userRole != null)
					return false;
				UserRole userRole2 = new UserRole(new Accounts(accountId), role);
				session.save(userRole2);
				tx.commit();
				return true;
			} catch (Exception e) {
				logger.info("Unable to add role", e);
			} finally {
				session.close();
			}
		} else {
			try {
				if (userRole == null)
					return false;

				session.delete(userRole);
				tx.commit();
				return true;
			} catch (Exception e) {
				logger.info("Unable to delete role ", e);
			} finally {
				session.close();
			}
		}
		return false;
	}

	@Override
	public List<UserRole> getUserRole(Long accountId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<UserRole> userRoles = new ArrayList<UserRole>();

		try {
			Criteria criteria = session.createCriteria(UserRole.class);
			criteria.createAlias("accounts", "accounts");
			criteria.add(Restrictions.eq("accounts.id", accountId));
			userRoles = criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} catch (Exception e) {
			logger.info("No role assigned ", e);
		} finally {
			session.close();
		}
		return userRoles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> allQuickCodesType() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Set<String> set = new HashSet<String>();
		List<QuickCodes> list = null;
		try {
			Criteria criteria = session.createCriteria(QuickCodes.class);
			list = criteria.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}
		if (list != null) {
			for (QuickCodes quickCodes : list) {
				set.add(quickCodes.getType());
			}
		}
		return set;
	}

	@Override
	public List<Providers> allProviders() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Providers> list = null;
		try {
			list = session.createCriteria(Providers.class).setCacheable(true)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			for (Providers providers : list) {
				Hibernate.initialize(providers.getAccounts());
			}
			tx.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<QuizHistory> quizHistoryByAccountAndQuizId(Long quizId,
			Long accountId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<QuizHistory> list = null;
		try {
			Criteria criteria = session.createCriteria(QuizHistory.class).add(
					Restrictions.eq("quizId", quizId));

			if (accountId != null)
				criteria.add(Restrictions.eq("accountId", accountId));

			list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<QuizAttempts> quizAttemptByQuizId(Long quizId, Long accountId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<QuizAttempts> list = null;
		try {
			Criteria criteria = session.createCriteria(QuizAttempts.class).add(
					Restrictions.eq("quizId", quizId));

			if (accountId != null)
				criteria.add(Restrictions.eq("accountId", accountId));

			list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<QuizAttemptDetails> quizAttemptDetailsByQuizId(Long attemptId) {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		List<QuizAttemptDetails> quizAttemptDetails = null;
		try {
			Criteria criteria = session
					.createCriteria(QuizAttemptDetails.class);
			criteria.add(Restrictions.eq("attemptId", attemptId));

			quizAttemptDetails = criteria.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}
		return quizAttemptDetails;
	}

	@Override
	public PaymentTransactions getTransaction(Long courseId, Long accountId) {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		PaymentTransactions paymentTransactions = null;
		try {
			Criteria criteria = session
					.createCriteria(PaymentTransactions.class);
			criteria.add(Restrictions.eq("accountId", accountId));
			criteria.add(Restrictions.eq("courseId", courseId));

			paymentTransactions = (PaymentTransactions) criteria
					.setCacheable(true).list().get(0);
			tx.commit();
		} finally {
			session.close();
		}
		return paymentTransactions;
	}

	@Override
	public List<Providers> providerListByOrgId(Long orgId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Providers> providers = new ArrayList<Providers>();

		try {
			Criteria criteria = session.createCriteria(Providers.class);
			criteria.createAlias("organization", "ac");
			criteria.add(Restrictions.eq("ac.id", orgId));

			providers = criteria
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} catch (Exception e) {
			logger.info("Providers not found ", e);
		} finally {
			session.close();
		}
		return providers;
	}
}
