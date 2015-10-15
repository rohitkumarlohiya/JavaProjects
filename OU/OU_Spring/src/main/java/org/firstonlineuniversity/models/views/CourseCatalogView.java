package org.firstonlineuniversity.models.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UN_COURSE_CATALOG_V", catalog = "ED")
public class CourseCatalogView {

	@Id
	@Column(name = "COURSE_ID")
	private Long courseId;

	@Column(name = "COURSE_NAME")
	private String courseName;

	@Column(name = "COURSE_IMAGE_LINK")
	private String courseImageLink;

	@Column(name = "PROMO_VIDEO_LINK")
	private String promoVideoLink;

	@Column(name = "COURSE_LOAD")
	private String courseLoad;

	@Column(name = "CREDITS")
	private Short credits;

	@Column(name = "COURSE_FORMAT")
	private String courseFormat;

	@Column(name = "DIFFICULTY_LEVEL", length = 30, nullable = true)
	private String difficultyLevel;

	@Column(name = "ORGANIZATION_ID")
	private Long organizationId;

	@Column(name = "ORGANIZATION_NAME")
	private String organizationName;

	@Column(name = "PRIMARY_PROVIDER_NAME")
	private String primaryProviderName;

	@Column(name = "PROVIDER_ID")
	private Long providerId;

	@Column(name = "OTHER_PROVIDER_NAMES")
	private String otherProviderNames;

	@Column(name = "OTHER_PROVIDER_COUNT")
	private Integer otherProviderCount;

	@Column(name = "PAID_COURSE")
	private Boolean paidCourse;

	@Column(name = "PRICE_ID")
	private Long priceId;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "DISCOUNT")
	private Double discount;

	@Column(name = "EFFECTIVE_PRICE")
	private Double effectivePrice;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public Short getCredits() {
		return credits;
	}

	public void setCredits(Short credits) {
		this.credits = credits;
	}

	public String getCourseFormat() {
		return courseFormat;
	}

	public void setCourseFormat(String courseFormat) {
		this.courseFormat = courseFormat;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPrimaryProviderName() {
		return primaryProviderName;
	}

	public void setPrimaryProviderName(String primaryProviderName) {
		this.primaryProviderName = primaryProviderName;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getOtherProviderNames() {
		return otherProviderNames;
	}

	public void setOtherProviderNames(String otherProviderNames) {
		this.otherProviderNames = otherProviderNames;
	}

	public Integer getOtherProviderCount() {
		return otherProviderCount;
	}

	public void setOtherProviderCount(Integer otherProviderCount) {
		this.otherProviderCount = otherProviderCount;
	}

	public Boolean getPaidCourse() {
		return paidCourse;
	}

	public void setPaidCourse(Boolean paidCourse) {
		this.paidCourse = paidCourse;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getEffectivePrice() {
		return effectivePrice;
	}

	public void setEffectivePrice(Double effectivePrice) {
		this.effectivePrice = effectivePrice;
	}
}
