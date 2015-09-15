package com.estel.dao;

import com.estel.entity.Merchant;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MerchantDAOImpl extends GenericDAOImpl<Merchant, Long> implements MerchantDAO {

    @Autowired
    Merchant merchant;

    @Autowired
    Merchant merchant1;

    @Autowired
    StatusDAO statusDAO;

    @Autowired
    LevelDAO levelDAO;

   /* @Autowired
    MerchantDAO merchantDAO;
*/
    @Transactional
    public Merchant addMerchant(Merchant merchant) {
        return (Merchant) this.read(this.create(merchant));
    }


    @Transactional
    public Merchant getMerchantById(Long Id) {
        return (Merchant) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<Merchant> listMerchants() {
        return getSession().createQuery("from Merchant").list();
    }


    @Transactional
    public void updateMerchant(Merchant merchant) {
        getSession().update(merchant);

    }

    @Transactional
    public Merchant getMerchantByAbbr(String abbr)  {
        String sql = "from Merchant where merchantCode=:abbr";
        Query query = getSession().createQuery(sql);
        query.setString("abbr", abbr);
        List<Merchant> merchantList = query.list();
        if (merchantList.size() <= 0) {
            return null;
        }
        return merchantList.get(0);
    }

    @Transactional
    public Merchant addMerchantByAbbr(String name, String abbr, Long statusId, String activationCode,String mobileNumber) {

        /*Long merchant_level_id = null;
        Long merchant_parent_id = null;
        Long merchant_parent_level_id = null;
        Long merchant_parent_status_id = null;
        Long merchant_status_id = null;
        Date merchant_creation_date = null;
        Date merchant_activation_date = null;
        Long merchant_ui_role_id = null;
        Date merchant_ts = null;*/

        //set mandatory field
        merchant.setMerchantLevelId(1L);
        merchant.setMerchant(merchant1);
        merchant.setMerchantParentLevelId(1L);
        merchant.setMerchantParentStatusId(1L);
        merchant.setStatus2(statusDAO.getStatusById(statusId));
        merchant.setMerchantCreationDate(new java.sql.Date(System.currentTimeMillis()));
        merchant.setMerchantActivationDate(new java.sql.Date(System.currentTimeMillis()));
        merchant.setMerchantUiRoleId(1L);
        merchant.setMerchantTs(new java.sql.Date(System.currentTimeMillis()));
        //set mandatory field

        merchant.setMerchantMobileNo(mobileNumber);
        merchant.setMerchantEmail(abbr);
        merchant.setMerchantType("Merchant");
        merchant.setMerchantName(name);
        merchant.setMerchantCode(abbr);
        merchant.setMerchantProofValue(activationCode);

        return (Merchant) this.read(this.create(merchant));




        /*if (merchant_level_id == null || merchant_parent_id == null
                || merchant_parent_level_id == null || merchant_parent_status_id == null
                || merchant_status_id == null || merchant_creation_date == null
                || merchant_activation_date == null || merchant_ui_role_id == null || merchant_ts == null) {

            System.out.println("mandatory fields null");
        }*/



    }

}
