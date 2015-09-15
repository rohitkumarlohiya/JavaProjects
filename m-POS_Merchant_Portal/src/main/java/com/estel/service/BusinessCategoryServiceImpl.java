package com.estel.service;

import com.estel.dao.BusinessCategoryDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.BusinessCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessCategoryServiceImpl extends GenericDAOImpl<BusinessCategory, Long> implements
        BusinessCategoryService {

	@Autowired
	private BusinessCategoryDAO BusinessCategoryDAO;


	public BusinessCategory addBusinessCategory(BusinessCategory BusinessCategory) {
		return BusinessCategoryDAO.addBusinessCategory(BusinessCategory);
	}


	public BusinessCategory getBusinessCategoryById(Long Id) {
		return BusinessCategoryDAO.getBusinessCategoryById(Id);
	}


	public List<BusinessCategory> listBusinessCategorys() {
		return BusinessCategoryDAO.listBusinessCategorys();
	}


	public void updateBusinessCategory(BusinessCategory BusinessCategory) {
		BusinessCategoryDAO.updateBusinessCategory(BusinessCategory);
		
	}

	
}
