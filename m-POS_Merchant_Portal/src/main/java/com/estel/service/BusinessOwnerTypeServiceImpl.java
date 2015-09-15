package com.estel.service;

import com.estel.dao.BusinessOwnerTypeDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.BusinessOwnerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessOwnerTypeServiceImpl extends GenericDAOImpl<BusinessOwnerType, Long> implements
        BusinessOwnerTypeService {

	@Autowired
	private BusinessOwnerTypeDAO BusinessOwnerTypeDAO;


	public BusinessOwnerType addBusinessOwnerType(BusinessOwnerType BusinessOwnerType) {
		return BusinessOwnerTypeDAO.addBusinessOwnerType(BusinessOwnerType);
	}


	public BusinessOwnerType getBusinessOwnerTypeById(Long Id) {
		return BusinessOwnerTypeDAO.getBusinessOwnerTypeById(Id);
	}


	public List<BusinessOwnerType> listBusinessOwnerTypes() {
		return BusinessOwnerTypeDAO.listBusinessOwnerTypes();
	}


	public void updateBusinessOwnerType(BusinessOwnerType BusinessOwnerType) {
		BusinessOwnerTypeDAO.updateBusinessOwnerType(BusinessOwnerType);
		
	}

	
}
