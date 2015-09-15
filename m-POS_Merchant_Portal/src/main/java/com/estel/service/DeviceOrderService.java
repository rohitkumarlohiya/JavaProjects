package com.estel.service;

import com.estel.entity.DeviceOrder;

import java.util.List;


public interface DeviceOrderService {

	public DeviceOrder addDeviceOrder(DeviceOrder DeviceOrder);
	public DeviceOrder getDeviceOrderById(Long Id);
	public List<DeviceOrder> listDeviceOrders();
	public void updateDeviceOrder(DeviceOrder DeviceOrder);

    public List<DeviceOrder> listDeviceOrderByAgentId(Long agentId);
    public DeviceOrder addDeviceOrderByAgentId(Long deviceTypeId,Long statusId,String orderNumber,Long agentId,Long quantity,Long addressId);
}
