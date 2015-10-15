package org.firstonlineuniversity.domains.custom;

import java.util.Map;

public class CoursesNavigationGroups {
	private Map<String, String> difficultyLevelMap;
	private Map<String, String> languageMap;
	private Map<String, String> courseTypeMap;
	private Map<String, String> courseFormatlMap;
	private Map<String, String> categoriesMap;
	private Map<String, String> organizationMap;
	private Map<String, String> authorMap;

	public CoursesNavigationGroups(Map<String, String> difficultyLevelMap,
			Map<String, String> languageMap, Map<String, String> courseTypeMap,
			Map<String, String> courseFormatlMap,
			Map<String, String> categoriesMap,
			Map<String, String> organizationMap, Map<String, String> authorMap) {
		super();
		this.difficultyLevelMap = difficultyLevelMap;
		this.languageMap = languageMap;
		this.courseTypeMap = courseTypeMap;
		this.courseFormatlMap = courseFormatlMap;
		this.categoriesMap = categoriesMap;
		this.organizationMap = organizationMap;
		this.authorMap = authorMap;
	}

	public CoursesNavigationGroups() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map<String, String> getDifficultyLevelMap() {
		return difficultyLevelMap;
	}

	public void setDifficultyLevelMap(Map<String, String> difficultyLevelMap) {
		this.difficultyLevelMap = difficultyLevelMap;
	}

	public Map<String, String> getLanguageMap() {
		return languageMap;
	}

	public void setLanguageMap(Map<String, String> languageMap) {
		this.languageMap = languageMap;
	}

	public Map<String, String> getCourseTypeMap() {
		return courseTypeMap;
	}

	public void setCourseTypeMap(Map<String, String> courseTypeMap) {
		this.courseTypeMap = courseTypeMap;
	}

	public Map<String, String> getCourseFormatlMap() {
		return courseFormatlMap;
	}

	public void setCourseFormatlMap(Map<String, String> courseFormatlMap) {
		this.courseFormatlMap = courseFormatlMap;
	}

	public Map<String, String> getCategoriesMap() {
		return categoriesMap;
	}

	public void setCategoriesMap(Map<String, String> categoriesMap) {
		this.categoriesMap = categoriesMap;
	}

	public Map<String, String> getOrganizationMap() {
		return organizationMap;
	}

	public void setOrganizationMap(Map<String, String> organizationMap) {
		this.organizationMap = organizationMap;
	}

	public Map<String, String> getAuthorMap() {
		return authorMap;
	}

	public void setAuthorMap(Map<String, String> authorMap) {
		this.authorMap = authorMap;
	}
}
