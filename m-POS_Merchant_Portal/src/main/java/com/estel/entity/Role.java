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
import javax.persistence.Table;


/**
 * The persistent class for the roles database table.
 * 
 */
@Component
@Entity
@Table(name="roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="ROLES_ROLESID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLES_ROLESID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="roles_id")
	private Long rolesId;

	@Column(name="roles_name")
	private String rolesName;

	@Column(name="roles_sp_id")
	private Long rolesSpId;

	@Column(name="roles_ts")
	private Date rolesTs;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="roles_status_id")
	private Status status;

	//bi-directional many-to-one association to RolesRightsDetail
	@OneToMany(mappedBy="role")
	private List<RolesRightsDetail> rolesRightsDetails;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="role")
	private List<User> users;

	public Role() {
	}

	public Long getRolesId() {
		return this.rolesId;
	}

	public void setRolesId(Long rolesId) {
		this.rolesId = rolesId;
	}

	public String getRolesName() {
		return this.rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}

	public Long getRolesSpId() {
		return this.rolesSpId;
	}

	public void setRolesSpId(Long rolesSpId) {
		this.rolesSpId = rolesSpId;
	}

	public Date getRolesTs() {
		return this.rolesTs;
	}

	public void setRolesTs(Date rolesTs) {
		this.rolesTs = rolesTs;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<RolesRightsDetail> getRolesRightsDetails() {
		return this.rolesRightsDetails;
	}

	public void setRolesRightsDetails(List<RolesRightsDetail> rolesRightsDetails) {
		this.rolesRightsDetails = rolesRightsDetails;
	}

	public RolesRightsDetail addRolesRightsDetail(RolesRightsDetail rolesRightsDetail) {
		getRolesRightsDetails().add(rolesRightsDetail);
		rolesRightsDetail.setRole(this);

		return rolesRightsDetail;
	}

	public RolesRightsDetail removeRolesRightsDetail(RolesRightsDetail rolesRightsDetail) {
		getRolesRightsDetails().remove(rolesRightsDetail);
		rolesRightsDetail.setRole(null);

		return rolesRightsDetail;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}