package com.estel.dao;

import com.estel.entity.Business;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BusinessDAOImpl extends GenericDAOImpl<Business, Long> implements BusinessDAO {

    @Autowired
    MerchantDAO merchantDAO;

    @Autowired
    Business business;

    @Autowired
    private BusinessOwnerTypeDAO businessOwnerTypeDAO;

    @Autowired
    private BusinessCategoryDAO businessCategoryDAO;

    @Autowired
    private BusinessTypeDAO businessTypeDAO;


    @Transactional
    public Business addBusiness(Business Business) {
        return (Business) this.read(this.create(Business));
    }


    @Transactional
    public Business getBusinessById(Long Id) {
        return (Business) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<Business> listBusinesss() {
        return getSession().createQuery("from Business").list();
    }


    @Transactional
    public void updateBusiness(Business Business) {
        getSession().update(Business);

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Business getBusinessByAgentId(Long agentId) {

        String sql = "from Business where businessMerchantId=:agentId";
        Query query = getSession().createQuery(sql);
        query.setLong("agentId", agentId);
        List<Business> businessList = query.list();
        if (businessList.size() <= 0) {
            return null;
        }
        return businessList.get(0);
    }

    @Transactional
    public void updateBusinessByAgentId(String legalName,
                                        String marketName,
                                        Long businessOwnerTypeId,
                                        Long businessTypeId,
                                        Long businessCategoryId,
                                        Long agentId) {

        business = this.getBusinessByAgentId(agentId);

        business.setBusinessName(legalName);
        business.setBusinessMarkName(marketName);
        business.setBusinessOwnerType(businessOwnerTypeDAO.getBusinessOwnerTypeById(businessOwnerTypeId));
        business.setBusinessType(businessTypeDAO.getBusinessTypeById(businessTypeId));
        business.setBusinessCategory(businessCategoryDAO.getBusinessCategoryById(businessCategoryId));

        this.updateBusiness(business);


    }

    @Transactional
    public Business addBusinessByAgentId(String legalName,
                                         String marketName,
                                         Long businessOwnerTypeId,
                                         Long businessTypeId,
                                         Long businessCategoryId,
                                         Long agentId) {
        //set mandatory field
        /*mbbus_ts*/
        business.setMbbusTs(new java.sql.Date(System.currentTimeMillis()));
        //set mandatory field

        business.setBusinessMerchantId(agentId);
        business.setBusinessName(legalName);
        business.setBusinessMarkName(marketName);
        business.setBusinessOwnerType(businessOwnerTypeDAO.getBusinessOwnerTypeById(businessOwnerTypeId));
        business.setBusinessType(businessTypeDAO.getBusinessTypeById(businessTypeId));
        business.setBusinessCategory(businessCategoryDAO.getBusinessCategoryById(businessCategoryId));

        return (Business) this.read(this.create(business));
    }
}
