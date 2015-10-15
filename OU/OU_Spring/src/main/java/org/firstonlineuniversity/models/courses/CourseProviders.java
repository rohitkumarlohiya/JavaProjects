package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_COURSE_PROVIDERS", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CourseProviders extends AbstractEntity implements Serializable {

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;
   
	@Column(name = "PRIMARY_PROVIDER", nullable = true)
	private boolean primaryProvider;
	
	@Column(name = "PROVIDER_VISIBLE", nullable = true)
	private boolean providerVisible;
	
	@Column(name = "PROVIDER_ENABLED", nullable = true)
	private boolean providerEnabled;

	@Column(name = "PROVIDER_ROLE", length = 30, nullable = true)
	private String providerRole;
	
	@Column(name = "COURSES_ID", nullable = false, unique = false)
	private long courses;
	
	@Column(name = "ACCOUNT_ID", nullable = true, unique = false)
	private Long accountId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROVIDER_ID", nullable = false)
	@JsonManagedReference
	private Providers providers;
	
	public CourseProviders(boolean primaryProvider, String providerRole,
			long courses, Providers providers, long cb, Date cd) {
		super();
		this.primaryProvider = primaryProvider;
		this.providerRole = providerRole;
		this.courses = courses;
		this.providers = providers;
		this.setCD(cd);
		this.setCB(cb);
	}

	public CourseProviders() {
		super();
	}

	public Providers getProviders() {
		return providers;
	}

	public void setProviders(Providers providers) {
		this.providers = providers;
	}

	public long getCourses() {
		return courses;
	}

	public void setCourses(long courses) {
		this.courses = courses;
	}

	public boolean isPrimaryProvider() {
		return primaryProvider;
	}

	public void setPrimaryProvider(boolean primaryProvider) {
		this.primaryProvider = primaryProvider;
	}

	public String getProviderRole() {
		return providerRole;
	}

	public void setProviderRole(String providerRole) {
		this.providerRole = providerRole;
	}

	public boolean isProviderVisible() {
		return providerVisible;
	}

	public void setProviderVisible(boolean providerVisible) {
		this.providerVisible = providerVisible;
	}

	public boolean isProviderEnabled() {
		return providerEnabled;
	}

	public void setProviderEnabled(boolean providerEnabled) {
		this.providerEnabled = providerEnabled;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
