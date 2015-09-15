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
 * The persistent class for the city database table.
 * 
 */
@Component
@Entity
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CITY_CITYID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CITY_CITYID_GENERATOR")
	@Column(name="city_id")
	private Long cityId;

	@Column(name="city_code")
	private String cityCode;

	@Column(name="city_description")
	private String cityDescription;

	@Column(name="city_state_id")
	private Long cityStateId;

	@Column(name="city_ts")
	private Date cityTs;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="city_status_id")
	private Status status;

	public City() {
	}

	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityDescription() {
		return this.cityDescription;
	}

	public void setCityDescription(String cityDescription) {
		this.cityDescription = cityDescription;
	}

	public Long getCityStateId() {
		return this.cityStateId;
	}

	public void setCityStateId(Long cityStateId) {
		this.cityStateId = cityStateId;
	}

	public Date getCityTs() {
		return this.cityTs;
	}

	public void setCityTs(Date cityTs) {
		this.cityTs = cityTs;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}