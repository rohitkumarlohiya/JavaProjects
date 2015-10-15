package org.firstonlineuniversity.models.courses;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "UN_COURSE_PRICES", catalog = "ED")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CoursePrices extends AbstractEntity implements Serializable {
	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "PRICE_TYPE", length = 45, nullable = false)
	private String priceType;
	
	@Column(name = "CURRENCY", length = 10, nullable = false)
	private String currency;
	
	@Column(name = "PRICE", length = 10, nullable = false)
	private double price;
	
	@Column(name = "DISCOUNT_TYPE", length = 20, nullable = true)
	private String discountType;
	
	@Column(name = "DISCOUNT", nullable = true)
	private double discount;
	
	@Column(name = "DISCOUNT_PERCEMT", nullable = true)
	private double discountPercent;
	
	@Column(name = "START_DATE", nullable = true)
	private Date startDate;
	
	@Column(name = "END_DATE", nullable = true)
	private Date endDate;
	
	@Transient
	private String startDateString;
	
	@Transient
	private String endDateString;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "COURSE_ID")
	private CourseInformation courses;

	public CoursePrices(String priceType, String currency, double price,
			String discountType, double discount, double discountPercent,
			Date startDate, Date endDate, String startDateString,
			String endDateString, CourseInformation courses) {
		super();
		this.priceType = priceType;
		this.currency = currency;
		this.price = price;
		this.discountType = discountType;
		this.discount = discount;
		this.discountPercent = discountPercent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startDateString = startDateString;
		this.endDateString = endDateString;
		this.courses = courses;
	}

	public CoursePrices(Long id,String priceType, String currency, double price) {
		super();
		this.priceType = priceType;
		this.currency = currency;
		this.price = price;
		this.setId(id);
	}

	public CoursePrices() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public CourseInformation getCourses() {
		return courses;
	}

	public void setCourses(CourseInformation courses) {
		this.courses = courses;
	}

}
