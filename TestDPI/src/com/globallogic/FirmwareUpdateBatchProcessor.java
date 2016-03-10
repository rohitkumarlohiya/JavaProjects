package com.globallogic;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FirmwareUpdateBatchProcessor extends GenericUpdate implements
		Runnable {

	public FirmwareUpdateBatchProcessor(int noOfConcurrentUpdate,
			int noOfConsecutiveFailure, Date startProcessingTime,
			Date stopProcessingTime, Date nextOpenHourStart,
			Date nextOpenHourStop) {
		super();

		this.noOfConcurrentUpdate = noOfConcurrentUpdate;
		this.noOfConsecutiveFailure = noOfConsecutiveFailure;
		this.startProcessingTime = startProcessingTime;
		this.stopProcessingTime = stopProcessingTime;
		this.nextOpenHourStart = nextOpenHourStart;
		this.nextOpenHourStop = nextOpenHourStop;

	}

	public void submit() {

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(this);
		executorService.shutdown();
		return;
	}

	@Override
	public void run() {
		int maxThreadPoolSize = noOfConcurrentUpdate;

		ScheduledExecutorService scheduledExecutorService = Executors
				.newScheduledThreadPool(maxThreadPoolSize);

		scheduledExecutorService.schedule(new Runnable() {

			@Override
			public void run() {
				System.out.println("inside firmware run => " + new Date());

			}
		}, 5, TimeUnit.SECONDS);
		
		scheduledExecutorService.shutdown();
		while(!scheduledExecutorService.isTerminated());
	}
}
