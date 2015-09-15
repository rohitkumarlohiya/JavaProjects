package com.estel.dao;

import com.estel.entity.BusinessOwnerType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BusinessOwnerTypeDAOImpl extends GenericDAOImpl<BusinessOwnerType, Long> implements BusinessOwnerTypeDAO {
	

	@Transactional
	public BusinessOwnerType addBusinessOwnerType(BusinessOwnerType BusinessOwnerType) {
		return (BusinessOwnerType) this.read(this.create(BusinessOwnerType));
	}


	@Transactional
	public BusinessOwnerType getBusinessOwnerTypeById(Long Id) {
		return (BusinessOwnerType) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<BusinessOwnerType> listBusinessOwnerTypes() {
		return getSession().createQuery("from BusinessOwnerType").list();
	}


	@Transactional
	public void updateBusinessOwnerType(BusinessOwnerType BusinessOwnerType) {
		getSession().update(BusinessOwnerType);
		
	}

}
