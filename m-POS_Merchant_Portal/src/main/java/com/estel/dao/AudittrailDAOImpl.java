package com.estel.dao;

import com.estel.entity.Audittrail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AudittrailDAOImpl extends GenericDAOImpl<Audittrail, Long> implements AudittrailDAO {

    @Autowired
    Audittrail audittrail;

    @Transactional
    public Audittrail addAudittrail(Audittrail Audittrail) {
        return (Audittrail) this.read(this.create(Audittrail));
    }


    @Transactional
    public Audittrail getAudittrailById(Long Id) {
        return (Audittrail) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<Audittrail> listAudittrails() {
        return getSession().createQuery("from Audittrail").list();
    }


    @Transactional
    public void updateAudittrail(Audittrail Audittrail) {
        getSession().update(Audittrail);

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Audittrail addAudittrailByUserId(Long userId, String lpanelTabName, String actionName, String ip, String description, String old_value, String new_value) {

        //mandatory
        audittrail.setAudittrailUserId(userId);
        audittrail.setAudittrailLpanelTabName(lpanelTabName);
        audittrail.setAudittrailActionName(actionName);
        audittrail.setAudittrailIp(ip);
        audittrail.setAudittrailCts(new java.sql.Date(System.currentTimeMillis()));
        //mandatory

        audittrail.setAudittrailInterface("Merchant Portal");
        audittrail.setAudittrailDescription(description);
        audittrail.setAudittrailOldValue(old_value);
        audittrail.setAudittrailNewValue(new_value);

        return (Audittrail) this.read(this.create(audittrail));
    }
}
