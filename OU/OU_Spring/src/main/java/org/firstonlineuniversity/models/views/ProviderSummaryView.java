package org.firstonlineuniversity.models.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UN_PROVIDER_SUMMARY_V", catalog = "ED")
public class ProviderSummaryView {

	@Id
	@Column(name = "PROVIDER_ID")
	private Long providerId;

	@Column(name = "ACCOUNT_ID")
	private Long accountId;

	@Column(name = "PROVIDER_NAME")
	private String providerName;

	@Column(name = "COURSES")
	private int courses;

	@Column(name = "VIDEOS")
	private int videos;

	@Column(name = "ENROLLEMENTS")
	private int enrollements;

	@Column(name = "REVENUE")
	private double revenue;

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public int getCourses() {
		return courses;
	}

	public void setCourses(int courses) {
		this.courses = courses;
	}

	public int getVideos() {
		return videos;
	}

	public void setVideos(int videos) {
		this.videos = videos;
	}

	public int getEnrollements() {
		return enrollements;
	}

	public void setEnrollements(int enrollements) {
		this.enrollements = enrollements;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
}
