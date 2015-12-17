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
 * The persistent class for the transaction_type database table.
 * 
 */
@Component
@Entity
@Table(name="transaction_type")
public class TransactionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="TRANSACTION_TYPE_TRANSACTIONTYPEID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSACTION_TYPE_TRANSACTIONTYPEID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="transaction_type_id")
	private Long transactionTypeId;

	@Column(name="transaction_type_code")
	private String transactionTypeCode;

	@Column(name="transaction_type_description")
	private String transactionTypeDescription;

	@Column(name="transaction_type_ts")
	private Date transactionTypeTs;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="transaction_type_status_id")
	private Status status;

	public TransactionType() {
	}

	public Long getTransactionTypeId() {
		return this.transactionTypeId;
	}

	public void setTransactionTypeId(Long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public String getTransactionTypeCode() {
		return this.transactionTypeCode;
	}

	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	public String getTransactionTypeDescription() {
		return this.transactionTypeDescription;
	}

	public void setTransactionTypeDescription(String transactionTypeDescription) {
		this.transactionTypeDescription = transactionTypeDescription;
	}

	public Date getTransactionTypeTs() {
		return this.transactionTypeTs;
	}

	public void setTransactionTypeTs(Date transactionTypeTs) {
		this.transactionTypeTs = transactionTypeTs;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}