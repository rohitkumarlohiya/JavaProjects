package com.estel.service;

import com.estel.dao.BusinessTypeDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeServiceImpl extends GenericDAOImpl<BusinessType, Long> implements
        BusinessTypeService {

	@Autowired
	private BusinessTypeDAO BusinessTypeDAO;


	public BusinessType addBusinessType(BusinessType BusinessType) {
		return BusinessTypeDAO.addBusinessType(BusinessType);
	}


	public BusinessType getBusinessTypeById(Long Id) {
		return BusinessTypeDAO.getBusinessTypeById(Id);
	}


	public List<BusinessType> listBusinessTypes() {
		return BusinessTypeDAO.listBusinessTypes();
	}


	public void updateBusinessType(BusinessType BusinessType) {
		BusinessTypeDAO.updateBusinessType(BusinessType);
		
	}

	
}
