package com.lohiya;

class Runner extends Thread {

	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
	}

	@Override
	public void run() {
		System.out.println("runing ");
		throw new RuntimeException();
	}
	
/*	public String getDescription(Object obj) {
		return obj.toString();
	}

	public String getDescription(String obj) {
		return obj;
	}

	public void getDescription(String obj) {
		return obj;
	}
*/
}

public class TestCall {

	public static void main(String[] args) {
		
		
		//Long.parseUnsignedLong("184614912", radix)
		
		
		String hexNumber = "0f";
				int decimal = Integer.parseInt(hexNumber, 16);
				System.out.println("Hex value is " + decimal);
		
		String str = "12345678";
		
	System.out.println(str.substring(0, 2));
	System.out.println(str.substring(2,4));
	System.out.println(str.substring(4, 8));
		
		

		Runner runner = new Runner();
		
		/*runner.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("uncaughtException");
			}

		});*/
		
		//runner.setDefaultUncaughtExceptionHandler();
		

		runner.start();
	}

}
