package org.firstonlineuniversity.services;

import java.util.List;
import java.util.Map;

import org.firstonlineuniversity.domains.custom.CustomCoursesFilterNav;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.AbstractEntityList;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.login.UserRole;
import org.firstonlineuniversity.models.profiles.Profiles;
import org.firstonlineuniversity.models.views.SiteSummaryView;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;


public interface DataServices<T extends AbstractEntity> {

	/**
	 * @param entity
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addEntity(T entity) throws ConstraintViolationException,
			HibernateException, Exception;

	/**
	 * @param entity
	 * @return boolean
	 * @throws Exception
	 */

	public T getEntity(long id, Class<? extends T> clazz) throws Exception;

	/**
	 * @param id
	 * @param clazz
	 * @return boolean
	 * @throws Exception
	 */
	public boolean deleteEntity(long id, Class<? extends T> clazz)
			throws Exception;

	/**
	 * @param clazz
	 * @return List<T>
	 * @throws HibernateException
	 * @throws Exception
	 */

	public List<T> getEntityList(Class<? extends T> clazz)
			throws HibernateException, Exception;

	public List<T> getEntityList(Class<? extends T> clazz, Integer page,
			Integer perpage, String queryType, String queryValue)
			throws HibernateException, Exception;

	/**
	 * @param id
	 * @param restrictionStr
	 * @param clazz
	 * @return List<T>
	 * @throws Exception
	 */
	public List<T> getEntityList(long id, String restrictionStr,
			Class<? extends T> clazz) throws Exception;

	public Accounts findByUserName(String username) throws Exception;

	public boolean addUser(Accounts accounts, UserRole userRole)
			throws Exception;

	public boolean activateUser(Accounts accounts) throws Exception;

	public boolean updateConfirmation(String email, String confirmStriing)
			throws Exception;

	public Profiles getProfileByAccountId(Accounts accounts) throws Exception;

	public boolean updateOrg(AbstractEntity entity) throws Exception;

	public AbstractEntityList getEntityListPagination(Class<? extends T> clazz,
			Integer page, Integer perpage, String queryType, String queryValue)
			throws HibernateException, Exception;

	public AbstractEntityList getEntityListMultipleFilter(
			Class<? extends T> clazz, Integer page, Integer perpage,
			Map<String, Object> queryMap) throws HibernateException, Exception;

	public List<AbstractEntity> getProvidersByCourseId(long id)
			throws Exception;

	public List<CustomCoursesFilterNav> getCoursesFilterNav()
			throws HibernateException, Exception;

	boolean updateUserEntity(AbstractEntity entity) throws Exception;

	boolean updateEntity(AbstractEntity entity) throws Exception;

	public List<AbstractEntity> getCodesByType(Class<? extends T> clazz,
			String type) throws HibernateException, Exception;

	public boolean disableEntity(long id, Class<? extends AbstractEntity> clazz)
			throws HibernateException, Exception;

	public AbstractEntity getEntityByIdByHQL(String tableName, long id)
			throws HibernateException, Exception;

	public String getCourseKey(long courseId);

	public boolean validateExistance(String columnName, Object columnValue,
			Class<? extends T> clazz);

	public List<SiteSummaryView> siteSummaryList() throws Exception;

}
