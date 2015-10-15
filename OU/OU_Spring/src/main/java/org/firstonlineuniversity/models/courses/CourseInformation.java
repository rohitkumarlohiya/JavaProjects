package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_COURSE_INFORMATION", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CourseInformation extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "COURSE_NAME", length = 300, nullable = true, unique = true)
	private String courseName;

	@Column(name = "COURSE_DESCRIPTION", length = 1000, nullable = true)
	private String courseDescription;

	@Column(name = "COURSE_LONG_DESCRIPTION", length = 5000, nullable = true)
	private String courseLongDescription;

	@Column(name = "COURSE_DURATION", length = 80, nullable = true)
	private String courseDuration;

	@Column(name = "PRE_REQUISITE", length = 5000, nullable = true)
	private String preRequisite;

	@Column(name = "DIFFICULTY_LEVEL", length = 30, nullable = true)
	private String difficultyLevel;

	@Column(name = "VERSION_NUMBER", length = 10, nullable = true)
	private String versionNumber;

	@Column(name = "VERSION_DESCRIPTION", length = 5000, nullable = true)
	private String versionDescription;

	@Column(name = "COURSE_RESOURCES", length = 5000, nullable = true)
	private String courseResources;

	@Column(name = "COURSE_LOGISTICS", length = 5000, nullable = true)
	private String courseLogistics;

	@Column(name = "COURSE_TYPE", length = 10, nullable = true)
	private String courseType;

	@Column(name = "COURSE_FAQ", length = 5000, nullable = true)
	private String courseFaq;

	@Column(name = "COURSE_KEY", length = 200, nullable = true)
	private String courseKey;

	@Column(name = "NEW_COURSE", nullable = true)
	private boolean newCourse;

	@Column(name = "POPULAR_COURSE", nullable = true)
	private boolean popularCourse;

	@Column(name = "FEATURED_COURSE", nullable = true)
	private boolean featuredCourse;

	@Column(name = "PAID_COURSE", nullable = true)
	private boolean paidCourse;

	@Column(name = "VERIFIED_CERTIFICATE", nullable = true)
	private boolean verifiedCertificate;

	@Column(name = "COURSE_IMAGE_LINK", length = 200, nullable = true)
	private String courseImageLink;

	@Column(name = "PROMO_VIDEO_LINK", length = 200, nullable = true)
	private String promoVideoLink;

	@Column(name = "COURSE_LOAD", length = 80, nullable = true)
	private String courseLoad;

	@Column(name = "STARTING_SOON", nullable = true)
	private boolean startingSoon;

	@Column(name = "ORGANIZATION_ID", nullable = true)
	private long organizationId;

	@Transient
	private String organizationName;

	@Transient
	private String tagString;

	@Column(name = "PRIMARY_LANGUAGE", length = 20, nullable = true)
	private String primaryLanguage;

	@Column(name = "CREDITS", nullable = true, unique = false)
	private short credits;

	@Transient
	private long providerId;

	@Transient
	private Providers provider;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private CourseCategory courseCategory;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="course")
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CourseSections> courseSections;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="course")
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CourseLectures> courseLectures;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="course")
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CourseStatuses> courseStatuses;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="course")
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CourseContent> courseFiles;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="course")
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CourseResources> courseResource;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="course")
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<QuizInformation> quizInformations;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="courses")
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CoursePrices> prices;

	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinTable(name = "UN_COURSES_TAGS", joinColumns = { @JoinColumn(name = "COURSE_ID") }, inverseJoinColumns = { @JoinColumn(name = "TAG_ID") })
	private Set<Tags> tags = new HashSet<Tags>();

	public CourseInformation(String courseName, String courseType,
			String courseImageLink, String courseLoad, short credits,
			Set<CoursePrices> prices) {
		super();
		this.courseName = courseName;
		this.courseType = courseType;
		this.courseImageLink = courseImageLink;
		this.courseLoad = courseLoad;
		this.credits = credits;
		this.prices = prices;
	}

	public CourseInformation() {
		super();
	}

	public CourseInformation(String courseName, long id) {
		super();
		this.courseName = courseName;
		super.setId(id);
	}

	public CourseInformation(String courseName, String courseDescription,
			String courseLongDescription, String courseDuration,
			String preRequisite, String difficultyLevel, String versionNumber,
			String versionDescription, String courseResources,
			String courseLogistics, String courseType, String courseFaq,
			boolean newCourse, boolean popularCourse, boolean featuredCourse,
			String courseImageLink, String promoVideoLink, String courseLoad,
			boolean startingSoon, long organizationId, String primaryLanguage,
			CourseCategory courseCategory) {
		super();
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseLongDescription = courseLongDescription;
		this.courseDuration = courseDuration;
		this.preRequisite = preRequisite;
		this.difficultyLevel = difficultyLevel;
		this.versionNumber = versionNumber;
		this.versionDescription = versionDescription;
		this.courseResources = courseResources;
		this.courseLogistics = courseLogistics;
		this.courseType = courseType;
		this.courseFaq = courseFaq;
		this.newCourse = newCourse;
		this.popularCourse = popularCourse;
		this.featuredCourse = featuredCourse;
		this.courseImageLink = courseImageLink;
		this.promoVideoLink = promoVideoLink;
		this.courseLoad = courseLoad;
		this.startingSoon = startingSoon;
		this.organizationId = organizationId;
		this.primaryLanguage = primaryLanguage;
		this.courseCategory = courseCategory;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public String getCourseFaq() {
		return courseFaq;
	}

	public void setCourseFaq(String courseFaq) {
		this.courseFaq = courseFaq;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseLongDescription() {
		return courseLongDescription;
	}

	public void setCourseLongDescription(String courseLongDescription) {
		this.courseLongDescription = courseLongDescription;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getPreRequisite() {
		return preRequisite;
	}

	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public String getCourseResources() {
		return courseResources;
	}

	public void setCourseResources(String courseResources) {
		this.courseResources = courseResources;
	}

	public String getCourseLogistics() {
		return courseLogistics;
	}

	public void setCourseLogistics(String courseLogistics) {
		this.courseLogistics = courseLogistics;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public boolean isNewCourse() {
		return newCourse;
	}

	public void setNewCourse(boolean newCourse) {
		this.newCourse = newCourse;
	}

	public boolean isPopularCourse() {
		return popularCourse;
	}

	public void setPopularCourse(boolean popularCourse) {
		this.popularCourse = popularCourse;
	}

	public boolean isFeaturedCourse() {
		return featuredCourse;
	}

	public void setFeaturedCourse(boolean featuredCourse) {
		this.featuredCourse = featuredCourse;
	}

	public String getCourseImageLink() {
		return courseImageLink;
	}

	public void setCourseImageLink(String courseImageLink) {
		this.courseImageLink = courseImageLink;
	}

	public String getCourseLoad() {
		return courseLoad;
	}

	public void setCourseLoad(String courseLoad) {
		this.courseLoad = courseLoad;
	}

	public CourseCategory getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(CourseCategory courseCategory) {
		this.courseCategory = courseCategory;
	}

	public String getPromoVideoLink() {
		return promoVideoLink;
	}

	public void setPromoVideoLink(String promoVideoLink) {
		this.promoVideoLink = promoVideoLink;
	}

	public boolean isStartingSoon() {
		return startingSoon;
	}

	public void setStartingSoon(boolean startingSoon) {
		this.startingSoon = startingSoon;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}

	public Set<CourseSections> getCourseSections() {
		return courseSections;
	}

	public void setCourseSections(Set<CourseSections> courseSections) {
		this.courseSections = courseSections;
	}

	public Set<CourseLectures> getCourseLectures() {
		return courseLectures;
	}

	public void setCourseLectures(Set<CourseLectures> courseLectures) {
		this.courseLectures = courseLectures;
	}

	public Set<CourseContent> getCourseFiles() {
		return courseFiles;
	}

	public void setCourseFiles(Set<CourseContent> courseFiles) {
		this.courseFiles = courseFiles;
	}

	public String getCourseKey() {
		return courseKey;
	}

	public void setCourseKey(String courseKey) {
		this.courseKey = courseKey;
	}

	public Set<CourseResources> getCourseResource() {
		return courseResource;
	}

	public void setCourseResource(Set<CourseResources> courseResource) {
		this.courseResource = courseResource;
	}

	public Set<QuizInformation> getQuizInformations() {
		return quizInformations;
	}

	public void setQuizInformations(Set<QuizInformation> quizInformations) {
		this.quizInformations = quizInformations;
	}

	public boolean isPaidCourse() {
		return paidCourse;
	}

	public void setPaidCourse(boolean paidCourse) {
		this.paidCourse = paidCourse;
	}

	public boolean isVerifiedCertificate() {
		return verifiedCertificate;
	}

	public void setVerifiedCertificate(boolean verifiedCertificate) {
		this.verifiedCertificate = verifiedCertificate;
	}

	public short getCredits() {
		return credits;
	}

	public void setCredits(short credits) {
		this.credits = credits;
	}

	public Set<CoursePrices> getPrices() {
		return prices;
	}

	public void setPrices(Set<CoursePrices> prices) {
		this.prices = prices;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Providers getProvider() {
		return provider;
	}

	public void setProvider(Providers provider) {
		this.provider = provider;
	}

	public Set<CourseStatuses> getCourseStatuses() {
		return courseStatuses;
	}

	public void setCourseStatuses(Set<CourseStatuses> courseStatuses) {
		this.courseStatuses = courseStatuses;
	}

	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}

	public Set<Tags> getTags() {
		return tags;
	}

	public void setTags(Set<Tags> tags) {
		this.tags = tags;
	}
}
