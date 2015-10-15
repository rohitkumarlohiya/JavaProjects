package org.firstonlineuniversity.domains.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UN_COURSE_FILTERS_V", catalog = "ED")
public class CustomCoursesFilterNav {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "TYPE")
	private String type;

	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
