package com.estel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the contactdetails database table.
 * 
 */
@Component
@Entity
@Table(name="contactdetails")
public class Contactdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="CONTACTDETAILS_CONTACTDETAILSID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTACTDETAILS_CONTACTDETAILSID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="contactdetails_id")
	private Long contactdetailsId;

	@Column(name="contactdetails_add_line_1")
	private String contactdetailsAddLine1;

	@Column(name="contactdetails_add_line_2")
	private String contactdetailsAddLine2;

	@Column(name="contactdetails_bd_applicable")
	private String contactdetailsBdApplicable;

	@Column(name="contactdetails_birth_date")
	private Date contactdetailsBirthDate;

	@Column(name="contactdetails_bod_ts")
	private Date contactdetailsBodTs;

	@Column(name="contactdetails_city")
	private String contactdetailsCity;

	@Column(name="contactdetails_contact_name")
	private String contactdetailsContactName;

	@Column(name="contactdetails_country")
	private String contactdetailsCountry;

	@Column(name="contactdetails_email")
	private String contactdetailsEmail;

	@Column(name="contactdetails_eod_ts")
	private Date contactdetailsEodTs;

	@Column(name="contactdetails_father_name")
	private String contactdetailsFatherName;

	@Column(name="contactdetails_fax_number")
	private String contactdetailsFaxNumber;

	@Column(name="contactdetails_gender")
	private String contactdetailsGender;

	@Column(name="contactdetails_marital")
	private String contactdetailsMarital;

	@Column(name="contactdetails_mobile_number")
	private String contactdetailsMobileNumber;

	@Column(name="contactdetails_mother_name")
	private String contactdetailsMotherName;

	@Column(name="contactdetails_notify_sms")
	private String contactdetailsNotifySms;

	@Column(name="contactdetails_pan")
	private String contactdetailsPan;

	@Column(name="contactdetails_phone_number")
	private String contactdetailsPhoneNumber;

	@Column(name="contactdetails_postcode")
	private String contactdetailsPostcode;

	@Column(name="contactdetails_state")
	private String contactdetailsState;

	@Column(name="contactdetails_tan")
	private String contactdetailsTan;

	@Column(name="contactdetails_ts")
	private Date contactdetailsTs;

	@Column(name="contactdetails_type")
	private String contactdetailsType;

	//bi-directional many-to-one association to Merchant
	@ManyToOne
	@JoinColumn(name="contactdetails_merchant_id")
	private Merchant merchant;

	public Contactdetail() {
	}

	public Long getContactdetailsId() {
		return this.contactdetailsId;
	}

	public void setContactdetailsId(Long contactdetailsId) {
		this.contactdetailsId = contactdetailsId;
	}

	public String getContactdetailsAddLine1() {
		return this.contactdetailsAddLine1;
	}

	public void setContactdetailsAddLine1(String contactdetailsAddLine1) {
		this.contactdetailsAddLine1 = contactdetailsAddLine1;
	}

	public String getContactdetailsAddLine2() {
		return this.contactdetailsAddLine2;
	}

	public void setContactdetailsAddLine2(String contactdetailsAddLine2) {
		this.contactdetailsAddLine2 = contactdetailsAddLine2;
	}

	public String getContactdetailsBdApplicable() {
		return this.contactdetailsBdApplicable;
	}

	public void setContactdetailsBdApplicable(String contactdetailsBdApplicable) {
		this.contactdetailsBdApplicable = contactdetailsBdApplicable;
	}

	public Date getContactdetailsBirthDate() {
		return this.contactdetailsBirthDate;
	}

	public void setContactdetailsBirthDate(Date contactdetailsBirthDate) {
		this.contactdetailsBirthDate = contactdetailsBirthDate;
	}

	public Date getContactdetailsBodTs() {
		return this.contactdetailsBodTs;
	}

	public void setContactdetailsBodTs(Date contactdetailsBodTs) {
		this.contactdetailsBodTs = contactdetailsBodTs;
	}

	public String getContactdetailsCity() {
		return this.contactdetailsCity;
	}

	public void setContactdetailsCity(String contactdetailsCity) {
		this.contactdetailsCity = contactdetailsCity;
	}

	public String getContactdetailsContactName() {
		return this.contactdetailsContactName;
	}

	public void setContactdetailsContactName(String contactdetailsContactName) {
		this.contactdetailsContactName = contactdetailsContactName;
	}

	public String getContactdetailsCountry() {
		return this.contactdetailsCountry;
	}

	public void setContactdetailsCountry(String contactdetailsCountry) {
		this.contactdetailsCountry = contactdetailsCountry;
	}

	public String getContactdetailsEmail() {
		return this.contactdetailsEmail;
	}

	public void setContactdetailsEmail(String contactdetailsEmail) {
		this.contactdetailsEmail = contactdetailsEmail;
	}

	public Date getContactdetailsEodTs() {
		return this.contactdetailsEodTs;
	}

	public void setContactdetailsEodTs(Date contactdetailsEodTs) {
		this.contactdetailsEodTs = contactdetailsEodTs;
	}

	public String getContactdetailsFatherName() {
		return this.contactdetailsFatherName;
	}

	public void setContactdetailsFatherName(String contactdetailsFatherName) {
		this.contactdetailsFatherName = contactdetailsFatherName;
	}

	public String getContactdetailsFaxNumber() {
		return this.contactdetailsFaxNumber;
	}

	public void setContactdetailsFaxNumber(String contactdetailsFaxNumber) {
		this.contactdetailsFaxNumber = contactdetailsFaxNumber;
	}

	public String getContactdetailsGender() {
		return this.contactdetailsGender;
	}

	public void setContactdetailsGender(String contactdetailsGender) {
		this.contactdetailsGender = contactdetailsGender;
	}

	public String getContactdetailsMarital() {
		return this.contactdetailsMarital;
	}

	public void setContactdetailsMarital(String contactdetailsMarital) {
		this.contactdetailsMarital = contactdetailsMarital;
	}

	public String getContactdetailsMobileNumber() {
		return this.contactdetailsMobileNumber;
	}

	public void setContactdetailsMobileNumber(String contactdetailsMobileNumber) {
		this.contactdetailsMobileNumber = contactdetailsMobileNumber;
	}

	public String getContactdetailsMotherName() {
		return this.contactdetailsMotherName;
	}

	public void setContactdetailsMotherName(String contactdetailsMotherName) {
		this.contactdetailsMotherName = contactdetailsMotherName;
	}

	public String getContactdetailsNotifySms() {
		return this.contactdetailsNotifySms;
	}

	public void setContactdetailsNotifySms(String contactdetailsNotifySms) {
		this.contactdetailsNotifySms = contactdetailsNotifySms;
	}

	public String getContactdetailsPan() {
		return this.contactdetailsPan;
	}

	public void setContactdetailsPan(String contactdetailsPan) {
		this.contactdetailsPan = contactdetailsPan;
	}

	public String getContactdetailsPhoneNumber() {
		return this.contactdetailsPhoneNumber;
	}

	public void setContactdetailsPhoneNumber(String contactdetailsPhoneNumber) {
		this.contactdetailsPhoneNumber = contactdetailsPhoneNumber;
	}

	public String getContactdetailsPostcode() {
		return this.contactdetailsPostcode;
	}

	public void setContactdetailsPostcode(String contactdetailsPostcode) {
		this.contactdetailsPostcode = contactdetailsPostcode;
	}

	public String getContactdetailsState() {
		return this.contactdetailsState;
	}

	public void setContactdetailsState(String contactdetailsState) {
		this.contactdetailsState = contactdetailsState;
	}

	public String getContactdetailsTan() {
		return this.contactdetailsTan;
	}

	public void setContactdetailsTan(String contactdetailsTan) {
		this.contactdetailsTan = contactdetailsTan;
	}

	public Date getContactdetailsTs() {
		return this.contactdetailsTs;
	}

	public void setContactdetailsTs(Date contactdetailsTs) {
		this.contactdetailsTs = contactdetailsTs;
	}

	public String getContactdetailsType() {
		return this.contactdetailsType;
	}

	public void setContactdetailsType(String contactdetailsType) {
		this.contactdetailsType = contactdetailsType;
	}

	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

}