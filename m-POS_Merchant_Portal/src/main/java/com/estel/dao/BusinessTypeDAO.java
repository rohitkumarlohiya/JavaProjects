package com.estel.dao;

import com.estel.entity.BusinessType;

import java.util.List;

public interface BusinessTypeDAO extends GenericDAO<BusinessType, Long> {

	public BusinessType addBusinessType(BusinessType BusinessType);
	public BusinessType getBusinessTypeById(Long Id);
	public List<BusinessType> listBusinessTypes();
	public void updateBusinessType(BusinessType BusinessType);
}
