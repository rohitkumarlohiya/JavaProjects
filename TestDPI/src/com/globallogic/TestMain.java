package com.globallogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain {

	public static void main(String[] args) {

		int noOfConcurrentUpdate = 3;
		int noOfConsecutiveFailure = 5;
		Date startProcessingTime = null;
		Date stopProcessingTime = null;
		Date nextOpenHourStart = null;
		Date nextOpenHourStop = null;
		try {
			startProcessingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse("2015-05-18 15:24:00");
			stopProcessingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse("2015-05-18 15:24:00");
			nextOpenHourStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse("2015-05-18 15:24:00");
			nextOpenHourStop = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse("2015-05-18 15:24:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		CertificateUpdateBatchProcessor batchProcessor = new CertificateUpdateBatchProcessor(
				noOfConcurrentUpdate, noOfConsecutiveFailure,
				startProcessingTime, stopProcessingTime, nextOpenHourStart,
				nextOpenHourStop);

		FirmwareUpdateBatchProcessor batchProcessor2 = new FirmwareUpdateBatchProcessor(
				noOfConcurrentUpdate, noOfConsecutiveFailure,
				startProcessingTime, stopProcessingTime, nextOpenHourStart,
				nextOpenHourStop);

		batchProcessor.submit();

		batchProcessor2.submit();

		System.out.println("Return to main");
	}
}
