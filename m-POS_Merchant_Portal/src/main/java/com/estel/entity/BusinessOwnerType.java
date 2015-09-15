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
 * The persistent class for the business_owner_type database table.
 * 
 */
@Component
@Entity
@Table(name="business_owner_type")
public class BusinessOwnerType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BUSINESS_OWNER_TYPE_BUSINESSOWNERTYPEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BUSINESS_OWNER_TYPE_BUSINESSOWNERTYPEID_GENERATOR")
	@Column(name="business_owner_type_id")
	private Long businessOwnerTypeId;

	@Column(name="business_owner_type_code")
	private String businessOwnerTypeCode;

	@Column(name="business_owner_type_description")
	private String businessOwnerTypeDescription;

	@Column(name="business_owner_type_ts")
	private Date businessOwnerTypeTs;

	//bi-directional many-to-one association to Business
	@OneToMany(mappedBy="businessOwnerType")
	private List<Business> businesses;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="business_owner_type_status_id")
	private Status status;

	public BusinessOwnerType() {
	}

	public Long getBusinessOwnerTypeId() {
		return this.businessOwnerTypeId;
	}

	public void setBusinessOwnerTypeId(Long businessOwnerTypeId) {
		this.businessOwnerTypeId = businessOwnerTypeId;
	}

	public String getBusinessOwnerTypeCode() {
		return this.businessOwnerTypeCode;
	}

	public void setBusinessOwnerTypeCode(String businessOwnerTypeCode) {
		this.businessOwnerTypeCode = businessOwnerTypeCode;
	}

	public String getBusinessOwnerTypeDescription() {
		return this.businessOwnerTypeDescription;
	}

	public void setBusinessOwnerTypeDescription(String businessOwnerTypeDescription) {
		this.businessOwnerTypeDescription = businessOwnerTypeDescription;
	}

	public Date getBusinessOwnerTypeTs() {
		return this.businessOwnerTypeTs;
	}

	public void setBusinessOwnerTypeTs(Date businessOwnerTypeTs) {
		this.businessOwnerTypeTs = businessOwnerTypeTs;
	}

	public List<Business> getBusinesses() {
		return this.businesses;
	}

	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}

	public Business addBusiness(Business business) {
		getBusinesses().add(business);
		business.setBusinessOwnerType(this);

		return business;
	}

	public Business removeBusiness(Business business) {
		getBusinesses().remove(business);
		business.setBusinessOwnerType(null);

		return business;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}