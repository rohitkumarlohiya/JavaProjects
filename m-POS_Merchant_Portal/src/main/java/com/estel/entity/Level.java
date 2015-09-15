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


/**
 * The persistent class for the level database table.
 * 
 */
@Component
@Entity
public class Level implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LEVEL_LEVELID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LEVEL_LEVELID_GENERATOR")
	@Column(name="level_id")
	private Long levelId;

	@Column(name="level_code")
	private String levelCode;

	@Column(name="level_description")
	private String levelDescription;

	@Column(name="level_ts")
	private Date levelTs;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="level_status_id")
	private Status status;

	/*//bi-directional many-to-one association to Merchant
	@OneToMany(mappedBy="level")
	private List<Merchant> merchants;*/

	public Level() {
	}

	public Long getLevelId() {
		return this.levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getLevelCode() {
		return this.levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelDescription() {
		return this.levelDescription;
	}

	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}

	public Date getLevelTs() {
		return this.levelTs;
	}

	public void setLevelTs(Date levelTs) {
		this.levelTs = levelTs;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/*public List<Merchant> getMerchants() {
		return this.merchants;
	}

	public void setMerchants(List<Merchant> merchants) {
		this.merchants = merchants;
	}*/

}