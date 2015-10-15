package org.firstonlineuniversity.models.userquestions;

import java.util.Date;
import java.util.UUID;

public class UserResponseComments {
	private UUID responseid;
	private UUID commentid;
	private boolean anynomous;
	private String commenttext;
	private boolean enabled;
	private Date updatedDate;
	private Long accountId;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getResponseid() {
		return responseid;
	}

	public void setResponseid(UUID responseid) {
		this.responseid = responseid;
	}

	public UUID getCommentid() {
		return commentid;
	}

	public void setCommentid(UUID commentid) {
		this.commentid = commentid;
	}

	public boolean isAnynomous() {
		return anynomous;
	}

	public void setAnynomous(boolean anynomous) {
		this.anynomous = anynomous;
	}

	public String getCommenttext() {
		return commenttext;
	}

	public void setCommenttext(String commenttext) {
		this.commenttext = commenttext;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
