package com.estel.dto;

import com.estel.entity.DeviceOrder;
import com.estel.entity.DeviceType;
import com.estel.entity.State;
import com.estel.entity.Status;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ashutosh Sahu
 * Date: 7 Oct, 2013
 * Time: 4:13:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeviceOrderModel {

    private Long deviceType;
    private Long quantity;

    private String registeredOfficeAddress;
    private String stateRegisterOfficeAddr;
    private String cityRegisterOfficeAddr;
    private String zipCodeRegisterOfficeAddrr;
    private String registerOfficePhoneNumber;

    private String regisAdd;
    private String regisState;
    private String regisCity;
    private String regisZipCode;
    private String regisPhoneNumber;

    private boolean sameAsRegisteredAddress;


    private List<DeviceType> deviceTypeList;
    private List<DeviceOrder> deviceOrderList;
    private List<State> stateList;
    private List<Status>  statusList;

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getRegisteredOfficeAddress() {
        return registeredOfficeAddress;
    }

    public void setRegisteredOfficeAddress(String registeredOfficeAddress) {
        this.registeredOfficeAddress = registeredOfficeAddress;
    }

    public String getStateRegisterOfficeAddr() {
        return stateRegisterOfficeAddr;
    }

    public void setStateRegisterOfficeAddr(String stateRegisterOfficeAddr) {
        this.stateRegisterOfficeAddr = stateRegisterOfficeAddr;
    }

    public String getCityRegisterOfficeAddr() {
        return cityRegisterOfficeAddr;
    }

    public void setCityRegisterOfficeAddr(String cityRegisterOfficeAddr) {
        this.cityRegisterOfficeAddr = cityRegisterOfficeAddr;
    }

    public String getZipCodeRegisterOfficeAddrr() {
        return zipCodeRegisterOfficeAddrr;
    }

    public void setZipCodeRegisterOfficeAddrr(String zipCodeRegisterOfficeAddrr) {
        this.zipCodeRegisterOfficeAddrr = zipCodeRegisterOfficeAddrr;
    }

    public String getRegisterOfficePhoneNumber() {
        return registerOfficePhoneNumber;
    }

    public void setRegisterOfficePhoneNumber(String registerOfficePhoneNumber) {
        this.registerOfficePhoneNumber = registerOfficePhoneNumber;
    }

    public String getRegisAdd() {
        return regisAdd;
    }

    public void setRegisAdd(String regisAdd) {
        this.regisAdd = regisAdd;
    }

    public String getRegisState() {
        return regisState;
    }

    public void setRegisState(String regisState) {
        this.regisState = regisState;
    }

    public String getRegisCity() {
        return regisCity;
    }

    public void setRegisCity(String regisCity) {
        this.regisCity = regisCity;
    }

    public String getRegisZipCode() {
        return regisZipCode;
    }

    public void setRegisZipCode(String regisZipCode) {
        this.regisZipCode = regisZipCode;
    }

    public String getRegisPhoneNumber() {
        return regisPhoneNumber;
    }

    public void setRegisPhoneNumber(String regisPhoneNumber) {
        this.regisPhoneNumber = regisPhoneNumber;
    }

    public boolean isSameAsRegisteredAddress() {
        return sameAsRegisteredAddress;
    }

    public void setSameAsRegisteredAddress(boolean sameAsRegisteredAddress) {
        this.sameAsRegisteredAddress = sameAsRegisteredAddress;
    }

    public List<DeviceType> getDeviceTypeList() {
        return deviceTypeList;
    }

    public void setDeviceTypeList(List<DeviceType> deviceTypeList) {
        this.deviceTypeList = deviceTypeList;
    }

    public List<DeviceOrder> getDeviceOrderList() {
        return deviceOrderList;
    }

    public void setDeviceOrderList(List<DeviceOrder> deviceOrderList) {
        this.deviceOrderList = deviceOrderList;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }
}
