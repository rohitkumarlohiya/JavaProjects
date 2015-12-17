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


/**
 * The persistent class for the language database table.
 * 
 */
@Component
@Entity
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="LANGUAGE_LANGUAGEID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LANGUAGE_LANGUAGEID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="language_id")
	private Long languageId;

	@Column(name="language_code")
	private String languageCode;

	@Column(name="language_description")
	private String languageDescription;

	@Column(name="language_ts")
	private Date languageTs;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="language")
	private List<Device> devices;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="language_status_id")
	private Status status;

	public Language() {
	}

	public Long getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageDescription() {
		return this.languageDescription;
	}

	public void setLanguageDescription(String languageDescription) {
		this.languageDescription = languageDescription;
	}

	public Date getLanguageTs() {
		return this.languageTs;
	}

	public void setLanguageTs(Date languageTs) {
		this.languageTs = languageTs;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setLanguage(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setLanguage(null);

		return device;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}