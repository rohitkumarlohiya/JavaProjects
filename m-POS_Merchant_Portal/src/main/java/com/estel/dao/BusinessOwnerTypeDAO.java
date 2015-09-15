package com.estel.dao;

import com.estel.entity.BusinessOwnerType;

import java.util.List;

public interface BusinessOwnerTypeDAO extends GenericDAO<BusinessOwnerType, Long> {

	public BusinessOwnerType addBusinessOwnerType(BusinessOwnerType BusinessOwnerType);
	public BusinessOwnerType getBusinessOwnerTypeById(Long Id);
	public List<BusinessOwnerType> listBusinessOwnerTypes();
	public void updateBusinessOwnerType(BusinessOwnerType BusinessOwnerType);
}
