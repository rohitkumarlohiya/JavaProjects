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
 * The persistent class for the state database table.
 * 
 */
@Component
@Entity
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATE_STATEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATE_STATEID_GENERATOR")
	@Column(name="state_id")
	private Long stateId;

	@Column(name="state_code")
	private String stateCode;

	@Column(name="state_country_id")
	private Long stateCountryId;

	@Column(name="state_description")
	private String stateDescription;

	@Column(name="state_ts")
	private Date stateTs;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="state_status_id")
	private Status status;

	public State() {
	}

	public Long getStateId() {
		return this.stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Long getStateCountryId() {
		return this.stateCountryId;
	}

	public void setStateCountryId(Long stateCountryId) {
		this.stateCountryId = stateCountryId;
	}

	public String getStateDescription() {
		return this.stateDescription;
	}

	public void setStateDescription(String stateDescription) {
		this.stateDescription = stateDescription;
	}

	public Date getStateTs() {
		return this.stateTs;
	}

	public void setStateTs(Date stateTs) {
		this.stateTs = stateTs;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}