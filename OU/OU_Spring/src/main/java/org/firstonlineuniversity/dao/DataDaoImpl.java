package org.firstonlineuniversity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.custom.CustomCoursesFilterNav;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.AbstractEntityList;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.Providers;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.login.UserRole;
import org.firstonlineuniversity.models.profiles.Profiles;
import org.firstonlineuniversity.models.views.SiteSummaryView;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DataDaoImpl implements DataDao<AbstractEntity> {

	static final Logger logger = Logger.getLogger(DataDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addEntity(AbstractEntity entity)
			throws ConstraintViolationException, HibernateException, Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(entity);
			tx.commit();
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean updateEntity(AbstractEntity entity) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
			tx.commit();
		} finally {

			session.close();
		}
		return true;
	}

	@Override
	public AbstractEntity getEntity(long id,
			Class<? extends AbstractEntity> clazz) throws Exception {
		AbstractEntity abstractEntity = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		session.beginTransaction();
		try {
			abstractEntity = (AbstractEntity) session.get(clazz, new Long(id));
			tx.commit();
		} finally {

			session.close();
		}
		return abstractEntity;
	}

	@Override
	public boolean deleteEntity(long id, Class<? extends AbstractEntity> clazz)
			throws Exception {

		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		session.beginTransaction();
		try {
			AbstractEntity abstractEntity = (AbstractEntity) session.get(clazz,
					new Long(id));
			session.delete(abstractEntity);
			tx.commit();
		} finally {

			session.close();
		}
		return true;
	}

	@Override
	public List<AbstractEntity> getEntityList(
			Class<? extends AbstractEntity> clazz) throws HibernateException,
			Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<AbstractEntity> list = null;
		try {
			list = session.createCriteria(clazz)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} finally {

			session.close();
		}
		return list;

	}

	@Override
	public List<AbstractEntity> getEntityList(long id, String restrictionStr,
			Class<? extends AbstractEntity> clazz) throws Exception {

		return null;

	}

	@Override
	public Accounts findByUserName(String username) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Accounts accounts = null;
		try {
			String hql = "FROM Accounts A WHERE A.accountEmail = :account_email";

			Query query = session.createQuery(hql);
			query.setParameter("account_email", username);

			@SuppressWarnings("unchecked")
			List<Accounts> results = query.list();

			accounts = results.get(0);
			tx.commit();
		} finally {
			session.close();
		}
		return accounts;
	}

	@Override
	@Transactional
	public boolean addUser(Accounts accounts, UserRole userRole)
			throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(accounts);
			session.save(userRole);

			session.save(new Profiles(accounts.getFirstName(), accounts
					.getLastName(), accounts.getAccountEmail(), accounts));
			/* default role to be added to each user */
			session.save(new UserRole(accounts, "ROLE_ANON"));
			tx.commit();
		} finally {
			session.close();
		}
		return true;

	}

	@Override
	public boolean updateConfirmation(String email, String confirmStriing)
			throws Exception {
		String hql = "UPDATE Accounts set confirmation = :confirmStriing "
				+ "WHERE accountEmail = :email";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			Query query = session.createQuery(hql);
			query.setParameter("confirmStriing", confirmStriing);
			query.setParameter("email", email);

			int result = query.executeUpdate();
			tx.commit();
		} finally {
			session.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AbstractEntity> getEntityList(
			Class<? extends AbstractEntity> clazz, Integer page,
			Integer perpage, String queryType, String queryValue)
			throws HibernateException, Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<AbstractEntity> list = null;
		try {
			Criteria criteria = session.createCriteria(clazz);

			if (queryType != null)
				criteria.add(Restrictions.like(queryType, queryValue));

			if (page != null) {
				criteria.setFirstResult(page * perpage);
				criteria.setMaxResults(perpage);
			}
			list = criteria.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public Profiles getProfileByAccountId(Accounts accounts) throws Exception {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Profiles profiles = null;
		try {
			Criteria cr = session.createCriteria(Profiles.class);
			cr.add(Restrictions.eq("accounts", accounts));

			profiles = (Profiles) cr.list().get(0);
			tx.commit();
		} finally {
			session.close();
		}
		return profiles;
	}

	@Override
	public AbstractEntityList getEntityListPagination(
			Class<? extends AbstractEntity> clazz, Integer page,
			Integer perpage, String queryType, String queryValue)
			throws HibernateException, Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Long resultCount = null;
		List<AbstractEntity> list = null;
		try {
			Criteria criteria = session.createCriteria(clazz);
			Criteria criteriaCount = session.createCriteria(clazz);

			if (queryType != null) {
				criteria.add(Restrictions.like(queryType, queryValue));
				criteriaCount.add(Restrictions.like(queryType, queryValue));
			}
			resultCount = (Long) criteriaCount.setProjection(
					Projections.rowCount()).uniqueResult();

			// Long resultCount = (Long) criteria.uniqueResult();

			if (page != null) {
				criteria.setFirstResult(page * perpage);
				criteria.setMaxResults(perpage);
			}

			list = criteria.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}
		return new AbstractEntityList(resultCount, list);
	}

	@Override
	public AbstractEntityList getEntityListMultipleFilter(
			Class<? extends AbstractEntity> clazz, Integer page,
			Integer perpage, Map<String, Object> queryMap)
			throws HibernateException, Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Long resultCount = null;
		List<AbstractEntity> list = null;
		List<AbstractEntity> finalList = new ArrayList<AbstractEntity>();
		try {
			Criteria criteria = session.createCriteria(clazz);
			Criteria criteriaCount = session.createCriteria(clazz, "courses");

			if (queryMap.get("popularCourse") != null) {
				criteria.add(Restrictions.eq("popularCourse",
						(Boolean) queryMap.get("popularCourse")));
				criteriaCount.add(Restrictions.eq("popularCourse",
						(Boolean) queryMap.get("popularCourse")));

			}
			if (queryMap.get("organizationId") != null) {
				criteria.add(Restrictions.eq("organizationId",
						(long) queryMap.get("organizationId")));
				criteriaCount.add(Restrictions.eq("organizationId",
						(long) queryMap.get("organizationId")));

			}
			if (queryMap.get("featuredCourse") != null) {
				criteria.add(Restrictions.eq("featuredCourse",
						(Boolean) queryMap.get("featuredCourse")));
				criteriaCount.add(Restrictions.eq("featuredCourse",
						(Boolean) queryMap.get("featuredCourse")));

			}
			if (queryMap.get("startingSoon") != null) {
				criteria.add(Restrictions.eq("startingSoon",
						(Boolean) queryMap.get("startingSoon")));
				criteriaCount.add(Restrictions.eq("startingSoon",
						(Boolean) queryMap.get("startingSoon")));

			}
			if (queryMap.get("primaryLanguage") != null) {
				criteria.add(Restrictions.eq("primaryLanguage",
						(String) queryMap.get("primaryLanguage")));
				criteriaCount.add(Restrictions.eq("primaryLanguage",
						(String) queryMap.get("primaryLanguage")));

			}
			Disjunction categorydisjunction = null;
			if (queryMap.get("category") != null) {

				String[] catArray = (String[]) queryMap.get("category");
				logger.info("category " + catArray);
				logger.info("category length " + catArray.length);

				criteria.createAlias("courseCategory", "category");
				criteriaCount.createAlias("courseCategory", "category");

				categorydisjunction = Restrictions.disjunction();
				for (int x = 0; x < catArray.length; x++) {
					categorydisjunction.add(Restrictions.eq("category.id",
							Long.parseLong(catArray[x])));
				}
				criteria.add(categorydisjunction);
				criteriaCount.add(categorydisjunction);

				/*
				 * CourseCategory categories = (CourseCategory)
				 * session.get(CourseCategory.class, new Long(
				 * Long.parseLong(queryMap.get("categoryId"))));
				 * logger.info(categories.getId());
				 */

				/*
				 * logger.info("categoryId " + queryMap.get("categoryId"));
				 * 
				 * criteria.createAlias("courseCategory", "category");
				 * criteriaCount.createAlias("courses.courseCategory",
				 * "category");
				 * 
				 * criteria.add(Restrictions.eq("category.id",
				 * Long.parseLong((String) queryMap.get("categoryId"))));
				 * criteriaCount.add(Restrictions.eq("category.id",
				 * Long.parseLong((String) queryMap.get("categoryId"))));
				 */
			}
			Disjunction diffLevelDisjunction = null;
			if (queryMap.get("difficultyLevel") != null) {

				String[] diffArray = (String[]) queryMap.get("difficultyLevel");
				logger.info("difficultyLevel " + diffArray);
				logger.info("difficultyLevel length " + diffArray.length);

				diffLevelDisjunction = Restrictions.disjunction();
				for (int x = 0; x < diffArray.length; x++) {
					diffLevelDisjunction.add(Restrictions.eq("difficultyLevel",
							diffArray[x]));
				}
				criteria.add(diffLevelDisjunction);
				criteriaCount.add(diffLevelDisjunction);
			}

			Disjunction courseTypedisjunction = null;
			if (queryMap.get("courseType") != null) {

				String[] typeArray = (String[]) queryMap.get("courseType");
				logger.info("courseType " + typeArray);
				logger.info("courseType length " + typeArray.length);

				courseTypedisjunction = Restrictions.disjunction();
				for (int x = 0; x < typeArray.length; x++) {
					courseTypedisjunction.add(Restrictions.eq("courseType",
							typeArray[x]));
				}
				criteria.add(courseTypedisjunction);
				criteriaCount.add(courseTypedisjunction);

				/*
				 * Criterion type1 = Restrictions.like("courseType", "Online");
				 * Criterion type2 = Restrictions.ilike("courseType",
				 * "Online and"); Criterion type3 =
				 * Restrictions.ilike("courseType", "Online and");
				 * 
				 * Criterion completeCondition =
				 * Restrictions.disjunction().add(type1) .add(type2).add(type3);
				 */

				/*
				 * Criterion type = Restrictions.like("courseType",
				 * typeArray[0]); Criterion completeCondition =
				 * Restrictions.disjunction().add(type);
				 * 
				 * criteria.add(completeCondition);
				 * criteriaCount.add(completeCondition);
				 */

			}

			resultCount = (Long) criteriaCount.setProjection(
					Projections.rowCount()).uniqueResult();

			// Long resultCount = (Long) criteria.uniqueResult();

			if (page != null) {
				criteria.setFirstResult(page * perpage);
				criteria.setMaxResults(perpage);
			}

			list = criteria.setCacheable(true).list();

			if (queryMap.get("authorId") != null) {
				criteria.createAlias("courseProviders", "cp");
				criteria.add(Restrictions.eq("cp.pk.providers.id",
						(Long) queryMap.get("authorId")));

				criteriaCount.createAlias("courseProviders", "cpp");
				criteriaCount.add(Restrictions.eq("cpp.pk.providers.id",
						(Long) queryMap.get("authorId")));

				for (AbstractEntity abstractEntity : list) {
					CourseInformation courseInformation = (CourseInformation) abstractEntity;

					Long authorId = (Long) queryMap.get("authorId");
					if (authorId != null)
						finalList.add(courseInformation);
				}
			} else {
				for (AbstractEntity abstractEntity : list) {
					CourseInformation courseInformation = (CourseInformation) abstractEntity;
					finalList.add(courseInformation);
				}
			}

			tx.commit();
		} finally {
			session.close();
		}
		return new AbstractEntityList(resultCount, finalList);
	}

	@Override
	public List<AbstractEntity> getProvidersByCourseId(long id)
			throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<AbstractEntity> list = null;
		try {
			Criteria criteria = session.createCriteria(Providers.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.createAlias("courseProviders", "cp");
			criteria.add(Restrictions.eq("cp.courses", (Long) id));

			list = criteria.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List<CustomCoursesFilterNav> getCoursesFilterNav()
			throws HibernateException, Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<CustomCoursesFilterNav> list = null;
		try {
			list = session.createCriteria(CustomCoursesFilterNav.class)
					.setCacheable(true).list();
			if (list == null) {
				logger.info("list is null");
			}
			tx.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<AbstractEntity> getCodesByType(
			Class<? extends AbstractEntity> clazz, String type)
			throws HibernateException, Exception {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<AbstractEntity> list = new ArrayList<AbstractEntity>();
		try {
			Criteria criteria = session.createCriteria(clazz);
			criteria.add(Restrictions.eq("type", type));
			list = criteria.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public boolean disableEntity(long id, Class<? extends AbstractEntity> clazz)
			throws HibernateException, Exception {
		logger.info("Identifier: " + id);
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		session.beginTransaction();
		try {
			Providers entity = (Providers) session.get(clazz, id);
			if (entity == null)
				throw new EntityNotFoundException();

			entity.setEnabled(false);
			session.save(entity);
			tx.commit();
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public List<AbstractEntity> providersbyCourseId(long courseId)
			throws HibernateException, Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<AbstractEntity> list = null;
		try {
			Criteria criteria = session.createCriteria(Providers.class);
			criteria.createAlias("c", "course");
			criteria.add(Restrictions.eq("c.id", courseId));
			list = criteria.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public AbstractEntity getEntityByIdByHQL(String tableName, long id)
			throws HibernateException, Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<AbstractEntity> list = null;
		try {
			Query query = session.createQuery("from " + tableName.trim()
					+ " where id = :id ");
			query.setParameter("id", id);
			list = query.list();
			tx.commit();
		} finally {
			session.close();
		}
		if (list != null && list.size() != 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public String getCourseKey(long courseId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String courseKey = "unknown";
		try {
			Criteria criteria = session.createCriteria(CourseInformation.class);
			criteria.add(Restrictions.eq("id", courseId));

			criteria.setProjection(Projections.projectionList().add(
					Projections.property("courseKey"), "courseKey"));

			@SuppressWarnings("unchecked")
			List<Object[]> list = criteria.setCacheable(true).list();

			if (list == null || list.isEmpty())
				return courseKey;

			Object[] fieldsArray = list.get(0);
			courseKey = String.valueOf(fieldsArray[0]);

			tx.commit();
		} finally {
			session.close();
		}
		return courseKey;
	}

	@Override
	public boolean validateExistance(String columnName, Object columnValue,
			Class<? extends AbstractEntity> clazz) {
		boolean flag = false;

		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		session.beginTransaction();
		try {
			Criteria absCriteria = session.createCriteria(clazz);
			List<AbstractEntity> list = absCriteria.add(
					Restrictions.eq(columnName, columnValue)).list();

			if (list != null && list.size() > 0)
				flag = true;

			tx.commit();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<SiteSummaryView> siteSummaryList() throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<SiteSummaryView> list = null;
		try {
			list = session.createCriteria(SiteSummaryView.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setCacheable(true).list();
			tx.commit();
		} finally {
			session.close();
		}
		return list;

	}
}
