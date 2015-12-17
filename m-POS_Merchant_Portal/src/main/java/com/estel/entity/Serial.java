package com.estel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the serial database table.
 * 
 */
@Component
@Entity
public class Serial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="SERIAL_SERIALID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERIAL_SERIALID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="serial_id")
	private Long serialId;

	public Serial() {
	}

	public Long getSerialId() {
		return this.serialId;
	}

	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

}