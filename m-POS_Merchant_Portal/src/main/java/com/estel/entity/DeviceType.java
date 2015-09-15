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
 * The persistent class for the device_type database table.
 * 
 */
@Component
@Entity
@Table(name="device_type")
public class DeviceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEVICE_TYPE_DEVICETYPEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEVICE_TYPE_DEVICETYPEID_GENERATOR")
	@Column(name="device_type_id")
	private Long deviceTypeId;

	@Column(name="device_type_code")
	private String deviceTypeCode;

	@Column(name="device_type_description")
	private String deviceTypeDescription;

	@Column(name="device_type_ts")
	private Date deviceTypeTs;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="deviceType")
	private List<Device> devices;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="device_type_status_id")
	private Status status;

	public DeviceType() {
	}

	public Long getDeviceTypeId() {
		return this.deviceTypeId;
	}

	public void setDeviceTypeId(Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceTypeCode() {
		return this.deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getDeviceTypeDescription() {
		return this.deviceTypeDescription;
	}

	public void setDeviceTypeDescription(String deviceTypeDescription) {
		this.deviceTypeDescription = deviceTypeDescription;
	}

	public Date getDeviceTypeTs() {
		return this.deviceTypeTs;
	}

	public void setDeviceTypeTs(Date deviceTypeTs) {
		this.deviceTypeTs = deviceTypeTs;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setDeviceType(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setDeviceType(null);

		return device;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}