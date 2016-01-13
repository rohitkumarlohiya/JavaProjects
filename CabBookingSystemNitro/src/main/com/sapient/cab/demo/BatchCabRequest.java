package com.sapient.cab.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author indiahiring
 *
 */
public class BatchCabRequest {

	private List<CabRequest> batchCabRequests = new ArrayList<CabRequest>();

	/**
	 * @return the batchCabRequests
	 */
	public List<CabRequest> getBatchCabRequests() {
		return batchCabRequests;
	}

	/**
	 * @param batchCabRequests
	 *            the batchCabRequests to set
	 */
	public void setBatchCabRequests(List<CabRequest> batchCabRequests) {
		this.batchCabRequests = batchCabRequests;
	}

}
