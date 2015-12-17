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
 * The persistent class for the idproof database table.
 * 
 */
@Component
@Entity
public class Idproof implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="IDPROOF_IDPROOFID_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDPROOF_IDPROOFID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idproof_id")
	private Long idproofId;

	@Column(name="idproof_code")
	private String idproofCode;

	@Column(name="idproof_description")
	private String idproofDescription;

	@Column(name="idproof_ts")
	private Date idproofTs;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="idproof_status_id")
	private Status status;

	//bi-directional many-to-one association to Merchant
	@OneToMany(mappedBy="idproof")
	private List<Merchant> merchants;

	public Idproof() {
	}

	public Long getIdproofId() {
		return this.idproofId;
	}

	public void setIdproofId(Long idproofId) {
		this.idproofId = idproofId;
	}

	public String getIdproofCode() {
		return this.idproofCode;
	}

	public void setIdproofCode(String idproofCode) {
		this.idproofCode = idproofCode;
	}

	public String getIdproofDescription() {
		return this.idproofDescription;
	}

	public void setIdproofDescription(String idproofDescription) {
		this.idproofDescription = idproofDescription;
	}

	public Date getIdproofTs() {
		return this.idproofTs;
	}

	public void setIdproofTs(Date idproofTs) {
		this.idproofTs = idproofTs;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Merchant> getMerchants() {
		return this.merchants;
	}

	public void setMerchants(List<Merchant> merchants) {
		this.merchants = merchants;
	}

	public Merchant addMerchant(Merchant merchant) {
		getMerchants().add(merchant);
		merchant.setIdproof(this);

		return merchant;
	}

	public Merchant removeMerchant(Merchant merchant) {
		getMerchants().remove(merchant);
		merchant.setIdproof(null);

		return merchant;
	}

}