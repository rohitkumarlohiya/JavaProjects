package com.estel.dto;

import com.estel.entity.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rohit
 * Date: 9/5/13
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class BusinessInfoModel {

    private Long businessInfoId;
    private Long merchantId;

    private String legalBusinessName;
    private String marketingName;
    private Long ownershipType;
    private Long businessCategory;
    private Long businessType;

    private String businessAddress;
    //private Long stateBusinessAddr;

    private String stateBusinessAddr;
    private String cityBusinessAddr;
    private String zipCodeBusinessAddr;
    private String businessPhoneNumber;

    private boolean sameBusinessAddress;

    private String registeredOfficeAddress;
    //private Long stateRegisterOfficeAddr;
    private String stateRegisterOfficeAddr;
    private String cityRegisterOfficeAddr;
    private String zipCodeRegisterOfficeAddrr;
    private String registerOfficePhoneNumber;


    private String emailBusiness;
    private String panNumberBusiness;


    private List<State> stateList;
    private List<City> cityList;
    private List<City> cityList2;
    private List<Country> countryList;

    private List<BusinessOwnerType> businessOwnerTypeList;
    private  List<BusinessType> businessTypeList;
    private List<BusinessCategory> businessCategoryList;



    private String regisAdd;
    private String regisState;
    private String regisCity;
    private String regisZipCode;
    private String regisPhoneNumber;


    public Long getBusinessInfoId() {
        return businessInfoId;
    }

    public void setBusinessInfoId(Long businessInfoId) {
        this.businessInfoId = businessInfoId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getLegalBusinessName() {
        return legalBusinessName;
    }

    public void setLegalBusinessName(String legalBusinessName) {
        this.legalBusinessName = legalBusinessName;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public Long getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(Long ownershipType) {
        this.ownershipType = ownershipType;
    }

    public Long getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(Long businessCategory) {
        this.businessCategory = businessCategory;
    }

    public Long getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Long businessType) {
        this.businessType = businessType;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }



    public String getCityBusinessAddr() {
        return cityBusinessAddr;
    }

    public void setCityBusinessAddr(String cityBusinessAddr) {
        this.cityBusinessAddr = cityBusinessAddr;
    }

    public String getZipCodeBusinessAddr() {
        return zipCodeBusinessAddr;
    }

    public void setZipCodeBusinessAddr(String zipCodeBusinessAddr) {
        this.zipCodeBusinessAddr = zipCodeBusinessAddr;
    }

    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }

    public boolean isSameBusinessAddress() {
        return sameBusinessAddress;
    }

    public void setSameBusinessAddress(boolean sameBusinessAddress) {
        this.sameBusinessAddress = sameBusinessAddress;
    }

    public String getRegisteredOfficeAddress() {
        return registeredOfficeAddress;
    }

    public void setRegisteredOfficeAddress(String registeredOfficeAddress) {
        this.registeredOfficeAddress = registeredOfficeAddress;
    }

    public String getStateBusinessAddr() {
        return stateBusinessAddr;
    }

    public void setStateBusinessAddr(String stateBusinessAddr) {
        this.stateBusinessAddr = stateBusinessAddr;
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

    public String getEmailBusiness() {
        return emailBusiness;
    }

    public void setEmailBusiness(String emailBusiness) {
        this.emailBusiness = emailBusiness;
    }

    public String getPanNumberBusiness() {
        return panNumberBusiness;
    }

    public void setPanNumberBusiness(String panNumberBusiness) {
        this.panNumberBusiness = panNumberBusiness;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<City> getCityList2() {
        return cityList2;
    }

    public void setCityList2(List<City> cityList2) {
        this.cityList2 = cityList2;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<BusinessOwnerType> getBusinessOwnerTypeList() {
        return businessOwnerTypeList;
    }

    public void setBusinessOwnerTypeList(List<BusinessOwnerType> businessOwnerTypeList) {
        this.businessOwnerTypeList = businessOwnerTypeList;
    }

    public List<BusinessType> getBusinessTypeList() {
        return businessTypeList;
    }

    public void setBusinessTypeList(List<BusinessType> businessTypeList) {
        this.businessTypeList = businessTypeList;
    }

    public List<BusinessCategory> getBusinessCategoryList() {
        return businessCategoryList;
    }

    public void setBusinessCategoryList(List<BusinessCategory> businessCategoryList) {
        this.businessCategoryList = businessCategoryList;
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
}
