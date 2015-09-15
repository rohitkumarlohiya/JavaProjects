package com.estel.service;

import com.estel.dao.DeviceOrderDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.DeviceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceOrderServiceImpl extends GenericDAOImpl<DeviceOrder, Long> implements
        DeviceOrderService {

    @Autowired
    private DeviceOrderDAO DeviceOrderDAO;


    public DeviceOrder addDeviceOrder(DeviceOrder DeviceOrder) {
        return DeviceOrderDAO.addDeviceOrder(DeviceOrder);
    }


    public DeviceOrder getDeviceOrderById(Long Id) {
        return DeviceOrderDAO.getDeviceOrderById(Id);
    }


    public List<DeviceOrder> listDeviceOrders() {
        return DeviceOrderDAO.listDeviceOrders();
    }


    public void updateDeviceOrder(DeviceOrder DeviceOrder) {
        DeviceOrderDAO.updateDeviceOrder(DeviceOrder);

    }


    public List<DeviceOrder> listDeviceOrderByAgentId(Long agentId) {
        return DeviceOrderDAO.listDeviceOrderByAgentId(agentId);

    }


    public DeviceOrder addDeviceOrderByAgentId(Long deviceTypeId, Long statusId, String orderNumber, Long agentId, Long quantity, Long addressId) {
        return DeviceOrderDAO.addDeviceOrderByAgentId(deviceTypeId, statusId, orderNumber, agentId, quantity, addressId);
    }


}
