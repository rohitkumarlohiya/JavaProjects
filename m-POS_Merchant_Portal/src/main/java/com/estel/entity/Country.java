package com.estel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;


/**
 * The persistent class for the country database table.
 * 
 */
@Component
@Entity
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="COUNTRY_COUNTRYID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COUNTRY_COUNTRYID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="country_id")
	private Long countryId;

	@Column(name="country_code")
	private String countryCode;

	@Column(name="country_description")
	private String countryDescription;

	@Column(name="country_ts")
	private Date countryTs;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="country_status_id")
	private Status status;

	public Country() {
	}

	public Long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryDescription() {
		return this.countryDescription;
	}

	public void setCountryDescription(String countryDescription) {
		this.countryDescription = countryDescription;
	}

	public Date getCountryTs() {
		return this.countryTs;
	}

	public void setCountryTs(Date countryTs) {
		this.countryTs = countryTs;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}