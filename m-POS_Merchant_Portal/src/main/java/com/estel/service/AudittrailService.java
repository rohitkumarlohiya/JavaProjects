package com.estel.service;

import com.estel.entity.Audittrail;

import java.util.List;


public interface AudittrailService {

    public Audittrail addAudittrail(Audittrail Audittrail);

    public Audittrail getAudittrailById(Long Id);

    public List<Audittrail> listAudittrails();

    public void updateAudittrail(Audittrail Audittrail);

    public Audittrail addAudittrailByUserId(Long userId, String lpanelTabName, String actionName, String ip, String description, String old_value, String new_value);


}
