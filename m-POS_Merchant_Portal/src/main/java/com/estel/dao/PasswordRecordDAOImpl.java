package com.estel.dao;

import com.estel.entity.Merchant;
import com.estel.entity.PasswordRecord;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class PasswordRecordDAOImpl extends GenericDAOImpl<PasswordRecord, Long> implements PasswordRecordDAO {

    @Autowired
    MerchantDAO merchantDAO;

    @Autowired
    PinTypeDAO pinTypeDAO;

    @Autowired
    PasswordRecord passwordRecord;


	@Transactional
	public PasswordRecord addPasswordRecord(PasswordRecord PasswordRecord) {
		return (PasswordRecord) this.read(this.create(PasswordRecord));
	}


	@Transactional
	public PasswordRecord getPasswordRecordById(Long Id) {
		return (PasswordRecord) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<PasswordRecord> listPasswordRecords() {
		return getSession().createQuery("from PasswordRecord").list();
	}


	@Transactional
	public void updatePasswordRecord(PasswordRecord PasswordRecord) {
		getSession().update(PasswordRecord);
		
	}

    @SuppressWarnings("unchecked")
    @Transactional
    public List<PasswordRecord> listPasswordRecordByAgentId(Long agentId)
    {
        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from PasswordRecord where merchant=:merchant";
        Query query = getSession().createQuery(sql);
        query.setEntity("merchant", merchant);
        List<PasswordRecord> passwordRecordList = query.list();
        if (passwordRecordList.size() <= 0) {
            return null;
        }
        return passwordRecordList;

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<PasswordRecord> listPasswordRecordByAgentIdAndPassword(Long agentId,String password)
    {
        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from PasswordRecord where merchant=:merchant and passwordRecordPassword=:password";
        Query query = getSession().createQuery(sql);
        query.setEntity("merchant", merchant);
        query.setString("password",password);
        List<PasswordRecord> passwordRecordList = query.list();
        if (passwordRecordList.size() <= 0) {
            return null;
        }
        return passwordRecordList;

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public PasswordRecord addPasswordRecordByAgentId(Long passwordType,String password,Long agentId)
    {
        //Set mandatory field
        passwordRecord.setPasswordRecordPasswordType(passwordType);
        passwordRecord.setPasswordRecordPassword(password);
        passwordRecord.setMerchant(merchantDAO.getMerchantById(agentId));
        passwordRecord.setPasswordRecordTs(new java.sql.Date(System.currentTimeMillis()));

        //Set mandatory field
        return (PasswordRecord) this.read(this.create(passwordRecord));

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public PasswordRecord listAllByAgentIdWithLowestCTS(Long agentId)
    {
        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from PasswordRecord where merchant=:merchant";
        Query query = getSession().createQuery(sql);
        query.setEntity("merchant", merchant);
        List<PasswordRecord> passwordRecordList = query.list();
        if (passwordRecordList.size() <= 0) {
            return null;
        }

        Collections.sort(passwordRecordList, new Comparator<PasswordRecord>() {
            public int compare(PasswordRecord passwordRecord1, PasswordRecord passwordRecord2) {
                return passwordRecord1.getPasswordRecordTs().compareTo(passwordRecord2.getPasswordRecordTs());
            }
        });

        return passwordRecordList.get(0);
    }

}
