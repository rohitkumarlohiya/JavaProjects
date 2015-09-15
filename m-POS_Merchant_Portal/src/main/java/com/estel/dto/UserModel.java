package com.estel.dto;

import com.estel.entity.*;

import java.util.List;


public class UserModel {

    private String error;
    private List<User> userList;
    private Integer userListSize;
    private Long country; // use country id
    private Long state; // use state id
    private Long circle; // use circle id
    private String role;
    private Long agent; // use agent id
    private Long reportGroup;
    private String pageAction;

    private String name;

    private String rpassword;
    private String firstName;

    private String surName;
    private String email;
    private String description;
    private String list;
    private Long id;
    private Long userId;
    private User userLogin;
    private Integer nameId;
    private String addroles;
    private String roles;
    private String submenu;
    private Long status; // use status id
    private Long agentIdSel;
    private String agentNameSel;
    private Long hierarchy;
    private Long agentTypeId;
    private Long agentTypeIdSel;
    private String code;
    private Integer record;
    private String hide;
    private String agentExist;
    List<Role> roleList;
    private String agentTypeExist;
    private String editNotify;
    private List<State>  stateList;
    private List<City> cityList;
    private List<City> cityList2;
    private List<Country> countryList;



    private String contactName;
    private String residentialAddress;
    private String residentialCountry;
    private String residentialState;
    private String residentialStateName;
    private String residentialCity;
    private String residentialZipCode;
    private String mobileNumber;
    private String alternateMobileNumber;
    private boolean sameAddressOffnHome;
    private String permanentAddress;
    private String permanentCountry;
    private String permanentState;
    private String permanentCity;
    private String permanentZipCode;
    private String gender;
    private String password;
    private String oldPassword;
    private String confirmPassword;
    private String oldPin;
    private String pin;
    private String confirmPin;


    private String permAdd;
    private String permState;
    private String permCity;
    private String permZipCode;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Integer getUserListSize() {
        return userListSize;
    }

    public void setUserListSize(Integer userListSize) {
        this.userListSize = userListSize;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getCircle() {
        return circle;
    }

    public void setCircle(Long circle) {
        this.circle = circle;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getAgent() {
        return agent;
    }

    public void setAgent(Long agent) {
        this.agent = agent;
    }

    public Long getReportGroup() {
        return reportGroup;
    }

    public void setReportGroup(Long reportGroup) {
        this.reportGroup = reportGroup;
    }

    public String getPageAction() {
        return pageAction;
    }

    public void setPageAction(String pageAction) {
        this.pageAction = pageAction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }




    public User getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public String getAddroles() {
        return addroles;
    }

    public void setAddroles(String addroles) {
        this.addroles = addroles;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getAgentIdSel() {
        return agentIdSel;
    }

    public void setAgentIdSel(Long agentIdSel) {
        this.agentIdSel = agentIdSel;
    }

    public String getAgentNameSel() {
        return agentNameSel;
    }

    public void setAgentNameSel(String agentNameSel) {
        this.agentNameSel = agentNameSel;
    }

    public Long getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Long hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Long getAgentTypeId() {
        return agentTypeId;
    }

    public void setAgentTypeId(Long agentTypeId) {
        this.agentTypeId = agentTypeId;
    }

    public Long getAgentTypeIdSel() {
        return agentTypeIdSel;
    }

    public void setAgentTypeIdSel(Long agentTypeIdSel) {
        this.agentTypeIdSel = agentTypeIdSel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public String getAgentExist() {
        return agentExist;
    }

    public void setAgentExist(String agentExist) {
        this.agentExist = agentExist;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getAgentTypeExist() {
        return agentTypeExist;
    }

    public void setAgentTypeExist(String agentTypeExist) {
        this.agentTypeExist = agentTypeExist;
    }

    public String getEditNotify() {
        return editNotify;
    }

    public void setEditNotify(String editNotify) {
        this.editNotify = editNotify;
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }


    public String getResidentialState() {
        return residentialState;
    }

    public void setResidentialState(String residentialState) {
        this.residentialState = residentialState;
    }

    public String getResidentialStateName() {
        return residentialStateName;
    }

    public void setResidentialStateName(String residentialStateName) {
        this.residentialStateName = residentialStateName;
    }

    public String getResidentialCity() {
        return residentialCity;
    }

    public void setResidentialCity(String residentialCity) {
        this.residentialCity = residentialCity;
    }

    public String getResidentialZipCode() {
        return residentialZipCode;
    }

    public void setResidentialZipCode(String residentialZipCode) {
        this.residentialZipCode = residentialZipCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAlternateMobileNumber() {
        return alternateMobileNumber;
    }

    public void setAlternateMobileNumber(String alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
    }

    public boolean isSameAddressOffnHome() {
        return sameAddressOffnHome;
    }

    public void setSameAddressOffnHome(boolean sameAddressOffnHome) {
        this.sameAddressOffnHome = sameAddressOffnHome;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getResidentialCountry() {
        return residentialCountry;
    }

    public void setResidentialCountry(String residentialCountry) {
        this.residentialCountry = residentialCountry;
    }

    public String getPermanentCountry() {
        return permanentCountry;
    }

    public void setPermanentCountry(String permanentCountry) {
        this.permanentCountry = permanentCountry;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public String getPermanentZipCode() {
        return permanentZipCode;
    }

    public void setPermanentZipCode(String permanentZipCode) {
        this.permanentZipCode = permanentZipCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOldPin() {
        return oldPin;
    }

    public void setOldPin(String oldPin) {
        this.oldPin = oldPin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getConfirmPin() {
        return confirmPin;
    }

    public void setConfirmPin(String confirmPin) {
        this.confirmPin = confirmPin;
    }

    public String getPermAdd() {
        return permAdd;
    }

    public void setPermAdd(String permAdd) {
        this.permAdd = permAdd;
    }

    public String getPermState() {
        return permState;
    }

    public void setPermState(String permState) {
        this.permState = permState;
    }

    public String getPermCity() {
        return permCity;
    }

    public void setPermCity(String permCity) {
        this.permCity = permCity;
    }

    public String getPermZipCode() {
        return permZipCode;
    }

    public void setPermZipCode(String permZipCode) {
        this.permZipCode = permZipCode;
    }
}
