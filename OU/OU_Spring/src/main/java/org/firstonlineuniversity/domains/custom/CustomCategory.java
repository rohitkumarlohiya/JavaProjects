package org.firstonlineuniversity.domains.custom;

import org.firstonlineuniversity.models.commons.AbstractEntity;

public class CustomCategory extends AbstractEntity {
	private String categoryName;
	private String categoryDescription;
	private String subjectAreaId;
	private String categoryCode;
	private boolean activeFlag;
	private String imageLink;
	private int courseSize;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public CustomCategory(String categoryName,long id, String categoryDescription,
			String subjectAreaId, String categoryCode, boolean activeFlag,String imageLink) {
		super();
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.subjectAreaId = subjectAreaId;
		this.categoryCode = categoryCode;
		this.activeFlag = activeFlag;
		this.imageLink=imageLink;
		this.setId(id);
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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public CustomCategory(String categoryName, long id) {
		super();
		this.categoryName = categoryName;
		this.setId(id);
	}

	public CustomCategory() {
		super();
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getCourseSize() {
		return courseSize;
	}

	public void setCourseSize(int courseSize) {
		this.courseSize = courseSize;
	}
}
