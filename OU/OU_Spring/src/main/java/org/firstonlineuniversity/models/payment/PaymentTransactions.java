package org.firstonlineuniversity.models.payment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;

@Entity
@Table(name = "UN_PAYMENT_TRANSACTIONS", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PaymentTransactions extends AbstractEntity implements Serializable {

	public enum Status {
		SUCCESS, ERROR
	}

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSATION_TYPE")
	private TransactionType transactionType;
	
	@Column(name = "COURSE_ID")
	private Long courseId;
	
	@Column(name = "ACCOUNT_ID")
	private Long accountId;
	
	@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "AMMOUNT")
	private Double ammount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CURRENCY")
	private Currency currency;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ENROLLEMENT_ID")
	private Long enrollementId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status;

	public PaymentTransactions(TransactionType transactionType, Long courseId,
			Long accountId, String token, Double ammount, Currency currency,
			String description, Long enrollementId, Status status) {
		super();
		this.transactionType = transactionType;
		this.courseId = courseId;
		this.accountId = accountId;
		this.token = token;
		this.ammount = ammount;
		this.currency = currency;
		this.description = description;
		this.enrollementId = enrollementId;
		this.status = status;
	}

	public PaymentTransactions() {
		super();
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getEnrollementId() {
		return enrollementId;
	}

	public void setEnrollementId(Long enrollementId) {
		this.enrollementId = enrollementId;
	}
}
