package com.globallogic;

import java.util.Date;

public abstract class GenericUpdate {

	protected int noOfConcurrentUpdate;
	protected int noOfConsecutiveFailure;

	// open hour concept
	protected Date startProcessingTime;
	protected Date stopProcessingTime;
	protected Date nextOpenHourStart;
	protected Date nextOpenHourStop;

	protected static final int CONCURRENT_UPDATE_MIN = 1;
	protected static final int CONCURRENT_UPDATE_MAX = 5000;
	protected static final int CONCURRENT_UPDATE_DEFAULT = 3;

}
