package com.estel.dao;

import com.estel.entity.BusinessType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BusinessTypeDAOImpl extends GenericDAOImpl<BusinessType, Long> implements BusinessTypeDAO {
	

	@Transactional
	public BusinessType addBusinessType(BusinessType BusinessType) {
		return (BusinessType) this.read(this.create(BusinessType));
	}


	@Transactional
	public BusinessType getBusinessTypeById(Long Id) {
		return (BusinessType) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<BusinessType> listBusinessTypes() {
		return getSession().createQuery("from BusinessType").list();
	}


	@Transactional
	public void updateBusinessType(BusinessType BusinessType) {
		getSession().update(BusinessType);
		
	}

}
