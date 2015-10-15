package org.firstonlineuniversity.models.enrollements;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_UNIVERSITY_MEMBERSHIP", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UniversityMembership extends AbstractEntity implements
		Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	@JsonBackReference
	private Accounts accounts;

	@Column(name = "MEMBERSHIP_TYPE", length = 30, nullable = false)
	private String membershipType;

	@Column(name = "GROUP_ID", length = 20, nullable = true)
	private long groupId;

	@Column(name = "START_DATE", nullable = true)
	private Date startDate;

	@Column(name = "END_DATE", nullable = true)
	private Date endDate;

	@Column(name = "RENEWAL_DATE", nullable = true)
	private Date renewalDate;

	@Column(name = "NOTIFY_EXPIARY", nullable = true)
	private boolean notifyExpiary;

	@Column(name = "EXPIARY_THRESHOLD")
	private int expiaryThreshold;
	
	@Transient
	private String strStartDate;
	
	@Transient
	private String strEndDate;
	
	@Transient
	private String strRenewDate;
	

	public UniversityMembership(long id,String membershipType, long groupId,
			Date startDate, Date endDate, Date renewalDate,
			boolean notifyExpiary, int expiaryThreshold) {
		super();
		super.setId(id);
		this.membershipType = membershipType;
		this.groupId = groupId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.renewalDate = renewalDate;
		this.notifyExpiary = notifyExpiary;
		this.expiaryThreshold = expiaryThreshold;
		
	}
	
	public String getStrRenewDate() {
		return strRenewDate;
	}

	public void setStrRenewDate(String strRenewDate) {
		this.strRenewDate = strRenewDate;
	}

	public String getStrStartDate() {
		return strStartDate;
	}

	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

	public String getStrEndDate() {
		return strEndDate;
	}
	
	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}

	public UniversityMembership() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(Date renewalDate) {
		this.renewalDate = renewalDate;
	}

	public boolean isNotifyExpiary() {
		return notifyExpiary;
	}

	public void setNotifyExpiary(boolean notifyExpiary) {
		this.notifyExpiary = notifyExpiary;
	}

	public int getExpiaryThreshold() {
		return expiaryThreshold;
	}

	public void setExpiaryThreshold(int expiaryThreshold) {
		this.expiaryThreshold = expiaryThreshold;
	}

}
