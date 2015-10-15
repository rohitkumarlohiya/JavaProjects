package org.firstonlineuniversity.models.profiles;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;

@Entity
@Table(name = "UN_PROFILES", catalog = "ED")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Profiles extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "FIRST_NAME", length = 30, nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", length = 30, nullable = false)
	private String lastName;

	@Column(name = "MIDDLE_INITIAL", length = 10, nullable = true)
	private String middleInitial;

	@Column(name = "SEX", length = 1, nullable = true)
	private String sex;

	@Column(name = "BD_MONTH", length = 11, nullable = true)
	private int bdMonth;

	@Column(name = "BD_DAY", length = 11, nullable = true)
	private int bdDay;

	@Column(name = "BD_YEAR", length = 11, nullable = true)
	private int bdYear;

	@Column(name = "EMAIL", length = 50, nullable = false)
	private String email;

	@Column(name = "ALTERNATE_EMAIL", length = 20, nullable = true)
	private String alternateEmail;

	@Column(name = "PHONE_HOME", length = 30, nullable = true)
	private String phoneHome;

	@Column(name = "PHONE_CELL", length = 30, nullable = true)
	private String phoneCell;

	@Column(name = "PHONE_OFFICE", length = 30, nullable = true)
	private String phoneOffice;

	@Column(name = "BIOGRAPHY", length = 5000, nullable = true)
	private String biography;

	@Column(name = "PHOTO_LINK", length = 255, nullable = true)
	private String photoLink;

	@Column(name = "LINK_TWITTER", length = 50, nullable = true)
	private String linkTwitter;

	@Column(name = "LINK_FACEBOOK", length = 50, nullable = true)
	private String linkFacebok;

	@Column(name = "LINK_GOOGLE_PLUS", length = 50, nullable = true)
	private String linkGooglePlus;

	@Column(name = "LINK_LINKEDIN", length = 50, nullable = true)
	private String linkLinkedin;

	@Column(name = "LINK_WEBSITE", length = 50, nullable = true)
	private String linkWebsite;

	@Column(name = "LANGUAGE", length = 255, nullable = true)
	private String language;

	@Column(name = "TIME_ZONE", length = 100, nullable = true)
	private String timeZone;

	@Column(name = "DATE_FORMAT", length = 15, nullable = true)
	private String dateFormat;

	@Column(name = "VIDEO_PLAYERS", length = 30, nullable = true)
	private String videoPlayers;

	@Column(name = "PROFILE_ACCESS", length = 20, nullable = true)
	private String profileAccess;

	@Column(name = "HIGHEST_DEGREE", length = 1000, nullable = true)
	private String highestDegree;

	@Column(name = "HOBBIES", length = 2000, nullable = true)
	private String hobbies;
	
	@Column(name = "IP", length = 30, nullable = true)
	private String IP;

	@Column(name = "LK", length = 50, nullable = true)
	private String LK;
	
	public Profiles() {
		super();
	}

	public Profiles(String firstName, String lastName, String email, Accounts accounts) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accounts = accounts;
	}
	
	

	@OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
	@JoinColumn(name="ACCOUNT_ID")
	@JsonBackReference
	private Accounts accounts;
	
	@OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
	@JoinColumn(name="ORGANIZATION_ID")
	private Organization organization;

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

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getBdMonth() {
		return bdMonth;
	}

	public void setBdMonth(int bdMonth) {
		this.bdMonth = bdMonth;
	}

	public int getBdDay() {
		return bdDay;
	}

	public void setBdDay(int bdDay) {
		this.bdDay = bdDay;
	}

	public int getBdYear() {
		return bdYear;
	}

	public void setBdYear(int bdYear) {
		this.bdYear = bdYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String getPhoneHome() {
		return phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public String getPhoneCell() {
		return phoneCell;
	}

	public void setPhoneCell(String phoneCell) {
		this.phoneCell = phoneCell;
	}

	public String getPhoneOffice() {
		return phoneOffice;
	}

	public void setPhoneOffice(String phoneOffice) {
		this.phoneOffice = phoneOffice;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	public String getLinkTwitter() {
		return linkTwitter;
	}

	public void setLinkTwitter(String linkTwitter) {
		this.linkTwitter = linkTwitter;
	}

	public String getLinkFacebok() {
		return linkFacebok;
	}

	public void setLinkFacebok(String linkFacebok) {
		this.linkFacebok = linkFacebok;
	}

	public String getLinkGooglePlus() {
		return linkGooglePlus;
	}

	public void setLinkGooglePlus(String linkGooglePlus) {
		this.linkGooglePlus = linkGooglePlus;
	}

	public String getLinkLinkedin() {
		return linkLinkedin;
	}

	public void setLinkLinkedin(String linkLinkedin) {
		this.linkLinkedin = linkLinkedin;
	}

	public String getLinkWebsite() {
		return linkWebsite;
	}

	public void setLinkWebsite(String linkWebsite) {
		this.linkWebsite = linkWebsite;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getVideoPlayers() {
		return videoPlayers;
	}

	public void setVideoPlayers(String videoPlayers) {
		this.videoPlayers = videoPlayers;
	}

	public String getProfileAccess() {
		return profileAccess;
	}

	public void setProfileAccess(String profileAccess) {
		this.profileAccess = profileAccess;
	}

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@JsonIgnore
	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	
}


