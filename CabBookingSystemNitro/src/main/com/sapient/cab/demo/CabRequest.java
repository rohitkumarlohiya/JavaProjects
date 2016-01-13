package com.sapient.cab.demo;

import java.util.Date;

/**
 * @author indiahiring
 *
 */
public class CabRequest {

	private String bookingId;
	private long pickUpAreaCode;
	private long dropAreaCode;
	private Date pickUpTime;

	public CabRequest(String bookingId, long pickUpAreaCode,
			long dropAreaCode, Date pickUpTime) {
		super();
		this.bookingId = bookingId;
		this.pickUpAreaCode = pickUpAreaCode;
		this.dropAreaCode = dropAreaCode;
		this.pickUpTime = pickUpTime;
	}

	/**
	 * @return the bookingId
	 */
	public String getBookingId() {
		return bookingId;
	}

	/**
	 * @param bookingId
	 *            the bookingId to set
	 */
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * @return the pickUpAreaCode
	 */
	public long getPickUpAreaCode() {
		return pickUpAreaCode;
	}

	/**
	 * @param pickUpAreaCode the pickUpAreaCode to set
	 */
	public void setPickUpAreaCode(long pickUpAreaCode) {
		this.pickUpAreaCode = pickUpAreaCode;
	}

	/**
	 * @return the dropAreaCode
	 */
	public long getDropAreaCode() {
		return dropAreaCode;
	}

	/**
	 * @param dropAreaCode the dropAreaCode to set
	 */
	public void setDropAreaCode(long dropAreaCode) {
		this.dropAreaCode = dropAreaCode;
	}

	/**
	 * @return the pickUpTime
	 */
	public Date getPickUpTime() {
		return pickUpTime;
	}

	/**
	 * @param pickUpTime the pickUpTime to set
	 */
	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	
}
