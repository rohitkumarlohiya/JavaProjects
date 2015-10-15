package org.firstonlineuniversity.models.profiles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.Providers;

@Entity
@Table(name = "UN_ORGANIZATIONS", catalog = "ED")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization extends AbstractEntity implements Serializable{
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ORGANIZATION_TYPE", length = 20, nullable = true)
	private String organizationType;

	@Column(name = "ORGANIZATION_NAME", length = 200, nullable = true)
	private String organizationName;

	@Column(name = "ORGANIZATION_DESCRIPTION", length = 5000, nullable = true)
	private String organizationDescription;

	@Column(name = "PHOTO_LINK", length = 255, nullable = true)
	private String photoLink;

	@OneToOne(mappedBy="organization")
	private Profiles profiles;
	
	@OneToOne(mappedBy="organization")
	private Providers providers;
	
	@Column(name = "COUNTRY_CODE", length = 20, nullable = true)
	private String countryCode;

	@Column(name = "PARTNER_FLAG", nullable = true)
	private boolean partnerFlag;

	@Column(name = "LOGO_LINK", length = 255, nullable = true)
	private String logoLink;

	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Organization(String organizationType, String organizationName,
			String organizationDescription, String photoLink,
			Profiles profiles, Providers providers, String countryCode,
			boolean partnerFlag, String logoLink) {
		super();
		this.organizationType = organizationType;
		this.organizationName = organizationName;
		this.organizationDescription = organizationDescription;
		this.photoLink = photoLink;
		this.profiles = profiles;
		this.providers = providers;
		this.countryCode = countryCode;
		this.partnerFlag = partnerFlag;
		this.logoLink = logoLink;
	}
	
	public Organization(String organizationType, String organizationName,
			String organizationDescription, String photoLink, String logoLink, Long id) {
		super();
		this.organizationType = organizationType;
		this.organizationName = organizationName;
		this.organizationDescription = organizationDescription;
		this.photoLink = photoLink;
		this.logoLink = logoLink;
		this.setId(id);
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationDescription() {
		return organizationDescription;
	}

	public void setOrganizationDescription(String organizationDescription) {
		this.organizationDescription = organizationDescription;
	}

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	public Profiles getProfiles() {
		return profiles;
	}

	public void setProfiles(Profiles profiles) {
		this.profiles = profiles;
	}

	public Providers getProviders() {
		return providers;
	}

	public void setProviders(Providers providers) {
		this.providers = providers;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isPartnerFlag() {
		return partnerFlag;
	}

	public void setPartnerFlag(boolean partnerFlag) {
		this.partnerFlag = partnerFlag;
	}

	public String getLogoLink() {
		return logoLink;
	}

	public void setLogoLink(String logoLink) {
		this.logoLink = logoLink;
	}
}
