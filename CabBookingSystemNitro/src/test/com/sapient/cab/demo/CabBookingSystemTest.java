package com.sapient.cab.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;

public class CabBookingSystemTest {
	
	private static BatchCabRequest batchCabRequest = new BatchCabRequest();
	private static List<Cab> cabs = new ArrayList<Cab>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		
		//change input request and then see the variation in response
		batchCabRequest.getBatchCabRequests().add(new CabRequest("BR001", 100025, 100036, simpleFormatter.parse("2016/01/11 10:00:00")));
		batchCabRequest.getBatchCabRequests().add(new CabRequest("BR002", 100056, 100042, simpleFormatter.parse("2016/01/11 11:00:00")));
		batchCabRequest.getBatchCabRequests().add(new CabRequest("BR003", 100044, 100056, simpleFormatter.parse("2016/01/11 12:00:00")));
		batchCabRequest.getBatchCabRequests().add(new CabRequest("BR004", 100028, 100036, simpleFormatter.parse("2016/01/11 15:00:00")));
		
		cabs.add(new Cab("DL01HB001", 100020,true));
		cabs.add(new Cab("DL01HB002", 100040,true));
		cabs.add(new Cab("DL01HB003", 100060,true));
		cabs.add(new Cab("DL01HB004", 100080,true));
	}


	@Test
	public void testBookCab() {
		CabBookingSystem demo = new CabBookingSystem();
		
		Map<String, String> result = demo.bookCab(batchCabRequest, cabs);
		for(Entry<String, String> entry : result.entrySet()){
			System.out.println("Booking Id = "+entry.getKey()+" is assigned Cab Number =" +entry.getValue());	
		}
		
		
	}
}
