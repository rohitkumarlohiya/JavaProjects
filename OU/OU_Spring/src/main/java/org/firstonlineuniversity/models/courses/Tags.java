package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_TAGS", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Tags extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length = 100, nullable = false, unique = true)
	private String name;

	@Column(name = "DESCRIPTION", length = 500, nullable = true)
	private String description;

	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	private Set<CourseInformation> courseInformations = new HashSet<CourseInformation>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Tags() {
		super();
	}

	public Tags(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Tags(String name, long id) {
		super();
		this.name = name;
		this.setId(id);
	}
	
	public Tags(String name, long id, String description) {
		super();
		this.name = name;
		this.setId(id);
		this.description = description;
	}

	public Set<CourseInformation> getCourseInformations() {
		return courseInformations;
	}

	public void setCourseInformations(Set<CourseInformation> courseInformations) {
		this.courseInformations = courseInformations;
	}
}
