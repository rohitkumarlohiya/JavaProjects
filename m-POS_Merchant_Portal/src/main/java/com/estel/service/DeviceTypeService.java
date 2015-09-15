package com.estel.service;

import com.estel.entity.DeviceType;

import java.util.List;


public interface DeviceTypeService {

	public DeviceType addDeviceType(DeviceType DeviceType);
	public DeviceType getDeviceTypeById(Long Id);
	public List<DeviceType> listDeviceTypes();
	public void updateDeviceType(DeviceType DeviceType);
}
