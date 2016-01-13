package com.sapient.cab.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CabBookingSystem {

	public Map<String, String> bookCab(BatchCabRequest batchCabRequest,
			List<Cab> cabs) {

		Map<String, String> mapBookingIdCabNo = new HashMap<String, String>();

		for (CabRequest cabRequest : batchCabRequest.getBatchCabRequests()) {
			mapBookingIdCabNo.put(cabRequest.getBookingId(),
					findCab(cabRequest, cabs));
		}

		return mapBookingIdCabNo;

	}

	/**
	 * @param cabRequest
	 * @param cabs
	 * @return number of the cab otherwise NA if there is no cab
	 */
	private String findCab(CabRequest cabRequest, List<Cab> cabs) {
		
		String cabNumber = "NA";
		Cab cab = getCabForMaxProfit(getAllCabsEarnAtleast20PercentProfit(
				cabRequest, getAllCabsReach15MinEarly(cabRequest, getAllAvailableCabs(cabs))));

		if (cab != null) {
			cabNumber = cab.getCabNumber();
		}
		return cabNumber;
	}

	/**
	 * Get all available cabs in the system 
	 * @param cabs
	 * @return
	 */
	public List<Cab> getAllAvailableCabs(List<Cab> cabs) {
		List<Cab> availableCabs = new ArrayList<Cab>();
		for (Cab cab : cabs) {
			if (cab.isAvailable()) {
				availableCabs.add(cab);
			}
		}
		return availableCabs;
	}
	
	/**
	 * This will return all those cab that can reach before 15 minute at the demanded location in the cab request
	 * @param cabRequest
	 * @param cabs
	 * @return
	 */
	public List<Cab> getAllCabsReach15MinEarly(CabRequest cabRequest,
			List<Cab> cabs) {
		List<Cab> reach15MinEarlyCabs = new ArrayList<Cab>();

		for (Cab cab : cabs) {

			int timeToReachPickupLoc = (int) (Math.abs(cab.getCurrentLocation()
					- cabRequest.getPickUpAreaCode())) * 2; // This is in minute

			int totalTimeforReaching15MinEarly = timeToReachPickupLoc + 15;

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, totalTimeforReaching15MinEarly);
			Date reachDate = cal.getTime();

			Date demandTimeToReach = cabRequest.getPickUpTime();

			if (demandTimeToReach.getTime() - reachDate.getTime() > 0) {
				reach15MinEarlyCabs.add(cab);
			}
		}

		return reach15MinEarlyCabs;
	}

	/**
	 * This will return all that cabs that will give atleast 20 % profit
	 * @param cabRequest
	 * @param cabs
	 * @return
	 */
	public Map<Cab, Float> getAllCabsEarnAtleast20PercentProfit(
			CabRequest cabRequest, List<Cab> cabs) {

		Map<Cab, Float> map = new HashMap<Cab, Float>();

		for (Cab cab : cabs) {

			long travelDistance = Math.abs(cabRequest.getDropAreaCode()
					- cabRequest.getPickUpAreaCode());
			long diffInKm = Math.abs(cab.getCurrentLocation()
					- cabRequest.getPickUpAreaCode());

			float cabearncost = travelDistance * 5;
			float cabspentcost = diffInKm * 5;

			float profit = (cabearncost - cabspentcost) / (cabspentcost);

			if (profit > .20) {
				map.put(cab, profit);
			}
		}
		return map;
	}

	/**
	 * This will return a cab that has maximum profit
	 * @param map
	 * @return
	 */
	public Cab getCabForMaxProfit(Map<Cab, Float> map) {
		Cab cab = null;
		float f = 0;

		for (Entry<Cab, Float> entry : map.entrySet()) {
			if (entry.getValue() > f) {
				cab = entry.getKey();
			}
		}
		return cab;
	}
}
