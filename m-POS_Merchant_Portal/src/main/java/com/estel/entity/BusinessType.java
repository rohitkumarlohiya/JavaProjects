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
 * The persistent class for the business_type database table.
 * 
 */
@Component
@Entity
@Table(name="business_type")
public class BusinessType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BUSINESS_TYPE_BUSINESSTYPEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BUSINESS_TYPE_BUSINESSTYPEID_GENERATOR")
	@Column(name="business_type_id")
	private Long businessTypeId;

	@Column(name="business_type_code")
	private String businessTypeCode;

	@Column(name="business_type_description")
	private String businessTypeDescription;

	@Column(name="business_type_ts")
	private Date businessTypeTs;

	//bi-directional many-to-one association to Business
	@OneToMany(mappedBy="businessType")
	private List<Business> businesses;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="business_type_status_id")
	private Status status;

	public BusinessType() {
	}

	public Long getBusinessTypeId() {
		return this.businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getBusinessTypeCode() {
		return this.businessTypeCode;
	}

	public void setBusinessTypeCode(String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	public String getBusinessTypeDescription() {
		return this.businessTypeDescription;
	}

	public void setBusinessTypeDescription(String businessTypeDescription) {
		this.businessTypeDescription = businessTypeDescription;
	}

	public Date getBusinessTypeTs() {
		return this.businessTypeTs;
	}

	public void setBusinessTypeTs(Date businessTypeTs) {
		this.businessTypeTs = businessTypeTs;
	}

	public List<Business> getBusinesses() {
		return this.businesses;
	}

	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}

	public Business addBusiness(Business business) {
		getBusinesses().add(business);
		business.setBusinessType(this);

		return business;
	}

	public Business removeBusiness(Business business) {
		getBusinesses().remove(business);
		business.setBusinessType(null);

		return business;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}