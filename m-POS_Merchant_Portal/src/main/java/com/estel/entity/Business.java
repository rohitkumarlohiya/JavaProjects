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
 * The persistent class for the business database table.
 * 
 */
@Component
@Entity
public class Business implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="BUSINESS_BUSINESSID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BUSINESS_BUSINESSID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="business_id")
	private Long businessId;

	@Column(name="business_mark_name")
	private String businessMarkName;

	@Column(name="business_merchant_id")
	private Long businessMerchantId;

	@Column(name="business_name")
	private String businessName;

	@Column(name="mbbus_ts")
	private Date mbbusTs;

	//bi-directional many-to-one association to BusinessCategory
	@ManyToOne
	@JoinColumn(name="business_category_id")
	private BusinessCategory businessCategory;

	//bi-directional many-to-one association to BusinessOwnerType
	@ManyToOne
	@JoinColumn(name="business_owner_type_id")
	private BusinessOwnerType businessOwnerType;

	//bi-directional many-to-one association to BusinessType
	@ManyToOne
	@JoinColumn(name="business_type_id")
	private BusinessType businessType;

	public Business() {
	}

	public Long getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessMarkName() {
		return this.businessMarkName;
	}

	public void setBusinessMarkName(String businessMarkName) {
		this.businessMarkName = businessMarkName;
	}

	public Long getBusinessMerchantId() {
		return this.businessMerchantId;
	}

	public void setBusinessMerchantId(Long businessMerchantId) {
		this.businessMerchantId = businessMerchantId;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Date getMbbusTs() {
		return this.mbbusTs;
	}

	public void setMbbusTs(Date mbbusTs) {
		this.mbbusTs = mbbusTs;
	}

	public BusinessCategory getBusinessCategory() {
		return this.businessCategory;
	}

	public void setBusinessCategory(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}

	public BusinessOwnerType getBusinessOwnerType() {
		return this.businessOwnerType;
	}

	public void setBusinessOwnerType(BusinessOwnerType businessOwnerType) {
		this.businessOwnerType = businessOwnerType;
	}

	public BusinessType getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

}