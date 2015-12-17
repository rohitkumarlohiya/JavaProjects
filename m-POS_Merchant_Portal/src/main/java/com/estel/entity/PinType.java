package com.estel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the pin_type database table.
 * 
 */
@Component
@Entity
@Table(name="pin_type")
public class PinType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="PIN_TYPE_PINTYPEID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PIN_TYPE_PINTYPEID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pin_type_id")
	private Long pinTypeId;

	@Column(name="pin_type_abbr")
	private String pinTypeAbbr;

	@Column(name="pin_type_description")
	private String pinTypeDescription;

	@Column(name="pin_type_ts")
	private Date pinTypeTs;

	public PinType() {
	}

	public Long getPinTypeId() {
		return this.pinTypeId;
	}

	public void setPinTypeId(Long pinTypeId) {
		this.pinTypeId = pinTypeId;
	}

	public String getPinTypeAbbr() {
		return this.pinTypeAbbr;
	}

	public void setPinTypeAbbr(String pinTypeAbbr) {
		this.pinTypeAbbr = pinTypeAbbr;
	}

	public String getPinTypeDescription() {
		return this.pinTypeDescription;
	}

	public void setPinTypeDescription(String pinTypeDescription) {
		this.pinTypeDescription = pinTypeDescription;
	}

	public Date getPinTypeTs() {
		return this.pinTypeTs;
	}

	public void setPinTypeTs(Date pinTypeTs) {
		this.pinTypeTs = pinTypeTs;
	}

}