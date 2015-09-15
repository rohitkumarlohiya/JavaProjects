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
 * The persistent class for the roles_rights_detail database table.
 * 
 */
@Component
@Entity
@Table(name="roles_rights_detail")
public class RolesRightsDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROLES_RIGHTS_DETAIL_ROLESDETAILID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLES_RIGHTS_DETAIL_ROLESDETAILID_GENERATOR")
	@Column(name="roles_detail_id")
	private Long rolesDetailId;

	@Column(name="roles_detail_ts")
	private Date rolesDetailTs;

	//bi-directional many-to-one association to Right
	@ManyToOne
	@JoinColumn(name="roles_detail_rights_id")
	private Right right;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roles_detail_roles_id")
	private Role role;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="roles_detail_status_id")
	private Status status;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="roles_details_creator_user_id")
	private User user;

	public RolesRightsDetail() {
	}

	public Long getRolesDetailId() {
		return this.rolesDetailId;
	}

	public void setRolesDetailId(Long rolesDetailId) {
		this.rolesDetailId = rolesDetailId;
	}

	public Date getRolesDetailTs() {
		return this.rolesDetailTs;
	}

	public void setRolesDetailTs(Date rolesDetailTs) {
		this.rolesDetailTs = rolesDetailTs;
	}

	public Right getRight() {
		return this.right;
	}

	public void setRight(Right right) {
		this.right = right;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}