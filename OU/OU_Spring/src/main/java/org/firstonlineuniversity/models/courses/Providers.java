package org.firstonlineuniversity.models.courses;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.profiles.Organization;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_PROVIDERS", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Providers extends AbstractEntity {

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACCOUNT_ID")
	@JsonBackReference
	private Accounts accounts;

	@Column(name = "PROVIDER_TYPE", length = 20, nullable = true)
	private String providerType;

	@Column(name = "PROVIDER_NAME", length = 200, nullable = true)
	private String providerName;

	@Column(name = "EXPERIENCE", length = 2000, nullable = true)
	private String experience;

	@Column(name = "INTRODUCTION", length = 2000, nullable = true)
	private String introduction;

	@Column(name = "CONTACT_ID", nullable = true)
	private long contactId;

	@Column(name = "PROVIDER_PHOTO_LINK", length = 255, nullable = true)
	private String providerPhotoLink;

	@Column(name = "DESIGNATION", length = 100, nullable = true)
	private String designation;

	@Column(name = "DEPARTMENT", length = 100, nullable = true)
	private String department;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID")
	private Organization organization;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "providers")
	@JsonManagedReference
	private Set<CourseProviders> courseProviders;

	public Providers() {
		super();
	}
	
	public Providers(long id) {
		super.setId(id);
	}
	
	public Providers(long id,String providerName) {
		super.setId(id);
		this.setProviderName(providerName);
	}

	public Providers(Accounts accounts, String providerType,
			String providerName, String experience, String introduction,
			long contactId, String providerPhotoLink, String designation,
			String department, boolean enabled, Organization organization,
			Set<CourseProviders> courseProviders, Date cd, long cb) {
		super();
		this.accounts = accounts;
		this.providerType = providerType;
		this.providerName = providerName;
		this.experience = experience;
		this.introduction = introduction;
		this.contactId = contactId;
		this.providerPhotoLink = providerPhotoLink;
		this.designation = designation;
		this.department = department;
		this.setEnabled(enabled);
		this.organization = organization;
		this.courseProviders = courseProviders;
		this.setCB(cb);
		this.setCD(cd);
	}

	public Set<CourseProviders> getCourseProviders() {
		return courseProviders;
	}

	public void setCourseProviders(Set<CourseProviders> courseProviders) {
		this.courseProviders = courseProviders;
	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
