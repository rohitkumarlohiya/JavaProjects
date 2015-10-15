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
@Table(name = "UN_USER_CONTENT", catalog = "ED")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserContent extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ACCOUNT_ID", nullable = false)
	private long accountId;

	@Column(name = "CONTENT_ID", nullable = false)
	private long contentId;

	@Column(name = "COURSE_ID", nullable = false)
	private long courseId;

	@Column(name = "COMPLETION_STATUS", nullable = true)
	private int completionStatus;

	@Column(name = "TIME_SPENT", nullable = true)
	private int timeSpent;

	@Column(name = "ACTIVITY", length = 30, nullable = true)
	private String activity;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public int getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(int completionStatus) {
		this.completionStatus = completionStatus;
	}

	public int getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(int timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
}
