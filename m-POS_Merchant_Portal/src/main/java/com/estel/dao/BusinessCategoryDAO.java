package com.estel.dao;

import com.estel.entity.BusinessCategory;

import java.util.List;

public interface BusinessCategoryDAO extends GenericDAO<BusinessCategory, Long> {

	public BusinessCategory addBusinessCategory(BusinessCategory BusinessCategory);
	public BusinessCategory getBusinessCategoryById(Long Id);
	public List<BusinessCategory> listBusinessCategorys();
	public void updateBusinessCategory(BusinessCategory BusinessCategory);
}
