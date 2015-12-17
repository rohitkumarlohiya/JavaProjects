package com.estel.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


/**
 * The persistent class for the audittrail database table.
 */
@Component
@Entity
public class Audittrail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    //@SequenceGenerator(name = "AUDITTRAIL_AUDITTRAILID_GENERATOR")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUDITTRAIL_AUDITTRAILID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "audittrail_id")
    private Long audittrailId;

    @Column(name = "audittrail_action_name")
    private String audittrailActionName;

    @Column(name = "audittrail_cts")
    private Date audittrailCts;

    @Column(name = "audittrail_description")
    private String audittrailDescription;

    @Column(name = "audittrail_ip")
    private String audittrailIp;

    @Column(name = "audittrail_interface")
    private String audittrailInterface;

    @Column(name = "audittrail_lpanel_tab_name")
    private String audittrailLpanelTabName;

    @Column(name = "audittrail_new_value")
    private String audittrailNewValue;

    @Column(name = "audittrail_old_value")
    private String audittrailOldValue;

    @Column(name = "audittrail_user_id")
    private Long audittrailUserId;

    public Audittrail() {
    }

    public Long getAudittrailId() {
        return this.audittrailId;
    }

    public void setAudittrailId(Long audittrailId) {
        this.audittrailId = audittrailId;
    }

    public String getAudittrailActionName() {
        return this.audittrailActionName;
    }

    public void setAudittrailActionName(String audittrailActionName) {
        this.audittrailActionName = audittrailActionName;
    }

    public Date getAudittrailCts() {
        return this.audittrailCts;
    }

    public void setAudittrailCts(Date audittrailCts) {
        this.audittrailCts = audittrailCts;
    }

    public String getAudittrailDescription() {
        return this.audittrailDescription;
    }

    public void setAudittrailDescription(String audittrailDescription) {
        this.audittrailDescription = audittrailDescription;
    }

    public String getAudittrailInterface() {
        return this.audittrailInterface;
    }

    public void setAudittrailInterface(String audittrailInterface) {
        this.audittrailInterface = audittrailInterface;
    }

    public String getAudittrailLpanelTabName() {
        return this.audittrailLpanelTabName;
    }

    public void setAudittrailLpanelTabName(String audittrailLpanelTabName) {
        this.audittrailLpanelTabName = audittrailLpanelTabName;
    }

    public String getAudittrailNewValue() {
        return this.audittrailNewValue;
    }

    public void setAudittrailNewValue(String audittrailNewValue) {
        this.audittrailNewValue = audittrailNewValue;
    }

    public String getAudittrailOldValue() {
        return this.audittrailOldValue;
    }

    public void setAudittrailOldValue(String audittrailOldValue) {
        this.audittrailOldValue = audittrailOldValue;
    }

    public Long getAudittrailUserId() {
        return this.audittrailUserId;
    }

    public void setAudittrailUserId(Long audittrailUserId) {
        this.audittrailUserId = audittrailUserId;
    }

    public String getAudittrailIp() {
        return audittrailIp;
    }

    public void setAudittrailIp(String audittrailIp) {
        this.audittrailIp = audittrailIp;
    }
}