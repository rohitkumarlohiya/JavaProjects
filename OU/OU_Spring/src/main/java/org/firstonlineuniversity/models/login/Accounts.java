package org.firstonlineuniversity.models.login;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.Providers;
import org.firstonlineuniversity.models.courses.UserPrograms;
import org.firstonlineuniversity.models.enrollements.CoursesEnrollements;
import org.firstonlineuniversity.models.enrollements.UniversityMembership;
import org.firstonlineuniversity.models.profiles.Profiles;
import org.firstonlineuniversity.models.payment.Cards;

@Entity
@Table(name = "UN_ACCOUNTS", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Accounts extends AbstractEntity implements Serializable {

	/**
	 * @author Nagesh.Chauhan
	 */

	@Column(name = "ACCOUNT_EMAIL", length = 100, nullable = false, unique = true)
	private String accountEmail;

	@Column(name = "ACCOUNT_PHONE", length = 30, nullable = true)
	private String accountPhone;

	@Column(name = "PASSWD", length = 100, nullable = false)
	private String password;

	@Transient
	private String confirmPassword;

	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", length = 50, nullable = true)
	private String lastName;

	@Column(name = "ACCESS_LEVEL", length = 20, nullable = true)
	private String accessLevel;

	@Column(name = "IS_ACTIVATED", nullable = true)
	private boolean isActivated;

	@Column(name = "IP", length = 30, nullable = true)
	private String IP;

	@Column(name = "LK", length = 50, nullable = true)
	private String LK;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accounts")
	@JsonManagedReference
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accounts")
	@JsonManagedReference
	private Set<Cards> Cards = new HashSet<Cards>(0);

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accounts")
	@JsonManagedReference
	private Set<CoursesEnrollements> coursesEnrollements = new HashSet<CoursesEnrollements>(
			0);

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accounts")
	@JsonManagedReference
	private Set<UniversityMembership> universityMemberships = new HashSet<UniversityMembership>(
			0);

	@OneToOne(mappedBy = "accounts", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Profiles profiles;

	@OneToOne(mappedBy = "accounts", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Providers coursesProviders;

	@Column(name = "CONFIRMATION", nullable = false)
	private String confirmation;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accounts")
	@JsonBackReference
	private Set<UserPrograms> userPrograms = new HashSet<UserPrograms>(0);

	public Providers getCoursesProviders() {
		return coursesProviders;
	}

	public void setCoursesProviders(Providers coursesProviders) {
		this.coursesProviders = coursesProviders;
	}

	public Set<UserPrograms> getUserPrograms() {
		return userPrograms;
	}

	public void setUserPrograms(Set<UserPrograms> userPrograms) {
		this.userPrograms = userPrograms;
	}

	public Accounts() {
		super();
	}

	public Accounts(long id) {
		super();
		this.setId(id);
	}

	public Accounts(String accountEmail, String accountPhone, String password,
			String firstName, String lastName, String accessLevel,
			boolean isActivated, Set<UserRole> userRole) {
		super();
		this.accountEmail = accountEmail;
		this.accountPhone = accountPhone;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accessLevel = accessLevel;
		this.isActivated = isActivated;
		this.userRole = userRole;
	}

	@JsonIgnore
	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public String getPassword() {
		return password;
	}

	@JsonIgnore
	public String getConfirmPassword() {
		return confirmPassword;
	}

	@JsonDeserialize
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@JsonDeserialize
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	@JsonIgnore
	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	@JsonIgnore
	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Profiles getProfiles() {
		return profiles;
	}

	public void setProfiles(Profiles profiles) {
		this.profiles = profiles;
	}

	public Set<CoursesEnrollements> getCoursesEnrollements() {
		return coursesEnrollements;
	}

	public void setCoursesEnrollements(
			Set<CoursesEnrollements> coursesEnrollements) {
		this.coursesEnrollements = coursesEnrollements;
	}

	public Set<UniversityMembership> getUniversityMemberships() {
		return universityMemberships;
	}

	public void setUniversityMemberships(
			Set<UniversityMembership> universityMemberships) {
		this.universityMemberships = universityMemberships;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getLK() {
		return LK;
	}

	public void setLK(String lK) {
		LK = lK;
	}

	public Set<Cards> getCards() {
		return Cards;
	}

	public void setCards(Set<Cards> cards) {
		Cards = cards;
	}
}
