package com.sapient.cab.demo;

public class Cab {

	private String cabNumber;
	private long currentLocation;
	private boolean isAvailable;

	public Cab(String cabNumber, long currentLocation, boolean isAvailable) {
		super();
		this.cabNumber = cabNumber;
		this.currentLocation = currentLocation;
		this.isAvailable = isAvailable;
	}

	/**
	 * @return the cabNumber
	 */
	public String getCabNumber() {
		return cabNumber;
	}

	/**
	 * @param cabNumber
	 *            the cabNumber to set
	 */
	public void setCabNumber(String cabNumber) {
		this.cabNumber = cabNumber;
	}

	

	/**
	 * @return the currentLocation
	 */
	public long getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(long currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * @return the isAvailable
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	/**
	 * @param isAvailable
	 *            the isAvailable to set
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
