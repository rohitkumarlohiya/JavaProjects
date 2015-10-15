package org.firstonlineuniversity.models.payment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;

@Entity
@Table(name = "UN_CARDS", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cards extends AbstractEntity implements Serializable {

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	@JsonBackReference
	private Accounts accounts;

	@Column(name = "CARD_NUMBER", length = 60, nullable = false)
	private String cardNumber;

	@Column(name = "EXPIRATION_DATE", nullable = true)
	private Date expirationDate;

	@Column(name = "CARD_TYPE", length = 30, nullable = true)
	private String cardType;

	@Column(name = "CARD_CATEGORY", length = 60, nullable = true)
	private String cardCategory;

	@Column(name = "ACCOUNT_NAME", length = 240, nullable = true)
	private String accountName;

	@Column(name = "CARD_NAME", length = 240, nullable = true)
	private String cardName;

	@Column(name = "CARD_IDENTIFICATION_NUMBER", length = 10, nullable = true)
	private String cardIdentificationNumber;

	@Column(name = "ISSUER_NAME", length = 240, nullable = true)
	private String issuerName;

	@Column(name = "SECURE_CODE", length = 100, nullable = false)
	private String secureCode;

	@Column(name = "EXPIRATION_MONTH", nullable = false)
	private int expirationMonth;

	@Column(name = "EXPIRATION_YEAR", nullable = false)
	private int expirationYear;

	public Cards(Accounts accounts, String cardNumber, Date expirationDate,
			String cardType, String cardCategory, String accountName,
			String issuerName, String secureCode, int expirationMonth,
			int expirationYear, String cardName, String cardIdentificationNumber) {
		super();
		this.accounts = accounts;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.cardType = cardType;
		this.cardCategory = cardCategory;
		this.accountName = accountName;
		this.issuerName = issuerName;
		this.secureCode = secureCode;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.cardName = cardName;
		this.cardIdentificationNumber = cardIdentificationNumber;
	}

	public Cards() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardCategory() {
		return cardCategory;
	}

	public void setCardCategory(String cardCategory) {
		this.cardCategory = cardCategory;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getSecureCode() {
		return secureCode;
	}

	public void setSecureCode(String secureCode) {
		this.secureCode = secureCode;
	}

	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardIdentificationNumber() {
		return cardIdentificationNumber;
	}

	public void setCardIdentificationNumber(String cardIdentificationNumber) {
		this.cardIdentificationNumber = cardIdentificationNumber;
	}
}
