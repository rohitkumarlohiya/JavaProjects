package com.estel.dao;

import com.estel.entity.Business;

import java.util.List;

public interface BusinessDAO extends GenericDAO<Business, Long> {

	public Business addBusiness(Business Business);
	public Business getBusinessById(Long Id);
	public List<Business> listBusinesss();
	public void updateBusiness(Business Business);

    public Business getBusinessByAgentId(Long agentId);

    public void updateBusinessByAgentId(String legalName,
                                          String marketName,
                                          Long businessOwnerTypeId,
                                          Long businessTypeId,
                                          Long businessCategoryId,
                                          Long agentId);

    public Business addBusinessByAgentId(String legalName,
                                             String marketName,
                                             Long businessOwnerTypeId,
                                             Long businessTypeId,
                                             Long businessCategoryId,
                                             Long agentId);
	
}
