package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_USER_PROGRAM_COURSES", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserProgramCources extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "PROGRAM_NAME", length = 40, nullable = true, unique = false)
	private String programName;

	@Column(name = "PRIORITY", nullable = true, unique = false)
	private int priority;

	@Column(name = "ACCOUNT_ID", nullable = false, unique = false)
	private long accounts;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROGRAM_ID", nullable = false)
	@JsonManagedReference
	private UserPrograms userPrograms;

	@Column(name = "COURSES_ID", nullable = false, unique = false)
	private long courses;

	@Column(name = "TARGET_START_DATE", nullable = true)
	private Date targetStartDate;

	@Column(name = "TARGET_END_DATE", nullable = true)
	private Date targetEndDate;
	
	@Transient
	private String targetStartDateSt;
	
	@Transient
	private String targetEndDateSt;

	public UserProgramCources() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProgramCources(String programName, int priority, long accounts,
			UserPrograms userPrograms, long courses, Date targetStartDate,
			Date targetEndDate) {
		super();
		this.programName = programName;
		this.priority = priority;
		this.accounts = accounts;
		this.userPrograms = userPrograms;
		this.courses = courses;
		this.targetStartDate = targetStartDate;
		this.targetEndDate = targetEndDate;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public UserPrograms getUserPrograms() {
		return userPrograms;
	}

	public void setUserPrograms(UserPrograms userPrograms) {
		this.userPrograms = userPrograms;
	}

	public long getAccounts() {
		return accounts;
	}

	public void setAccounts(long accounts) {
		this.accounts = accounts;
	}

	public long getCourses() {
		return courses;
	}

	public void setCourses(long courses) {
		this.courses = courses;
	}

	public Date getTargetStartDate() {
		return targetStartDate;
	}

	public void setTargetStartDate(Date targetStartDate) {
		this.targetStartDate = targetStartDate;
	}

	public Date getTargetEndDate() {
		return targetEndDate;
	}

	public void setTargetEndDate(Date targetEndDate) {
		this.targetEndDate = targetEndDate;
	}

	public String getTargetStartDateSt() {
		return targetStartDateSt;
	}

	public void setTargetStartDateSt(String targetStartDateSt) {
		this.targetStartDateSt = targetStartDateSt;
	}

	public String getTargetEndDateSt() {
		return targetEndDateSt;
	}

	public void setTargetEndDateSt(String targetEndDateSt) {
		this.targetEndDateSt = targetEndDateSt;
	}
}
