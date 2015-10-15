package org.firstonlineuniversity.models.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UN_SITE_SUMMARY_V", catalog = "ED")
public class SiteSummaryView {

	@Column(name = "members")
	private int members;

	@Column(name = "gurus")
	private int gurus;

	@Id
	@Column(name = "courses")
	private int courses;

	@Column(name = "videos")
	private int videos;

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public int getGurus() {
		return gurus;
	}

	public void setGurus(int gurus) {
		this.gurus = gurus;
	}

	public int getCourses() {
		return courses;
	}

	public void setCourses(int courses) {
		this.courses = courses;
	}

	public int getVideos() {
		return videos;
	}

	public void setVideos(int videos) {
		this.videos = videos;
	}
}
