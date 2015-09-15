package com.estel.service;

import com.estel.entity.BusinessCategory;

import java.util.List;


public interface BusinessCategoryService {

	public BusinessCategory addBusinessCategory(BusinessCategory BusinessCategory);
	public BusinessCategory getBusinessCategoryById(Long Id);
	public List<BusinessCategory> listBusinessCategorys();
	public void updateBusinessCategory(BusinessCategory BusinessCategory);
}
