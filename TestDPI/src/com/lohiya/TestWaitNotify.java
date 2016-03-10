/*package com.lohiya;

import java.io.File;
import java.lang.reflect.ReflectPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ProductQueue{
	
	private List<Integer> list;
	
	ProductQueue(List<Integer> list){
		this.list = list;
	}
	
	public void produce()
	{
		
	}
	
	public void consume()
	{
		
	}
	
}


class Produce extends Thread {

	private List<Integer> list;

	public Produce(List<Integer> list) {
		this.list = list;
	}

	public void run() {

		for (int i = 0; i < 30; i++) {
			synchronized (list) {

				if (list.size() == 10) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {

					System.out.println("Adding elment " + i);
					list.add(new Random().nextInt(100));
					notify();
				}
			}
		}
	}
}

class Consume extends Thread {

	private List<Integer> list;

	public Consume(List<Integer> list) {
		this.list = list;
	}

	public void run() {

		for (int i = 0; i < 30; i++) {
			synchronized (list) {
				if (list.size() == 0) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {

					System.out.println("consume value = " + list.remove(0));
					notify();
				}
			}
		}
	}
}

public class TestWaitNotify {
	
	public static void main(String[] args) {

String a ="hello";
String b ="hello";

System.out.println(a==b);

File file;
ReflectPermission

		List<Integer> list = new ArrayList<Integer>();
		
		list.add(1);
		list.add(2);
		
		System.out.println(list);
		
		list.add(1,5);
		System.out.println(list);
		
		Produce produce = new Produce(list);
		Consume consume = new Consume(list);

		produce.start();
		consume.start();

		System.out.println("Completed");
	}

}
*/