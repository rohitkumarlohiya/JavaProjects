package com.estel.service;

import com.estel.entity.BusinessType;

import java.util.List;


public interface BusinessTypeService {

	public BusinessType addBusinessType(BusinessType BusinessType);
	public BusinessType getBusinessTypeById(Long Id);
	public List<BusinessType> listBusinessTypes();
	public void updateBusinessType(BusinessType BusinessType);
}
