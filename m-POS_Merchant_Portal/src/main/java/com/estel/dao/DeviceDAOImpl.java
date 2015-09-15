package com.estel.dao;

import com.estel.entity.Device;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DeviceDAOImpl extends GenericDAOImpl<Device, Long> implements DeviceDAO {


    @Autowired
    MerchantDAO merchantDAO;

    @Autowired
    Device device;

    @Autowired
    DeviceTypeDAO deviceTypeDAO;

    @Autowired
    StatusDAO statusDAO;

    @Autowired
    LanguageDAO languageDAO;

    @Transactional
    public Device addDevice(Device Device) {
        return (Device) this.read(this.create(Device));
    }


    @Transactional
    public Device getDeviceById(Long Id) {
        return (Device) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<Device> listDevices() {
        return getSession().createQuery("from Device").list();
    }


    @Transactional
    public void updateDevice(Device Device) {
        getSession().update(Device);

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Device> listDeviceByAgentId(Long agentId) {
        String sql = "from Device where deviceMerchantId=:agentId";
        Query query = getSession().createQuery(sql);
        query.setLong("agentId", agentId);
        List<Device> deviceList = query.list();
        if (deviceList.size() <= 0) {
            return null;
        }
        return deviceList;

    }

    @Transactional
    public List<Device> listDeviceByPhoneNumber(String phoneNumber) {
        String sql = "from Device where deviceNumber=:phoneNumber";
        Query query = getSession().createQuery(sql);
        query.setString("phoneNumber", phoneNumber);
        List<Device> deviceList = query.list();
        if (deviceList.size() <= 0) {
            return null;
        }
        return deviceList;
    }


    @Transactional
    public Device addDeviceByAgentId(Long agentId,
                                     Long deviceTypeId,
                                     String phoneNumber,
                                     Long statusId,
                                     String activationCode,
                                     String imeiNumber, String cardReaderSerialNo) {
        //set mandatory field
        /*device_merchant_id
        device_type_id
        device_number
        device_status_id
        device_ts*/

        device.setDeviceMerchantId(agentId);
        device.setDeviceType(deviceTypeDAO.getDeviceTypeById(deviceTypeId));
        device.setDeviceNumber(phoneNumber);
        device.setStatus(statusDAO.getStatusById(statusId));
        device.setDeviceTs(new java.sql.Date(System.currentTimeMillis()));

        device.setDeviceAttemptCtr(0L);
       // device.setLanguage(languageDAO.getLanguageById(1L));
        //set mandatory field

        device.setDeviceActivationCode(activationCode);
        device.setDeviceTerminalId(imeiNumber);
        device.setDevicePhoneId(cardReaderSerialNo);

        return (Device) this.read(this.create(device));


    }

    @Transactional
    public void updateDevice(Long id,
                             String macphonenumber,
                             Long statusId,
                             String imeiNumber,String cardReaderSerialNo) {

        Device device = this.getDeviceById(id);
        device.setDeviceNumber(macphonenumber);
        device.setDeviceTerminalId(imeiNumber);
        device.setStatus(statusDAO.getStatusById(statusId));
        device.setDevicePhoneId(cardReaderSerialNo);

        getSession().update(device);
    }


    @Transactional
    public void updateDevice(Long id,
                             String macphonenumber,
                             Long statusId,
                             String activationCode,
                             String imeiNumber,String cardReaderSerialNo) {

        Device device = this.getDeviceById(id);
        device.setDeviceNumber(macphonenumber);
        device.setDeviceTerminalId(imeiNumber);
        device.setStatus(statusDAO.getStatusById(statusId));
        device.setDeviceActivationCode(activationCode);
        device.setDevicePhoneId(cardReaderSerialNo);

        getSession().update(device);
    }

}
