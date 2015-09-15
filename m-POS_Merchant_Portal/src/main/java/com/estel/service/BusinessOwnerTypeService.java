package com.estel.service;

import com.estel.entity.BusinessOwnerType;

import java.util.List;


public interface BusinessOwnerTypeService {

	public BusinessOwnerType addBusinessOwnerType(BusinessOwnerType BusinessOwnerType);
	public BusinessOwnerType getBusinessOwnerTypeById(Long Id);
	public List<BusinessOwnerType> listBusinessOwnerTypes();
	public void updateBusinessOwnerType(BusinessOwnerType BusinessOwnerType);
}
