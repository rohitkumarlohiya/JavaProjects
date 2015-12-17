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
 * The persistent class for the bank_account_type database table.
 * 
 */
@Component
@Entity
@Table(name="bank_account_type")
public class BankAccountType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="BANK_ACCOUNT_TYPE_BANKACCOUNTTYPEID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BANK_ACCOUNT_TYPE_BANKACCOUNTTYPEID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bank_account_type_id")
	private Long bankAccountTypeId;

	@Column(name="bank_account_type_bank_id")
	private Long bankAccountTypeBankId;

	@Column(name="bank_account_type_code")
	private String bankAccountTypeCode;

	@Column(name="bank_account_type_name")
	private String bankAccountTypeName;

	@Column(name="bank_account_type_ts")
	private Date bankAccountTypeTs;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="bank_account_type_status_id")
	private Status status;

	public BankAccountType() {
	}

	public Long getBankAccountTypeId() {
		return this.bankAccountTypeId;
	}

	public void setBankAccountTypeId(Long bankAccountTypeId) {
		this.bankAccountTypeId = bankAccountTypeId;
	}

	public Long getBankAccountTypeBankId() {
		return this.bankAccountTypeBankId;
	}

	public void setBankAccountTypeBankId(Long bankAccountTypeBankId) {
		this.bankAccountTypeBankId = bankAccountTypeBankId;
	}

	public String getBankAccountTypeCode() {
		return this.bankAccountTypeCode;
	}

	public void setBankAccountTypeCode(String bankAccountTypeCode) {
		this.bankAccountTypeCode = bankAccountTypeCode;
	}

	public String getBankAccountTypeName() {
		return this.bankAccountTypeName;
	}

	public void setBankAccountTypeName(String bankAccountTypeName) {
		this.bankAccountTypeName = bankAccountTypeName;
	}

	public Date getBankAccountTypeTs() {
		return this.bankAccountTypeTs;
	}

	public void setBankAccountTypeTs(Date bankAccountTypeTs) {
		this.bankAccountTypeTs = bankAccountTypeTs;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}