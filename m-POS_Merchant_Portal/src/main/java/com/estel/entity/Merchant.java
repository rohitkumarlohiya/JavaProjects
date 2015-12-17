package com.estel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the merchant database table.
 * 
 */
@Component
@Entity
public class Merchant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="MERCHANT_MERCHANTID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MERCHANT_MERCHANTID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="merchant_id")
	private Long merchantId;

	@Column(name="merchant_activation_date")
	private Date merchantActivationDate;

	@Column(name="merchant_code")
	private String merchantCode;

	@Column(name="merchant_creation_date")
	private Date merchantCreationDate;

    @Column(name="merchant_email")
    private String merchantEmail;

	@Column(name="merchant_level_id")
	private Long merchantLevelId;

    @Column(name="merchant_mobile_no")
    private String merchantMobileNo;

	@Column(name="merchant_name")
	private String merchantName;

	@Column(name="merchant_proof_value")
	private String merchantProofValue;

	@Column(name="merchant_template_id")
	private Long merchantTemplateId;

	@Column(name="merchant_ts")
	private Date merchantTs;

	@Column(name="merchant_type")
	private String merchantType;

	@Column(name="merchant_ui_role_id")
	private Long merchantUiRoleId;

	//bi-directional many-to-one association to Contactdetail
	@OneToMany(mappedBy="merchant")
	private List<Contactdetail> contactdetails;

	//bi-directional many-to-one association to DeviceOrder
	@OneToMany(mappedBy="merchant")
	private List<DeviceOrder> deviceOrders;

	//bi-directional many-to-one association to Idproof
	@ManyToOne
	@JoinColumn(name="merchant_proof_id")
	private Idproof idproof;

    @Column(name="merchant_parent_level_id")
	private Long merchantParentLevelId;

	//bi-directional many-to-one association to Merchant
	@ManyToOne
	@JoinColumn(name="merchant_parent_id")
	private Merchant merchant;

	//bi-directional many-to-one association to Merchant
	@OneToMany(mappedBy="merchant")
	private List<Merchant> merchants;

    @Column(name="merchant_parent_status_id")
	private Long merchantParentStatusId;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="merchant_status_id")
	private Status status2;

	//bi-directional many-to-one association to PasswordRecord
	@OneToMany(mappedBy="merchant")
	private List<PasswordRecord> passwordRecords;

	//bi-directional many-to-one association to Pin
	@OneToMany(mappedBy="merchant")
	private List<Pin> pins;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="merchant")
	private List<User> users;

	public Merchant() {
	}

	public Long getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

    public String getMerchantEmail() {
        return merchantEmail;
    }

    public void setMerchantEmail(String merchantEmail) {
        this.merchantEmail = merchantEmail;
    }

    public Date getMerchantActivationDate() {
		return this.merchantActivationDate;
	}

	public void setMerchantActivationDate(Date merchantActivationDate) {
		this.merchantActivationDate = merchantActivationDate;
	}

	public String getMerchantCode() {
		return this.merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public Date getMerchantCreationDate() {
		return this.merchantCreationDate;
	}

	public void setMerchantCreationDate(Date merchantCreationDate) {
		this.merchantCreationDate = merchantCreationDate;
	}

	public Long getMerchantLevelId() {
		return this.merchantLevelId;
	}

	public void setMerchantLevelId(Long merchantLevelId) {
		this.merchantLevelId = merchantLevelId;
	}

	public String getMerchantName() {
		return this.merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantProofValue() {
		return this.merchantProofValue;
	}

	public void setMerchantProofValue(String merchantProofValue) {
		this.merchantProofValue = merchantProofValue;
	}

	public Long getMerchantTemplateId() {
		return this.merchantTemplateId;
	}

	public void setMerchantTemplateId(Long merchantTemplateId) {
		this.merchantTemplateId = merchantTemplateId;
	}

	public Date getMerchantTs() {
		return this.merchantTs;
	}

	public void setMerchantTs(Date merchantTs) {
		this.merchantTs = merchantTs;
	}

	public String getMerchantType() {
		return this.merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public Long getMerchantUiRoleId() {
		return this.merchantUiRoleId;
	}

	public void setMerchantUiRoleId(Long merchantUiRoleId) {
		this.merchantUiRoleId = merchantUiRoleId;
	}

	public List<Contactdetail> getContactdetails() {
		return this.contactdetails;
	}

	public void setContactdetails(List<Contactdetail> contactdetails) {
		this.contactdetails = contactdetails;
	}

	public Contactdetail addContactdetail(Contactdetail contactdetail) {
		getContactdetails().add(contactdetail);
		contactdetail.setMerchant(this);

		return contactdetail;
	}

	public Contactdetail removeContactdetail(Contactdetail contactdetail) {
		getContactdetails().remove(contactdetail);
		contactdetail.setMerchant(null);

		return contactdetail;
	}

	public List<DeviceOrder> getDeviceOrders() {
		return this.deviceOrders;
	}

	public void setDeviceOrders(List<DeviceOrder> deviceOrders) {
		this.deviceOrders = deviceOrders;
	}

	public DeviceOrder addDeviceOrder(DeviceOrder deviceOrder) {
		getDeviceOrders().add(deviceOrder);
		deviceOrder.setMerchant(this);

		return deviceOrder;
	}

	public DeviceOrder removeDeviceOrder(DeviceOrder deviceOrder) {
		getDeviceOrders().remove(deviceOrder);
		deviceOrder.setMerchant(null);

		return deviceOrder;
	}

	public Idproof getIdproof() {
		return this.idproof;
	}

	public void setIdproof(Idproof idproof) {
		this.idproof = idproof;
	}

	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public List<Merchant> getMerchants() {
		return this.merchants;
	}

	public void setMerchants(List<Merchant> merchants) {
		this.merchants = merchants;
	}

	public Merchant addMerchant(Merchant merchant) {
		getMerchants().add(merchant);
		merchant.setMerchant(this);

		return merchant;
	}

	public Merchant removeMerchant(Merchant merchant) {
		getMerchants().remove(merchant);
		merchant.setMerchant(null);

		return merchant;
	}

    public Long getMerchantParentLevelId() {
        return merchantParentLevelId;
    }

    public void setMerchantParentLevelId(Long merchantParentLevelId) {
        this.merchantParentLevelId = merchantParentLevelId;
    }

    public Long getMerchantParentStatusId() {
        return merchantParentStatusId;
    }

    public void setMerchantParentStatusId(Long merchantParentStatusId) {
        this.merchantParentStatusId = merchantParentStatusId;
    }

    public Status getStatus2() {
		return this.status2;
	}

	public void setStatus2(Status status2) {
		this.status2 = status2;
	}

	public List<PasswordRecord> getPasswordRecords() {
		return this.passwordRecords;
	}

	public void setPasswordRecords(List<PasswordRecord> passwordRecords) {
		this.passwordRecords = passwordRecords;
	}

	public PasswordRecord addPasswordRecord(PasswordRecord passwordRecord) {
		getPasswordRecords().add(passwordRecord);
		passwordRecord.setMerchant(this);

		return passwordRecord;
	}

	public PasswordRecord removePasswordRecord(PasswordRecord passwordRecord) {
		getPasswordRecords().remove(passwordRecord);
		passwordRecord.setMerchant(null);

		return passwordRecord;
	}

	public List<Pin> getPins() {
		return this.pins;
	}

	public void setPins(List<Pin> pins) {
		this.pins = pins;
	}

	public Pin addPin(Pin pin) {
		getPins().add(pin);
		pin.setMerchant(this);

		return pin;
	}

	public Pin removePin(Pin pin) {
		getPins().remove(pin);
		pin.setMerchant(null);

		return pin;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setMerchant(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setMerchant(null);

		return user;
	}

    public String getMerchantMobileNo() {
        return merchantMobileNo;
    }

    public void setMerchantMobileNo(String merchantMobileNo) {
        this.merchantMobileNo = merchantMobileNo;
    }
}