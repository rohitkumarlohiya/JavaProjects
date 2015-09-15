package com.estel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the device database table.
 * 
 */
@Component
@Entity
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;



   /* DEVICE_MERCHANT_ID INTEGER NOT NULL, -- MERCHANT
    DEVICE_TYPE_ID INTEGER NOT NULL, -- DEVICE type
    DEVICE_TERMINAL_ID VARCHAR(50),
    DEVICE_PHONE_ID VARCHAR(50),
    DEVICE_LANGUAGE_ID INTEGER,
    DEVICE_ACTIVATION_CODE VARCHAR(50),
    DEVICE_NUMBER VARCHAR(50) NOT NULL,
    DEVICE_WRONG_ATTEMPT_CTR INTEGER DEFAULT 0 NOT NULL,
    DEVICE_STATUS_ID INTEGER NOT NULL,
    DEVICE_ACTIVATION_TS DATETIME YEAR TO SECOND,
    DEVICE_DEACTIVATION_TS DATETIME YEAR TO SECOND,     /////
    DEVICE_TS DATETIME YEAR TO SECOND DEFAULT CURRENT YEAR TO SECOND NOT NULL*/




    @Id
	@SequenceGenerator(name="DEVICE_DEVICEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEVICE_DEVICEID_GENERATOR")
	@Column(name="device_id")       ////
	private Long deviceId;

	@Column(name="device_activation_code") ///
	private String deviceActivationCode;

	@Column(name="device_activation_ts")  ///
	private Date deviceActivationTs;

    	@Column(name="device_wrong_attempt_ctr")////
     private Long deviceAttemptCtr;

    @Column(name="device_deactivation_ts")////
    private Long deviceDeactivationTs;


/*	@Column(name="device_attempt_ctr")
    private Long deviceAttemptCtr;*/

	/*@Column(name="device_auto_refill")
	private String deviceAutoRefill;

	@Column(name="device_block_ts")
	private Date deviceBlockTs;

	@Column(name="device_cancel_ts")
	private Date deviceCancelTs;

	@Column(name="device_first_trans_ts")
	private Date deviceFirstTransTs;

	@Column(name="device_last_trans_ts")
	private Date deviceLastTransTs;

	@Column(name="device_login_ts")
	private Date deviceLoginTs;*/

	@Column(name="device_merchant_id") ///
	private Long deviceMerchantId;

	@Column(name="device_number")     ///
	private String deviceNumber;

	/*@Column(name="device_password_expiry_ts")
	private Date devicePasswordExpiryTs;*/

	@Column(name="device_phone_id")   ////
	private String devicePhoneId;

	@Column(name="device_terminal_id")   ////
	private String deviceTerminalId;

	@Column(name="device_ts")      ///
	private Date deviceTs;

	//bi-directional many-to-one association to DeviceType
	@ManyToOne
	@JoinColumn(name="device_type_id")     ////
	private DeviceType deviceType;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="device_language_id")  ///
	private Language language;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="device_status_id")    ///
	private Status status;

	public Device() {
	}

	public Long getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceActivationCode() {
		return this.deviceActivationCode;
	}

	public void setDeviceActivationCode(String deviceActivationCode) {
		this.deviceActivationCode = deviceActivationCode;
	}

	public Date getDeviceActivationTs() {
		return this.deviceActivationTs;
	}

	public void setDeviceActivationTs(Date deviceActivationTs) {
		this.deviceActivationTs = deviceActivationTs;
	}

	public Long getDeviceAttemptCtr() {
		return this.deviceAttemptCtr;
	}

	public void setDeviceAttemptCtr(Long deviceAttemptCtr) {
		this.deviceAttemptCtr = deviceAttemptCtr;
	}



	public Long getDeviceMerchantId() {
		return this.deviceMerchantId;
	}

	public void setDeviceMerchantId(Long deviceMerchantId) {
		this.deviceMerchantId = deviceMerchantId;
	}

	public String getDeviceNumber() {
		return this.deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	/*public Date getDevicePasswordExpiryTs() {
		return this.devicePasswordExpiryTs;
	}

	public void setDevicePasswordExpiryTs(Date devicePasswordExpiryTs) {
		this.devicePasswordExpiryTs = devicePasswordExpiryTs;
	}*/

	public String getDevicePhoneId() {
		return this.devicePhoneId;
	}

	public void setDevicePhoneId(String devicePhoneId) {
		this.devicePhoneId = devicePhoneId;
	}

	public String getDeviceTerminalId() {
		return this.deviceTerminalId;
	}

	public void setDeviceTerminalId(String deviceTerminalId) {
		this.deviceTerminalId = deviceTerminalId;
	}

	public Date getDeviceTs() {
		return this.deviceTs;
	}

	public void setDeviceTs(Date deviceTs) {
		this.deviceTs = deviceTs;
	}

	public DeviceType getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

    public Long getDeviceDeactivationTs() {
        return deviceDeactivationTs;
    }

    public void setDeviceDeactivationTs(Long deviceDeactivationTs) {
        this.deviceDeactivationTs = deviceDeactivationTs;
    }
}