package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_COURSE_STATUS", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CourseStatuses extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "COURSE_ID")
	private CourseInformation course;
	
	@Column(name = "ACCOUNT_ID", nullable = false)
	private long accountId;
	
	@Column(name = "STATUS_INDEX", nullable = false)
	private int statusIndex;
	
	@Column(name = "COURSE_STATUS", length = 255, nullable = false)
	private String courseStatus;
	
	@Column(name = "SUBMISSION_DATE", nullable = false)
	private Date submissionDate;
	
	@Column(name = "SUBMISSION_COMMENT", length = 500, nullable = false)
	private String submissionComment;
	
	@Column(name = "APPROVAL_COMMENT", length = 500, nullable = true)
	private String approvalComment;
	
	@Column(name = "APPROVER_ID", nullable = true)
	private long approverId;

	public CourseStatuses(CourseInformation course, long accountId,
			int statusIndex, String courseStatus, Date submissionDate,
			String submissionComment, String approvalComment, long approverId) {
		super();
		this.course = course;
		this.accountId = accountId;
		this.statusIndex = statusIndex;
		this.courseStatus = courseStatus;
		this.submissionDate = submissionDate;
		this.submissionComment = submissionComment;
		this.approvalComment = approvalComment;
		this.approverId = approverId;
	}

	public CourseStatuses() {
		super();
	}

	public CourseInformation getCourse() {
		return course;
	}

	public void setCourse(CourseInformation course) {
		this.course = course;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public int getStatusIndex() {
		return statusIndex;
	}

	public void setStatusIndex(int statusIndex) {
		this.statusIndex = statusIndex;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getSubmissionComment() {
		return submissionComment;
	}

	public void setSubmissionComment(String submissionComment) {
		this.submissionComment = submissionComment;
	}

	public String getApprovalComment() {
		return approvalComment;
	}

	public void setApprovalComment(String approvalComment) {
		this.approvalComment = approvalComment;
	}

	public long getApproverId() {
		return approverId;
	}

	public void setApproverId(long approverId) {
		this.approverId = approverId;
	}
}
