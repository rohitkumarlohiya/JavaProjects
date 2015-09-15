package com.estel.dao;

import com.estel.entity.DeviceType;

import java.util.List;

public interface DeviceTypeDAO extends GenericDAO<DeviceType, Long> {

	public DeviceType addDeviceType(DeviceType DeviceType);
	public DeviceType getDeviceTypeById(Long Id);
	public List<DeviceType> listDeviceTypes();
	public void updateDeviceType(DeviceType DeviceType);
}
