package com.estel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the chargeslip database table.
 * 
 */
@Component
@Entity
public class Chargeslip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="CHARGESLIP_CHARGESLIPID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHARGESLIP_CHARGESLIPID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="chargeslip_id")
	private Long chargeslipId;

	@Column(name="chargeslip_amount")
	private String chargeslipAmount;

	@Lob
	@Column(name="chargeslip_byte")
	private byte[] chargeslipByte;

	@Column(name="chargeslip_last_four_digit")
	private String chargeslipLastFourDigit;

	@Column(name="chargeslip_response_ts")
	private Date chargeslipResponseTs;

	@Column(name="chargeslip_status")
	private Long chargeslipStatus;

	@Column(name="chargeslip_ts")
	private Date chargeslipTs;

	@Column(name="chargeslip_txn_id")
	private String chargeslipTxnId;

    @Column(name="chargeslip_merchant_id")
    private Long chargeslipMerchantId;

    public Long getChargeslipMerchantId() {
        return chargeslipMerchantId;
    }

    public void setChargeslipMerchantId(Long chargeslipMerchantId) {
        this.chargeslipMerchantId = chargeslipMerchantId;
    }

    public Chargeslip() {
	}

	public Long getChargeslipId() {
		return this.chargeslipId;
	}

	public void setChargeslipId(Long chargeslipId) {
		this.chargeslipId = chargeslipId;
	}

	public String getChargeslipAmount() {
		return this.chargeslipAmount;
	}

	public void setChargeslipAmount(String chargeslipAmount) {
		this.chargeslipAmount = chargeslipAmount;
	}

	public byte[] getChargeslipByte() {
		return this.chargeslipByte;
	}

	public void setChargeslipByte(byte[] chargeslipByte) {
		this.chargeslipByte = chargeslipByte;
	}

	public String getChargeslipLastFourDigit() {
		return this.chargeslipLastFourDigit;
	}

	public void setChargeslipLastFourDigit(String chargeslipLastFourDigit) {
		this.chargeslipLastFourDigit = chargeslipLastFourDigit;
	}

	public Date getChargeslipResponseTs() {
		return this.chargeslipResponseTs;
	}

	public void setChargeslipResponseTs(Date chargeslipResponseTs) {
		this.chargeslipResponseTs = chargeslipResponseTs;
	}

	public Long getChargeslipStatus() {
		return this.chargeslipStatus;
	}

	public void setChargeslipStatus(Long chargeslipStatus) {
		this.chargeslipStatus = chargeslipStatus;
	}

	public Date getChargeslipTs() {
		return this.chargeslipTs;
	}

	public void setChargeslipTs(Date chargeslipTs) {
		this.chargeslipTs = chargeslipTs;
	}

	public String getChargeslipTxnId() {
		return this.chargeslipTxnId;
	}

	public void setChargeslipTxnId(String chargeslipTxnId) {
		this.chargeslipTxnId = chargeslipTxnId;
	}

}