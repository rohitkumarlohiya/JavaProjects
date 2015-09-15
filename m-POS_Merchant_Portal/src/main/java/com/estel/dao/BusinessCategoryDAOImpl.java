package com.estel.dao;

import com.estel.entity.BusinessCategory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BusinessCategoryDAOImpl extends GenericDAOImpl<BusinessCategory, Long> implements BusinessCategoryDAO {
	

	@Transactional
	public BusinessCategory addBusinessCategory(BusinessCategory BusinessCategory) {
		return (BusinessCategory) this.read(this.create(BusinessCategory));
	}


	@Transactional
	public BusinessCategory getBusinessCategoryById(Long Id) {
		return (BusinessCategory) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<BusinessCategory> listBusinessCategorys() {
		return getSession().createQuery("from BusinessCategory").list();
	}


	@Transactional
	public void updateBusinessCategory(BusinessCategory BusinessCategory) {
		getSession().update(BusinessCategory);
		
	}

}
