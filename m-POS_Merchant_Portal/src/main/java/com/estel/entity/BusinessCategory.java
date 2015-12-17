package com.estel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the business_category database table.
 * 
 */
@Component
@Entity
@Table(name="business_category")
public class BusinessCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="BUSINESS_CATEGORY_BUSINESSCATEGORYID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BUSINESS_CATEGORY_BUSINESSCATEGORYID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="business_category_id")
	private Long businessCategoryId;

	@Column(name="business_category_code")
	private String businessCategoryCode;

	@Column(name="business_category_description")
	private String businessCategoryDescription;

	@Column(name="business_category_ts")
	private Date businessCategoryTs;

	//bi-directional many-to-one association to Business
	@OneToMany(mappedBy="businessCategory")
	private List<Business> businesses;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="business_category_status_id")
	private Status status;

	public BusinessCategory() {
	}

	public Long getBusinessCategoryId() {
		return this.businessCategoryId;
	}

	public void setBusinessCategoryId(Long businessCategoryId) {
		this.businessCategoryId = businessCategoryId;
	}

	public String getBusinessCategoryCode() {
		return this.businessCategoryCode;
	}

	public void setBusinessCategoryCode(String businessCategoryCode) {
		this.businessCategoryCode = businessCategoryCode;
	}

	public String getBusinessCategoryDescription() {
		return this.businessCategoryDescription;
	}

	public void setBusinessCategoryDescription(String businessCategoryDescription) {
		this.businessCategoryDescription = businessCategoryDescription;
	}

	public Date getBusinessCategoryTs() {
		return this.businessCategoryTs;
	}

	public void setBusinessCategoryTs(Date businessCategoryTs) {
		this.businessCategoryTs = businessCategoryTs;
	}

	public List<Business> getBusinesses() {
		return this.businesses;
	}

	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}

	public Business addBusiness(Business business) {
		getBusinesses().add(business);
		business.setBusinessCategory(this);

		return business;
	}

	public Business removeBusiness(Business business) {
		getBusinesses().remove(business);
		business.setBusinessCategory(null);

		return business;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}