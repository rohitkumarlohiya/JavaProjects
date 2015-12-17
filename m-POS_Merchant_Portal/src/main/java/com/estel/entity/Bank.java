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
 * The persistent class for the bank database table.
 * 
 */
@Component
@Entity
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="BANK_BANKID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BANK_BANKID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bank_id")
	private Long bankId;

	@Column(name="bank_account_name")
	private String bankAccountName;

	@Column(name="bank_account_number")
	private String bankAccountNumber;

	@Column(name="bank_account_type_id")
	private Long bankAccountTypeId;

	@Column(name="bank_branch_code")
	private String bankBranchCode;

	@Column(name="bank_branch_name")
	private String bankBranchName;

	@Column(name="bank_merchant_id")
	private Long bankMerchantId;

	@Column(name="bank_name")
	private String bankName;

	@Column(name="bank_routing_no")
	private Long bankRoutingNo;

	@Column(name="bank_ts")
	private Date bankTs;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="bank_status_id")
	private Status status;

	public Bank() {
	}

	public Long getBankId() {
		return this.bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getBankAccountName() {
		return this.bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public Long getBankAccountTypeId() {
		return this.bankAccountTypeId;
	}

	public void setBankAccountTypeId(Long bankAccountTypeId) {
		this.bankAccountTypeId = bankAccountTypeId;
	}

	public String getBankBranchCode() {
		return this.bankBranchCode;
	}

	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}

	public String getBankBranchName() {
		return this.bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public Long getBankMerchantId() {
		return this.bankMerchantId;
	}

	public void setBankMerchantId(Long bankMerchantId) {
		this.bankMerchantId = bankMerchantId;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getBankRoutingNo() {
		return this.bankRoutingNo;
	}

	public void setBankRoutingNo(Long bankRoutingNo) {
		this.bankRoutingNo = bankRoutingNo;
	}

	public Date getBankTs() {
		return this.bankTs;
	}

	public void setBankTs(Date bankTs) {
		this.bankTs = bankTs;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}