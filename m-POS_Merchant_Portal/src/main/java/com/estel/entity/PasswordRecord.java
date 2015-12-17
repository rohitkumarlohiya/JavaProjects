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
import javax.persistence.Table;


/**
 * The persistent class for the password_record database table.
 * 
 */
@Component
@Entity
@Table(name="password_record")
public class PasswordRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="PASSWORD_RECORD_PASSWORDRECORDID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PASSWORD_RECORD_PASSWORDRECORDID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="password_record_id")
	private Long passwordRecordId;

	@Column(name="password_record_device_id")
	private Long passwordRecordDeviceId;

	@Column(name="password_record_password")
	private String passwordRecordPassword;

	@Column(name="password_record_password_type")
	private Long passwordRecordPasswordType;

	@Column(name="password_record_ts")
	private Date passwordRecordTs;

	//bi-directional many-to-one association to Merchant
	@ManyToOne
	@JoinColumn(name="password_record_merchant_id")
	private Merchant merchant;

	public PasswordRecord() {
	}

	public Long getPasswordRecordId() {
		return this.passwordRecordId;
	}

	public void setPasswordRecordId(Long passwordRecordId) {
		this.passwordRecordId = passwordRecordId;
	}

	public Long getPasswordRecordDeviceId() {
		return this.passwordRecordDeviceId;
	}

	public void setPasswordRecordDeviceId(Long passwordRecordDeviceId) {
		this.passwordRecordDeviceId = passwordRecordDeviceId;
	}

	public String getPasswordRecordPassword() {
		return this.passwordRecordPassword;
	}

	public void setPasswordRecordPassword(String passwordRecordPassword) {
		this.passwordRecordPassword = passwordRecordPassword;
	}

	public Long getPasswordRecordPasswordType() {
		return this.passwordRecordPasswordType;
	}

	public void setPasswordRecordPasswordType(Long passwordRecordPasswordType) {
		this.passwordRecordPasswordType = passwordRecordPasswordType;
	}

	public Date getPasswordRecordTs() {
		return this.passwordRecordTs;
	}

	public void setPasswordRecordTs(Date passwordRecordTs) {
		this.passwordRecordTs = passwordRecordTs;
	}

	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

}