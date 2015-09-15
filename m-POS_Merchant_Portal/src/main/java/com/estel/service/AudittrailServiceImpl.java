package com.estel.service;

import com.estel.dao.AudittrailDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.Audittrail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudittrailServiceImpl extends GenericDAOImpl<Audittrail, Long> implements
        AudittrailService {

    @Autowired
    private AudittrailDAO AudittrailDAO;


    public Audittrail addAudittrail(Audittrail Audittrail) {
        return AudittrailDAO.addAudittrail(Audittrail);
    }


    public Audittrail getAudittrailById(Long Id) {
        return AudittrailDAO.getAudittrailById(Id);
    }


    public List<Audittrail> listAudittrails() {
        return AudittrailDAO.listAudittrails();
    }


    public void updateAudittrail(Audittrail Audittrail) {
        AudittrailDAO.updateAudittrail(Audittrail);

    }

    public Audittrail addAudittrailByUserId(Long userId, String lpanelTabName, String actionName, String ip, String description, String old_value, String new_value) {
        return AudittrailDAO.addAudittrailByUserId(userId, lpanelTabName,actionName, ip, description, old_value, new_value);
    }
}
