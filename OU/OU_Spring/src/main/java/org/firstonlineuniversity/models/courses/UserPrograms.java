package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_USER_PROGRAMS", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserPrograms extends AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "PROGRAM_NAME", length = 40, nullable = true, unique = false)
	private String programName;

	@Column(name = "CREDITS", nullable = true, unique = false)
	private short credits;

	@Column(name = "PROGRAM_DESCRIPTION", length = 255, nullable = true, unique = false)
	private String progranDescription;

	@Column(name = "EXPECTED_COMPLETION_TIME", length = 4, nullable = true, unique = false)
	private int expectedComletionTime;

	@Column(name = "REMINDER", nullable = true, unique = false)
	private boolean reminder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	@JsonManagedReference
	private Accounts accounts;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userPrograms")
	@JsonBackReference
	private Set<UserProgramCources> userProgramCourses = new HashSet<UserProgramCources>(
			0);

	public UserPrograms() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPrograms(String programName, short credits,
			String progranDescription, int expectedComletionTime,
			boolean reminder, Accounts accounts) {
		super();
		this.programName = programName;
		this.credits = credits;
		this.progranDescription = progranDescription;
		this.expectedComletionTime = expectedComletionTime;
		this.reminder = reminder;
		this.accounts = accounts;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public short getCredits() {
		return credits;
	}

	public void setCredits(short credits) {
		this.credits = credits;
	}

	public String getProgranDescription() {
		return progranDescription;
	}

	public void setProgranDescription(String progranDescription) {
		this.progranDescription = progranDescription;
	}

	public int getExpectedComletionTime() {
		return expectedComletionTime;
	}

	public void setExpectedComletionTime(int expectedComletionTime) {
		this.expectedComletionTime = expectedComletionTime;
	}

	public boolean isReminder() {
		return reminder;
	}

	public void setReminder(boolean reminder) {
		this.reminder = reminder;
	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public Set<UserProgramCources> getUserProgramCourses() {
		return userProgramCourses;
	}

	public void setUserProgramCourses(Set<UserProgramCources> userProgramCourses) {
		this.userProgramCourses = userProgramCourses;
	}
}
