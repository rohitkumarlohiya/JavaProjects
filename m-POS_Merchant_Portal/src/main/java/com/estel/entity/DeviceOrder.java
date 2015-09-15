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
import javax.persistence.Table;


/**
 * The persistent class for the device_order database table.
 * 
 */
@Component
@Entity
@Table(name="device_order")
public class DeviceOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEVICE_ORDER_DEVICEORDERID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEVICE_ORDER_DEVICEORDERID_GENERATOR")
	@Column(name="device_order_id")
	private Long deviceOrderId;

	@Column(name="device_order_address_id")
	private Long deviceOrderAddressId;

	@Column(name="device_order_dt_id")
	private Long deviceOrderDtId;

	@Column(name="device_order_order_no")
	private String deviceOrderOrderNo;

	@Column(name="device_order_quantity")
	private Long deviceOrderQuantity;

	@Column(name="device_order_status")
	private Long deviceOrderStatus;

	@Column(name="device_order_ts")
	private Date deviceOrderTs;

	//bi-directional many-to-one association to Merchant
	@ManyToOne
	@JoinColumn(name="device_order_merchant_id")
	private Merchant merchant;

	public DeviceOrder() {
	}

	public Long getDeviceOrderId() {
		return this.deviceOrderId;
	}

	public void setDeviceOrderId(Long deviceOrderId) {
		this.deviceOrderId = deviceOrderId;
	}

	public Long getDeviceOrderAddressId() {
		return this.deviceOrderAddressId;
	}

	public void setDeviceOrderAddressId(Long deviceOrderAddressId) {
		this.deviceOrderAddressId = deviceOrderAddressId;
	}

	public Long getDeviceOrderDtId() {
		return this.deviceOrderDtId;
	}

	public void setDeviceOrderDtId(Long deviceOrderDtId) {
		this.deviceOrderDtId = deviceOrderDtId;
	}

	public String getDeviceOrderOrderNo() {
		return this.deviceOrderOrderNo;
	}

	public void setDeviceOrderOrderNo(String deviceOrderOrderNo) {
		this.deviceOrderOrderNo = deviceOrderOrderNo;
	}

	public Long getDeviceOrderQuantity() {
		return this.deviceOrderQuantity;
	}

	public void setDeviceOrderQuantity(Long deviceOrderQuantity) {
		this.deviceOrderQuantity = deviceOrderQuantity;
	}

	public Long getDeviceOrderStatus() {
		return this.deviceOrderStatus;
	}

	public void setDeviceOrderStatus(Long deviceOrderStatus) {
		this.deviceOrderStatus = deviceOrderStatus;
	}

	public Date getDeviceOrderTs() {
		return this.deviceOrderTs;
	}

	public void setDeviceOrderTs(Date deviceOrderTs) {
		this.deviceOrderTs = deviceOrderTs;
	}

	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

}