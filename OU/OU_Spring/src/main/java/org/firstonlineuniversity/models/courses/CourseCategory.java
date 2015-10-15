package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "UN_COURSE_CATEGORY", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourseCategory extends AbstractEntity implements Serializable{
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CATEGORY_NAME", length = 200, nullable = false)
	private String categoryName;
	
	@Column(name = "CATEGORY_IMAGE_LINK ", length = 200, nullable = true)
	private String categoryImageLink;

	@Column(name = "CATEGORY_DESCRIPTION", length = 5000, nullable = true)
	private String categoryDescription;

	@Column(name = "SUBJECT_AREA_ID", length = 11, nullable = true)
	private String subjectAreaId;

	@Column(name = "CATEGORY_CODE", length = 4, nullable = true)
	private String categoryCode;

	@Column(name = "ACTIVE_FLAG", nullable = true)
	private boolean activeFlag;
	
	@Transient
	private int courseCount;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "CATEGORY_ID")
	private Set<CourseInformation> courseInformations;

	public CourseCategory(String categoryName, String categoryImageLink,
			String categoryDescription, String subjectAreaId,
			String categoryCode, boolean activeFlag, int courseCount,
			Set<CourseInformation> courseInformations) {
		super();
		this.categoryName = categoryName;
		this.categoryImageLink = categoryImageLink;
		this.categoryDescription = categoryDescription;
		this.subjectAreaId = subjectAreaId;
		this.categoryCode = categoryCode;
		this.activeFlag = activeFlag;
		this.courseCount = courseCount;
		this.courseInformations = courseInformations;
	}

	public CourseCategory(String categoryName, long id) {
		super();
		this.categoryName = categoryName;
		this.setId(id);
	}

	public CourseCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getSubjectAreaId() {
		return subjectAreaId;
	}

	public void setSubjectAreaId(String subjectAreaId) {
		this.subjectAreaId = subjectAreaId;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Set<CourseInformation> getCourseInformations() {
		return courseInformations;
	}

	public void setCourseInformations(Set<CourseInformation> courseInformations) {
		this.courseInformations = courseInformations;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryImageLink() {
		return categoryImageLink;
	}

	public void setCategoryImageLink(String categoryImageLink) {
		this.categoryImageLink = categoryImageLink;
	}

	public int getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}
}
