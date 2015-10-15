package org.firstonlineuniversity.domains.custom;

import org.firstonlineuniversity.models.courses.CourseProviders;

public class CustomCourseProviders {

	private long id;
	private String providerType;
	private String providerName;
	private String experience;
	private String introduction;
	private long contactId;
	private String providerPhotoLink;
	private String designation;
	private String department;

	private Long courseProvidersId;

	public CustomCourseProviders(String providerType, String providerName,
			String experience, String introduction, long contactId,
			String providerPhotoLink, String designation, String department,
			long id) {
		super();
		this.providerType = providerType;
		this.providerName = providerName;
		this.experience = experience;
		this.introduction = introduction;
		this.contactId = contactId;
		this.providerPhotoLink = providerPhotoLink;
		this.designation = designation;
		this.department = department;
		this.setId(id);
	}

	public CustomCourseProviders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getProviderPhotoLink() {
		return providerPhotoLink;
	}

	public void setProviderPhotoLink(String providerPhotoLink) {
		this.providerPhotoLink = providerPhotoLink;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getCourseProvidersId() {
		return courseProvidersId;
	}

	public void setCourseProvidersId(Long courseProvidersId) {
		this.courseProvidersId = courseProvidersId;
	}
}
