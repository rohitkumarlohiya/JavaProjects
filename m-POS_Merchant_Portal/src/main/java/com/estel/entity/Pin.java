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
 * The persistent class for the pin database table.
 * 
 */
@Component
@Entity
public class Pin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="PIN_PINID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PIN_PINID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pin_id")
	private Long pinId;

	@Column(name="pin_device_id")
	private Long pinDeviceId;

	@Column(name="pin_expiry_date")
	private Date pinExpiryDate;

	@Column(name="pin_first_login")
	private String pinFirstLogin;

	@Column(name="pin_ts")
	private Date pinTs;

	@Column(name="pin_type_id")
	private Long pinTypeId;

    @Column(name="pin_wrong_attempt_ctr")
    private Long pinWrongAttemptCtr;

	@Column(name="pin_value")
	private String pinValue;

	//bi-directional many-to-one association to Merchant
	@ManyToOne
	@JoinColumn(name="pin_merchant_id")
	private Merchant merchant;

	public Pin() {
	}

	public Long getPinId() {
		return this.pinId;
	}

	public void setPinId(Long pinId) {
		this.pinId = pinId;
	}

	public Long getPinDeviceId() {
		return this.pinDeviceId;
	}

	public void setPinDeviceId(Long pinDeviceId) {
		this.pinDeviceId = pinDeviceId;
	}

	public Date getPinExpiryDate() {
		return this.pinExpiryDate;
	}

	public void setPinExpiryDate(Date pinExpiryDate) {
		this.pinExpiryDate = pinExpiryDate;
	}

	public String getPinFirstLogin() {
		return this.pinFirstLogin;
	}

	public void setPinFirstLogin(String pinFirstLogin) {
		this.pinFirstLogin = pinFirstLogin;
	}

	public Date getPinTs() {
		return this.pinTs;
	}

	public void setPinTs(Date pinTs) {
		this.pinTs = pinTs;
	}

	public Long getPinTypeId() {
		return this.pinTypeId;
	}

	public void setPinTypeId(Long pinTypeId) {
		this.pinTypeId = pinTypeId;
	}

	public String getPinValue() {
		return this.pinValue;
	}

	public void setPinValue(String pinValue) {
		this.pinValue = pinValue;
	}

	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

    public Long getPinWrongAttemptCtr() {
        return pinWrongAttemptCtr;
    }

    public void setPinWrongAttemptCtr(Long pinWrongAttemptCtr) {
        this.pinWrongAttemptCtr = pinWrongAttemptCtr;
    }
}