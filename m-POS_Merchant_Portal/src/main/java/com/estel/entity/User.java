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
 * The persistent class for the user database table.
 * 
 */
@Component
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="USER_USERID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_USERID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;

	@Column(name="user_activation_ts")
	private Date userActivationTs;

	@Column(name="user_first_name")
	private String userFirstName;

	@Column(name="user_last_login_ts")
	private Date userLastLoginTs;

	@Column(name="user_last_name")
	private String userLastName;

    @Column(name="user_wrong_attempt_ctr")
    private Long userWrongAttemptCtr;

	@Column(name="user_login_name")
	private String userLoginName;

	@Column(name="user_mail_id")
	private String userMailId;

	@Column(name="user_password")
	private String userPassword;

	@Column(name="user_password_expiry")
	private Date userPasswordExpiry;

	@Column(name="user_ts")
	private Date userTs;

	//bi-directional many-to-one association to RolesRightsDetail
	@OneToMany(mappedBy="user")
	private List<RolesRightsDetail> rolesRightsDetails;

	//bi-directional many-to-one association to Merchant
	@ManyToOne
	@JoinColumn(name="user_merchant_id")
	private Merchant merchant;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="user_role_id")
	private Role role;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="user_status_id")
	private Status status;

	public User() {
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getUserActivationTs() {
		return this.userActivationTs;
	}

	public void setUserActivationTs(Date userActivationTs) {
		this.userActivationTs = userActivationTs;
	}

	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public Date getUserLastLoginTs() {
		return this.userLastLoginTs;
	}

	public void setUserLastLoginTs(Date userLastLoginTs) {
		this.userLastLoginTs = userLastLoginTs;
	}

	public String getUserLastName() {
		return this.userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserLoginName() {
		return this.userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getUserMailId() {
		return this.userMailId;
	}

	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getUserPasswordExpiry() {
		return this.userPasswordExpiry;
	}

	public void setUserPasswordExpiry(Date userPasswordExpiry) {
		this.userPasswordExpiry = userPasswordExpiry;
	}

	public Date getUserTs() {
		return this.userTs;
	}

	public void setUserTs(Date userTs) {
		this.userTs = userTs;
	}

	public List<RolesRightsDetail> getRolesRightsDetails() {
		return this.rolesRightsDetails;
	}

	public void setRolesRightsDetails(List<RolesRightsDetail> rolesRightsDetails) {
		this.rolesRightsDetails = rolesRightsDetails;
	}

	public RolesRightsDetail addRolesRightsDetail(RolesRightsDetail rolesRightsDetail) {
		getRolesRightsDetails().add(rolesRightsDetail);
		rolesRightsDetail.setUser(this);

		return rolesRightsDetail;
	}

	public RolesRightsDetail removeRolesRightsDetail(RolesRightsDetail rolesRightsDetail) {
		getRolesRightsDetails().remove(rolesRightsDetail);
		rolesRightsDetail.setUser(null);

		return rolesRightsDetail;
	}

	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

    public Long getUserWrongAttemptCtr() {
        return userWrongAttemptCtr;
    }

    public void setUserWrongAttemptCtr(Long userWrongAttemptCtr) {
        this.userWrongAttemptCtr = userWrongAttemptCtr;
    }
}