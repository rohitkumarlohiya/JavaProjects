package org.firstonlineuniversity.domains.custom;

import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CoursePrices;

public class CustomCoursesInformation extends AbstractEntity {

	private String courseName;
	private String courseDescription;
	private String courseLongDescription;
	private String courseDuration;
	private String preRequisite;
	private String difficultyLevel;
	private String versionNumber;
	private String versionDescription;
	private String courseResources;
	private String courseLogistics;
	private String courseType;
	private String courseFaq;
	private boolean newCourse;
	private boolean popularCourse;
	private boolean featuredCourse;
	private String courseImageLink;
	private String promoVideoLink;
	private String courseLoad;
	private long organizationId;
	private short credits;
	private boolean paidCourse;
	private String primaryLanguage;
	private CoursePrices coursePrices;

	public CustomCoursesInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomCoursesInformation(long id, String courseName,
			String courseDescription, String courseLongDescription,
			String courseDuration, String preRequisite, String difficultyLevel,
			String versionNumber, String versionDescription,
			String courseResources, String courseLogistics, String courseType,
			String courseFaq, boolean newCourse, boolean popularCourse,
			boolean featuredCourse, String courseImageLink,
			String promoVideoLink, String courseLoad, long organizationId,
			String primaryLanguage) {
		super();
		this.organizationId = organizationId;
		this.primaryLanguage = primaryLanguage;
		this.organizationId = organizationId;
		this.primaryLanguage = primaryLanguage;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseLongDescription = courseLongDescription;
		this.courseDuration = courseDuration;
		this.preRequisite = preRequisite;
		this.difficultyLevel = difficultyLevel;
		this.versionNumber = versionNumber;
		this.versionDescription = versionDescription;
		this.courseResources = courseResources;
		this.courseLogistics = courseLogistics;
		this.courseType = courseType;
		this.courseFaq = courseFaq;
		this.newCourse = newCourse;
		this.popularCourse = popularCourse;
		this.featuredCourse = featuredCourse;
		this.courseImageLink = courseImageLink;
		this.promoVideoLink = promoVideoLink;
		this.courseLoad = courseLoad;
		this.setId(id);
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseLongDescription() {
		return courseLongDescription;
	}

	public void setCourseLongDescription(String courseLongDescription) {
		this.courseLongDescription = courseLongDescription;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getPreRequisite() {
		return preRequisite;
	}

	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public String getCourseResources() {
		return courseResources;
	}

	public void setCourseResources(String courseResources) {
		this.courseResources = courseResources;
	}

	public String getCourseLogistics() {
		return courseLogistics;
	}

	public void setCourseLogistics(String courseLogistics) {
		this.courseLogistics = courseLogistics;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseFaq() {
		return courseFaq;
	}

	public void setCourseFaq(String courseFaq) {
		this.courseFaq = courseFaq;
	}

	public boolean isNewCourse() {
		return newCourse;
	}

	public void setNewCourse(boolean newCourse) {
		this.newCourse = newCourse;
	}

	public boolean isPopularCourse() {
		return popularCourse;
	}

	public void setPopularCourse(boolean popularCourse) {
		this.popularCourse = popularCourse;
	}

	public boolean isFeaturedCourse() {
		return featuredCourse;
	}

	public void setFeaturedCourse(boolean featuredCourse) {
		this.featuredCourse = featuredCourse;
	}

	public String getCourseImageLink() {
		return courseImageLink;
	}

	public void setCourseImageLink(String courseImageLink) {
		this.courseImageLink = courseImageLink;
	}

	public String getPromoVideoLink() {
		return promoVideoLink;
	}

	public void setPromoVideoLink(String promoVideoLink) {
		this.promoVideoLink = promoVideoLink;
	}

	public String getCourseLoad() {
		return courseLoad;
	}

	public void setCourseLoad(String courseLoad) {
		this.courseLoad = courseLoad;
	}

	

	public short getCredits() {
		return credits;
	}

	public void setCredits(short credits) {
		this.credits = credits;
	}

	public boolean isPaidCourse() {
		return paidCourse;
	}

	public void setPaidCourse(boolean paidCourse) {
		this.paidCourse = paidCourse;
	}

	public CoursePrices getCoursePrices() {
		return coursePrices;
	}

	public void setCoursePrices(CoursePrices coursePrices) {
		this.coursePrices = coursePrices;
	}
}
