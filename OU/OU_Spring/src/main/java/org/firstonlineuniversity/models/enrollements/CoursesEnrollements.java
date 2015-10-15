package org.firstonlineuniversity.models.enrollements;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_COURSE_ENROLLEMENTS ", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CoursesEnrollements extends AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	@JsonBackReference
	private Accounts accounts;

	@Column(name = "COURSE_ID", length = 20, nullable = false)
	private long courseId;

	@Column(name = "SESSION_ID", length = 20, nullable = true)
	private long sessionId;

	@Column(name = "GROUP_ID", length = 20, nullable = true)
	private long groupId;

	@Column(name = "PROGRAM_ID", length = 20, nullable = true)
	private long programId;

	@Column(name = "PAID", nullable = true)
	private boolean paid;

	@Column(name = "COST", nullable = true)
	private double cost;

	@Column(name = "CURRENCY", length = 4, nullable = true)
	private String currency;

	@Column(name = "ETHIC_POLICY_ACCEPTED", nullable = true)
	private boolean ethicPolicyAccepted;

	@Column(name = "LEGAL_TERMS_ACCEPTED", nullable = true)
	private boolean legalTermsAccepted;

	@Column(name = "AUTHORIZATION", nullable = true)
	private String authorization;

	@Column(name = "TRANSACTION_ID", nullable = true)
	private Long transactionId;

	@Column(name = "FAVORITE", nullable = true)
	private boolean favorite;

	@Column(name = "ARCHIVE", nullable = true)
	private boolean archive;

	@Column(name = "COMPLETE", nullable = true)
	private boolean complete;

	public CoursesEnrollements(long id, long courseId, long sessionId,
			long groupId, long programId, boolean paid, double cost,
			String currency, boolean ethicPolicyAccepted,
			boolean legalTermsAccepted) {
		super();
		super.setId(id);
		this.courseId = courseId;
		this.sessionId = sessionId;
		this.groupId = groupId;
		this.programId = programId;
		this.paid = paid;
		this.cost = cost;
		this.currency = currency;
		this.ethicPolicyAccepted = ethicPolicyAccepted;
		this.legalTermsAccepted = legalTermsAccepted;
	}

	public CoursesEnrollements() {
		super();
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean isEthicPolicyAccepted() {
		return ethicPolicyAccepted;
	}

	public void setEthicPolicyAccepted(boolean ethicPolicyAccepted) {
		this.ethicPolicyAccepted = ethicPolicyAccepted;
	}

	public boolean isLegalTermsAccepted() {
		return legalTermsAccepted;
	}

	public void setLegalTermsAccepted(boolean legalTermsAccepted) {
		this.legalTermsAccepted = legalTermsAccepted;
	}

}
