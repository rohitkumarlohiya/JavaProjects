package com.estel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the status database table.
 * 
 */
@Component
@Entity
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATUS_STATUSID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATUS_STATUSID_GENERATOR")
	@Column(name="status_id")
	private Long statusId;

	@Column(name="status_code")
	private String statusCode;

	@Column(name="status_description")
	private String statusDescription;

	@Column(name="status_ts")
	private Date statusTs;

	//bi-directional many-to-one association to Bank
	@OneToMany(mappedBy="status")
	private List<Bank> banks;

	//bi-directional many-to-one association to BankAccountType
	@OneToMany(mappedBy="status")
	private List<BankAccountType> bankAccountTypes;

	//bi-directional many-to-one association to BusinessCategory
	@OneToMany(mappedBy="status")
	private List<BusinessCategory> businessCategories;

	//bi-directional many-to-one association to BusinessOwnerType
	@OneToMany(mappedBy="status")
	private List<BusinessOwnerType> businessOwnerTypes;

	//bi-directional many-to-one association to BusinessType
	@OneToMany(mappedBy="status")
	private List<BusinessType> businessTypes;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="status")
	private List<City> cities;

	//bi-directional many-to-one association to Country
	@OneToMany(mappedBy="status")
	private List<Country> countries;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="status")
	private List<Device> devices;

	//bi-directional many-to-one association to DeviceType
	@OneToMany(mappedBy="status")
	private List<DeviceType> deviceTypes;

	//bi-directional many-to-one association to Idproof
	@OneToMany(mappedBy="status")
	private List<Idproof> idproofs;

	//bi-directional many-to-one association to Language
	@OneToMany(mappedBy="status")
	private List<Language> languages;

	//bi-directional many-to-one association to Level
	@OneToMany(mappedBy="status")
	private List<Level> levels;

/*	//bi-directional many-to-one association to Merchant
	@OneToMany(mappedBy="status1")
	private List<Merchant> merchants1;*/

	//bi-directional many-to-one association to Merchant
	@OneToMany(mappedBy="status2")
	private List<Merchant> merchants2;

	//bi-directional many-to-one association to Right
	@OneToMany(mappedBy="status")
	private List<Right> rights;

	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="status")
	private List<Role> roles;

	//bi-directional many-to-one association to RolesRightsDetail
	@OneToMany(mappedBy="status")
	private List<RolesRightsDetail> rolesRightsDetails;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="status")
	private List<State> states;

	//bi-directional many-to-one association to TransactionType
	@OneToMany(mappedBy="status")
	private List<TransactionType> transactionTypes;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="status")
	private List<User> users;

	public Status() {
	}

	public Long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public Date getStatusTs() {
		return this.statusTs;
	}

	public void setStatusTs(Date statusTs) {
		this.statusTs = statusTs;
	}

	public List<Bank> getBanks() {
		return this.banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	public Bank addBank(Bank bank) {
		getBanks().add(bank);
		bank.setStatus(this);

		return bank;
	}

	public Bank removeBank(Bank bank) {
		getBanks().remove(bank);
		bank.setStatus(null);

		return bank;
	}

	public List<BankAccountType> getBankAccountTypes() {
		return this.bankAccountTypes;
	}

	public void setBankAccountTypes(List<BankAccountType> bankAccountTypes) {
		this.bankAccountTypes = bankAccountTypes;
	}

	public BankAccountType addBankAccountType(BankAccountType bankAccountType) {
		getBankAccountTypes().add(bankAccountType);
		bankAccountType.setStatus(this);

		return bankAccountType;
	}

	public BankAccountType removeBankAccountType(BankAccountType bankAccountType) {
		getBankAccountTypes().remove(bankAccountType);
		bankAccountType.setStatus(null);

		return bankAccountType;
	}

	public List<BusinessCategory> getBusinessCategories() {
		return this.businessCategories;
	}

	public void setBusinessCategories(List<BusinessCategory> businessCategories) {
		this.businessCategories = businessCategories;
	}

	public BusinessCategory addBusinessCategory(BusinessCategory businessCategory) {
		getBusinessCategories().add(businessCategory);
		businessCategory.setStatus(this);

		return businessCategory;
	}

	public BusinessCategory removeBusinessCategory(BusinessCategory businessCategory) {
		getBusinessCategories().remove(businessCategory);
		businessCategory.setStatus(null);

		return businessCategory;
	}

	public List<BusinessOwnerType> getBusinessOwnerTypes() {
		return this.businessOwnerTypes;
	}

	public void setBusinessOwnerTypes(List<BusinessOwnerType> businessOwnerTypes) {
		this.businessOwnerTypes = businessOwnerTypes;
	}

	public BusinessOwnerType addBusinessOwnerType(BusinessOwnerType businessOwnerType) {
		getBusinessOwnerTypes().add(businessOwnerType);
		businessOwnerType.setStatus(this);

		return businessOwnerType;
	}

	public BusinessOwnerType removeBusinessOwnerType(BusinessOwnerType businessOwnerType) {
		getBusinessOwnerTypes().remove(businessOwnerType);
		businessOwnerType.setStatus(null);

		return businessOwnerType;
	}

	public List<BusinessType> getBusinessTypes() {
		return this.businessTypes;
	}

	public void setBusinessTypes(List<BusinessType> businessTypes) {
		this.businessTypes = businessTypes;
	}

	public BusinessType addBusinessType(BusinessType businessType) {
		getBusinessTypes().add(businessType);
		businessType.setStatus(this);

		return businessType;
	}

	public BusinessType removeBusinessType(BusinessType businessType) {
		getBusinessTypes().remove(businessType);
		businessType.setStatus(null);

		return businessType;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setStatus(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setStatus(null);

		return city;
	}

	public List<Country> getCountries() {
		return this.countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Country addCountry(Country country) {
		getCountries().add(country);
		country.setStatus(this);

		return country;
	}

	public Country removeCountry(Country country) {
		getCountries().remove(country);
		country.setStatus(null);

		return country;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setStatus(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setStatus(null);

		return device;
	}

	public List<DeviceType> getDeviceTypes() {
		return this.deviceTypes;
	}

	public void setDeviceTypes(List<DeviceType> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}

	public DeviceType addDeviceType(DeviceType deviceType) {
		getDeviceTypes().add(deviceType);
		deviceType.setStatus(this);

		return deviceType;
	}

	public DeviceType removeDeviceType(DeviceType deviceType) {
		getDeviceTypes().remove(deviceType);
		deviceType.setStatus(null);

		return deviceType;
	}

	public List<Idproof> getIdproofs() {
		return this.idproofs;
	}

	public void setIdproofs(List<Idproof> idproofs) {
		this.idproofs = idproofs;
	}

	public Idproof addIdproof(Idproof idproof) {
		getIdproofs().add(idproof);
		idproof.setStatus(this);

		return idproof;
	}

	public Idproof removeIdproof(Idproof idproof) {
		getIdproofs().remove(idproof);
		idproof.setStatus(null);

		return idproof;
	}

	public List<Language> getLanguages() {
		return this.languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public Language addLanguage(Language language) {
		getLanguages().add(language);
		language.setStatus(this);

		return language;
	}

	public Language removeLanguage(Language language) {
		getLanguages().remove(language);
		language.setStatus(null);

		return language;
	}

	public List<Level> getLevels() {
		return this.levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public Level addLevel(Level level) {
		getLevels().add(level);
		level.setStatus(this);

		return level;
	}

	public Level removeLevel(Level level) {
		getLevels().remove(level);
		level.setStatus(null);

		return level;
	}

		public List<Merchant> getMerchants2() {
		return this.merchants2;
	}

	public void setMerchants2(List<Merchant> merchants2) {
		this.merchants2 = merchants2;
	}

	public Merchant addMerchants2(Merchant merchants2) {
		getMerchants2().add(merchants2);
		merchants2.setStatus2(this);

		return merchants2;
	}

	public Merchant removeMerchants2(Merchant merchants2) {
		getMerchants2().remove(merchants2);
		merchants2.setStatus2(null);

		return merchants2;
	}

	public List<Right> getRights() {
		return this.rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	public Right addRight(Right right) {
		getRights().add(right);
		right.setStatus(this);

		return right;
	}

	public Right removeRight(Right right) {
		getRights().remove(right);
		right.setStatus(null);

		return right;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setStatus(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setStatus(null);

		return role;
	}

	public List<RolesRightsDetail> getRolesRightsDetails() {
		return this.rolesRightsDetails;
	}

	public void setRolesRightsDetails(List<RolesRightsDetail> rolesRightsDetails) {
		this.rolesRightsDetails = rolesRightsDetails;
	}

	public RolesRightsDetail addRolesRightsDetail(RolesRightsDetail rolesRightsDetail) {
		getRolesRightsDetails().add(rolesRightsDetail);
		rolesRightsDetail.setStatus(this);

		return rolesRightsDetail;
	}

	public RolesRightsDetail removeRolesRightsDetail(RolesRightsDetail rolesRightsDetail) {
		getRolesRightsDetails().remove(rolesRightsDetail);
		rolesRightsDetail.setStatus(null);

		return rolesRightsDetail;
	}

	public List<State> getStates() {
		return this.states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public State addState(State state) {
		getStates().add(state);
		state.setStatus(this);

		return state;
	}

	public State removeState(State state) {
		getStates().remove(state);
		state.setStatus(null);

		return state;
	}

	public List<TransactionType> getTransactionTypes() {
		return this.transactionTypes;
	}

	public void setTransactionTypes(List<TransactionType> transactionTypes) {
		this.transactionTypes = transactionTypes;
	}

	public TransactionType addTransactionType(TransactionType transactionType) {
		getTransactionTypes().add(transactionType);
		transactionType.setStatus(this);

		return transactionType;
	}

	public TransactionType removeTransactionType(TransactionType transactionType) {
		getTransactionTypes().remove(transactionType);
		transactionType.setStatus(null);

		return transactionType;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setStatus(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setStatus(null);

		return user;
	}

}