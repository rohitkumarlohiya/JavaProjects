package org.firstonlineuniversity.models.courses;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "UN_COURSES_TAGS", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CoursesTags extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "COURSE_ID", length = 11, nullable = false)
	private long courseId;
	
	@Column(name = "TAG_ID", length = 11, nullable = false)
	private long tagId;

	public CoursesTags() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoursesTags(long courseId, long tagId) {
		super();
		this.courseId = courseId;
		this.tagId = tagId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
}
