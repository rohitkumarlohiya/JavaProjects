package com.estel.service;

import com.estel.dao.DeviceDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.dao.StatusDAO;
import com.estel.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl extends GenericDAOImpl<Device, Long> implements
        DeviceService {

    @Autowired
    private DeviceDAO DeviceDAO;

    @Autowired
    private StatusDAO statusDAO;


    public Device addDevice(Device Device) {
        return DeviceDAO.addDevice(Device);
    }


    public Device getDeviceById(Long Id) {
        return DeviceDAO.getDeviceById(Id);
    }


    public List<Device> listDevices() {
        return DeviceDAO.listDevices();
    }


    public void updateDevice(Device Device) {
        DeviceDAO.updateDevice(Device);

    }


    public List<Device> listDeviceByAgentId(Long agentId) {
        return DeviceDAO.listDeviceByAgentId(agentId);
    }


    public List<Device> listDeviceByPhoneNumber(String phoneNumber) {
        return DeviceDAO.listDeviceByPhoneNumber(phoneNumber);
    }


    public Device addDeviceByAgentId(Long agentId,
                                     Long deviceTypeId,
                                     String phoneNumber,
                                     Long statusId,
                                     String activationCode,
                                     String imeiNumber,String cardReaderSerialNo) {
        return DeviceDAO.addDeviceByAgentId(agentId, deviceTypeId, phoneNumber, statusId, activationCode, imeiNumber,cardReaderSerialNo );
    }


    public void updateDevice(Long id,
                             String macphonenumber,
                             Long statusId,
                             String imeiNumber,String cardReaderSerialNo) {
        DeviceDAO.updateDevice(id, macphonenumber, statusId, imeiNumber,cardReaderSerialNo);
    }


    public void updateDevice(Long id,
                             String macphonenumber,
                             Long statusId,
                             String activationCode,
                             String imeiNumber, String cardReaderSerialNo) {
        DeviceDAO.updateDevice(id, macphonenumber, statusId, activationCode, imeiNumber,cardReaderSerialNo);
    }

}
