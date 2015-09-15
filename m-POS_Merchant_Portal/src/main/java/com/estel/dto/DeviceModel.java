package com.estel.dto;

import com.estel.entity.Device;
import com.estel.entity.Status;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 13/9/13
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeviceModel {

    private String strError = null;
    private Long deviceId;
    private String deviceNumber;
    private String IMEINumber;
    private Long deviceStatus;
    private List<Device> deviceList;
    private List<Status> deviceStatusList;
    private String pin;

    private String cardReaderSerialNo;

    public String getCardReaderSerialNo() {
        return cardReaderSerialNo;
    }

    public void setCardReaderSerialNo(String cardReaderSerialNo) {
        this.cardReaderSerialNo = cardReaderSerialNo;
    }

    public String getStrError() {
        return strError;
    }

    public void setStrError(String strError) {
        this.strError = strError;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getIMEINumber() {
        return IMEINumber;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public Long getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Long deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<Status> getDeviceStatusList() {
        return deviceStatusList;
    }

    public void setDeviceStatusList(List<Status> deviceStatusList) {
        this.deviceStatusList = deviceStatusList;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
