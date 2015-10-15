package org.firstonlineuniversity.domains.custom;

import org.firstonlineuniversity.models.commons.AbstractEntity;

public class CustomProfile extends AbstractEntity{
	
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String sex;
	private int bdMonth;
	private int bdDay;
	private int bdYear;
	private String email;
	private String alternateEmail;
	private String phoneHome;
	private String phoneCell;
	private String phoneOffice;
	private String biography;
	private String photoLink;
	private String linkTwitter;
	private String linkFacebok;
	private String linkGooglePlus;
	private String linkLinkedin;
	private String linkWebsite;
	private String language;
	private String timeZone;
	private String dateFormat;
	private String videoPlayers;
	private String profileAccess;
	private String highestDegree;
	private String hobbies;
	
	
	
	public CustomProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomProfile(String firstName, String lastName,
			String middleInitial, String sex, int bdMonth, int bdDay,
			int bdYear, String email, String alternateEmail, String phoneHome,
			String phoneCell, String phoneOffice, String biography,
			String photoLink, String linkTwitter, String linkFacebok,
			String linkGooglePlus, String linkLinkedin, String linkWebsite,
			String language, String timeZone, String dateFormat,
			String videoPlayers, String profileAccess, String highestDegree,
			String hobbies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
		this.sex = sex;
		this.bdMonth = bdMonth;
		this.bdDay = bdDay;
		this.bdYear = bdYear;
		this.email = email;
		this.alternateEmail = alternateEmail;
		this.phoneHome = phoneHome;
		this.phoneCell = phoneCell;
		this.phoneOffice = phoneOffice;
		this.biography = biography;
		this.photoLink = photoLink;
		this.linkTwitter = linkTwitter;
		this.linkFacebok = linkFacebok;
		this.linkGooglePlus = linkGooglePlus;
		this.linkLinkedin = linkLinkedin;
		this.linkWebsite = linkWebsite;
		this.language = language;
		this.timeZone = timeZone;
		this.dateFormat = dateFormat;
		this.videoPlayers = videoPlayers;
		this.profileAccess = profileAccess;
		this.highestDegree = highestDegree;
		this.hobbies = hobbies;
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
}
