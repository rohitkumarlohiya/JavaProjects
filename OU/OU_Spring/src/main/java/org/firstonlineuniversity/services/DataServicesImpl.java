package org.firstonlineuniversity.services;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.dao.DataDao;
import org.firstonlineuniversity.domains.custom.CustomCoursesFilterNav;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.AbstractEntityList;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.login.UserRole;
import org.firstonlineuniversity.models.profiles.Profiles;
import org.firstonlineuniversity.models.views.ProviderSummaryView;
import org.firstonlineuniversity.models.views.SiteSummaryView;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "dataServices")
public class DataServicesImpl implements DataServices<AbstractEntity> {

	@Autowired
	DataDao<AbstractEntity> dataDao;

	static final Logger logger = Logger.getLogger(DataServicesImpl.class);

	@Override
	public boolean addEntity(AbstractEntity entity)
			throws ConstraintViolationException, HibernateException, Exception {
		return dataDao.addEntity(entity);
	}

	@Override
	@Transactional
	public boolean updateUserEntity(AbstractEntity entity) throws Exception {
		Accounts accounts = (Accounts) entity;
		Accounts accountsNew = dataDao.findByUserName(accounts
				.getAccountEmail());

		accountsNew.setFirstName(accounts.getFirstName());
		accountsNew.setLastName(accounts.getLastName());
		accountsNew.setPassword(accounts.getPassword());
		accountsNew.setAccountPhone(accounts.getAccountPhone());

		return dataDao.updateEntity(accountsNew);
	}

	@Override
	public boolean updateOrg(AbstractEntity entity) throws Exception {
		return dataDao.updateEntity(entity);
	}

	@Override
	public AbstractEntity getEntity(long id,
			Class<? extends AbstractEntity> clazz) throws Exception {

		return dataDao.getEntity(id, clazz);
	}

	@Override
	public boolean deleteEntity(long id, Class<? extends AbstractEntity> clazz)
			throws Exception {
		// TODO Auto-generated method stub
		return dataDao.deleteEntity(id, clazz);
	}

	@Override
	public List<AbstractEntity> getEntityList(
			Class<? extends AbstractEntity> clazz) throws HibernateException,
			Exception {
		// TODO Auto-generated method stub
		return dataDao.getEntityList(clazz);
	}

	@Override
	public List<AbstractEntity> getEntityList(long id, String restrictionStr,
			Class<? extends AbstractEntity> clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Accounts findByUserName(String username) throws Exception {
		return dataDao.findByUserName(username);
	}

	@Override
	@Transactional
	public boolean addUser(Accounts accounts, UserRole userRole)
			throws Exception {
		return dataDao.addUser(accounts, userRole);
	}

	@Override
	public boolean activateUser(Accounts accounts) throws Exception {
		accounts.setActivated(true);
		return dataDao.updateEntity(accounts);
	}

	@Override
	public boolean updateConfirmation(String email, String confirmStriing)
			throws Exception {
		return dataDao.updateConfirmation(email, confirmStriing);
	}

	@Override
	public List<AbstractEntity> getEntityList(
			Class<? extends AbstractEntity> clazz, Integer page,
			Integer perpage, String queryType, String queryValue)
			throws HibernateException, Exception {
		return dataDao.getEntityList(clazz, page, perpage, queryType,
				queryValue);
	}

	@Override
	public Profiles getProfileByAccountId(Accounts accounts) throws Exception {
		// TODO Auto-generated method stub
		return dataDao.getProfileByAccountId(accounts);
	}

	@Override
	public AbstractEntityList getEntityListPagination(
			Class<? extends AbstractEntity> clazz, Integer page,
			Integer perpage, String queryType, String queryValue)
			throws HibernateException, Exception {
		// TODO Auto-generated method stub
		return dataDao.getEntityListPagination(clazz, page, perpage, queryType,
				queryValue);
	}

	@Override
	public AbstractEntityList getEntityListMultipleFilter(
			Class<? extends AbstractEntity> clazz, Integer page,
			Integer perpage, Map<String, Object> queryMap)
			throws HibernateException, Exception {
		// TODO Auto-generated method stub
		return dataDao.getEntityListMultipleFilter(clazz, page, perpage,
				queryMap);
	}

	@Override
	public List<AbstractEntity> getProvidersByCourseId(long id)
			throws Exception {
		// TODO Auto-generated method stub
		return dataDao.getProvidersByCourseId(id);
	}

	@Override
	public List<CustomCoursesFilterNav> getCoursesFilterNav()
			throws HibernateException, Exception {
		// TODO Auto-generated method stub
		return dataDao.getCoursesFilterNav();
	}

	@Override
	public boolean updateEntity(AbstractEntity entity) throws Exception {
		return dataDao.updateEntity(entity);
	}

	@Override
	public List<AbstractEntity> getCodesByType(
			Class<? extends AbstractEntity> clazz, String type)
			throws HibernateException, Exception {
		return dataDao.getCodesByType(clazz, type);
	}

	@Override
	public boolean disableEntity(long id, Class<? extends AbstractEntity> clazz)
			throws HibernateException, Exception {
		// TODO Auto-generated method stub
		return dataDao.disableEntity(id, clazz);
	}

	@Override
	public AbstractEntity getEntityByIdByHQL(String tableName, long id)
			throws HibernateException, Exception {
		// TODO Auto-generated method stub
		return dataDao.getEntityByIdByHQL(tableName, id);
	}

	@Override
	public String getCourseKey(long courseId) {
		return dataDao.getCourseKey(courseId);
	}

	@Override
	public boolean validateExistance(String columnName, Object columnValue,
			Class<? extends AbstractEntity> clazz) {
		return dataDao.validateExistance(columnName, columnValue, clazz);
	}

	@Override
	public List<SiteSummaryView> siteSummaryList() throws Exception {
		return dataDao.siteSummaryList();
	}
}
