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
 * The persistent class for the rights database table.
 * 
 */
@Component
@Entity
@Table(name="rights")
public class Right implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RIGHTS_RIGHTSID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RIGHTS_RIGHTSID_GENERATOR")
	@Column(name="rights_id")
	private Long rightsId;

	@Column(name="rights_code")
	private String rightsCode;

	@Column(name="rights_description")
	private String rightsDescription;

	@Column(name="rights_ts")
	private Date rightsTs;

	@Column(name="roles_legacy_id")
	private Long rolesLegacyId;

	@Column(name="roles_sp_id")
	private Long rolesSpId;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="rights_status_id")
	private Status status;

	//bi-directional many-to-one association to RolesRightsDetail
	@OneToMany(mappedBy="right")
	private List<RolesRightsDetail> rolesRightsDetails;

	public Right() {
	}

	public Long getRightsId() {
		return this.rightsId;
	}

	public void setRightsId(Long rightsId) {
		this.rightsId = rightsId;
	}

	public String getRightsCode() {
		return this.rightsCode;
	}

	public void setRightsCode(String rightsCode) {
		this.rightsCode = rightsCode;
	}

	public String getRightsDescription() {
		return this.rightsDescription;
	}

	public void setRightsDescription(String rightsDescription) {
		this.rightsDescription = rightsDescription;
	}

	public Date getRightsTs() {
		return this.rightsTs;
	}

	public void setRightsTs(Date rightsTs) {
		this.rightsTs = rightsTs;
	}

	public Long getRolesLegacyId() {
		return this.rolesLegacyId;
	}

	public void setRolesLegacyId(Long rolesLegacyId) {
		this.rolesLegacyId = rolesLegacyId;
	}

	public Long getRolesSpId() {
		return this.rolesSpId;
	}

	public void setRolesSpId(Long rolesSpId) {
		this.rolesSpId = rolesSpId;
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
		rolesRightsDetail.setRight(this);

		return rolesRightsDetail;
	}

	public RolesRightsDetail removeRolesRightsDetail(RolesRightsDetail rolesRightsDetail) {
		getRolesRightsDetails().remove(rolesRightsDetail);
		rolesRightsDetail.setRight(null);

		return rolesRightsDetail;
	}

}