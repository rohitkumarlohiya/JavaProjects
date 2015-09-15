package com.estel.dao;

import com.estel.entity.Device;

import java.util.List;

public interface DeviceDAO extends GenericDAO<Device, Long> {

    public Device addDevice(Device Device);

    public Device getDeviceById(Long Id);

    public List<Device> listDevices();

    public void updateDevice(Device Device);

    public List<Device> listDeviceByAgentId(Long agentId);

    public List<Device> listDeviceByPhoneNumber(String phoneNumber);

    public Device addDeviceByAgentId(Long agentId,
                                     Long deviceTypeId,
                                     String phoneNumber,
                                     Long statusId,
                                     String activationCode,
                                     String imeiNumber, String cardReaderSerialNo);

    public void updateDevice(Long id,
                             String macphonenumber,
                             Long statusId,
                             String imeiNumber,String cardReaderSerialNo);

    public void updateDevice(Long id,
                             String macphonenumber,
                             Long statusId,
                             String activationCode,
                             String imeiNumber,String cardReaderSerialNo);
}
