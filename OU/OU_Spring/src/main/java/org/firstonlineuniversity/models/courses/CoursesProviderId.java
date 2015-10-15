package org.firstonlineuniversity.models.courses;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Embeddable
public class CoursesProviderId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	private CourseInformation courseInformation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	private Providers providers;

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CoursesProviderId that = (CoursesProviderId) o;

		if (courseInformation != null ? !courseInformation
				.equals(that.courseInformation)
				: that.courseInformation != null)
			return false;
		if (providers != null ? !providers.equals(that.providers)
				: that.providers != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (courseInformation != null ? courseInformation.hashCode() : 0);
		result = 31 * result + (providers != null ? providers.hashCode() : 0);
		return result;
	}

	public CourseInformation getCourseInformation() {
		return courseInformation;
	}

	public void setCourseInformation(CourseInformation courseInformation) {
		this.courseInformation = courseInformation;
	}

	public Providers getProviders() {
		return providers;
	}

	public void setProviders(Providers providers) {
		this.providers = providers;
	}
	
	
}
