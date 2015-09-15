package com.estel.service;

import com.estel.dao.BusinessDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl extends GenericDAOImpl<Business, Long> implements
        BusinessService {

	@Autowired
    BusinessDAO BusinessDAO;
	
	public Business addBusiness(Business Business) {
		return BusinessDAO.addBusiness(Business);
	}


	public Business getBusinessById(Long Id) {
		return BusinessDAO.getBusinessById(Id);
	}


	public List<Business> listBusinesss() {
		return BusinessDAO.listBusinesss();
	}


	public void updateBusiness(Business Business) {
		BusinessDAO.updateBusiness(Business);
		
	}


	public Business getBusinessByAgentId(Long agentId) {

        return  BusinessDAO.getBusinessByAgentId(agentId);
	}



    public void updateBusinessByAgentId(String legalName,
                                          String marketName,
                                          Long businessOwnerTypeId,
                                          Long businessTypeId,
                                          Long businessCategoryId,
                                          Long agentId)
    {

        BusinessDAO.updateBusinessByAgentId( legalName,
                 marketName,
                 businessOwnerTypeId,
                 businessTypeId,
                 businessCategoryId,
                 agentId);
    }



    public Business addBusinessByAgentId(String legalName,
                                             String marketName,
                                             Long businessOwnerTypeId,
                                             Long businessTypeId,
                                             Long businessCategoryId,
                                             Long agentId)
    {

      return BusinessDAO.addBusinessByAgentId(legalName,
                marketName,
                businessOwnerTypeId,
                businessTypeId,
                businessCategoryId,
                agentId);
    }

}
