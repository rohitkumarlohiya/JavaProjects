package com.lohiya;
import java.util.concurrent.atomic.AtomicInteger;

class MyThread implements Runnable {
	
	volatile static int j;
	
	MyThread(Object lock){
		this.lock = lock;
	}

	AtomicInteger ai = new AtomicInteger(1);

	private volatile String tern = "1";

	private Object lock ;

	@Override
	public void run() {

		j = ai.get();

		while (j  < 10) {

			synchronized (lock) {

				while (!Thread.currentThread().getName().equalsIgnoreCase(getTern())) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			System.out.println("ThreadName = " + Thread.currentThread().getName() + "====> " + j);

			j = ai.incrementAndGet();

			synchronized (lock) {
				setTern(String.valueOf(((Integer.valueOf(tern)) % 3) + 1));
				lock.notifyAll();
			}
		}

	}

	public String getTern() {
		return tern;
	}

	public void setTern(String tern) {
		this.tern = tern;
	}

}

public class SequenceThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		int numberOfThread = 5;
		// int number = 10;

		/*
		 * ExecutorService executorService =
		 * Executors.newFixedThreadPool(numberOfThread); for(int i=1;i<=5;i++){
		 * executorService.submit(new MyThread()); }
		 * 
		 * executorService.shutdown(); executorService.awaitTermination(1,
		 * TimeUnit.DAYS);
		 */
		
		 Object lock = new Object();
		 
		 MyThread myThread =  new MyThread(lock);

		Thread t1 = new Thread(myThread, "1");
		Thread t2 = new Thread(myThread, "2");
		Thread t3 = new Thread(myThread, "3");

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();

	}

}
