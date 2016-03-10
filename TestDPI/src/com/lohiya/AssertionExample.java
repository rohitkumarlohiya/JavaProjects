package com.lohiya;

import java.util.Scanner;


class s implements Runnable 
{ 
    int x, y; 
    public void run() 
    { 
        for(int i = 0; i < 1000; i++) 
            synchronized(this) 
            { 
                x = 12; 
                y = 12; 
            } 
        System.out.print(x + " " + y + " "); 
    } 
    public static void main(String args[]) 
    { 
        s run = new s(); 
        Thread t1 = new Thread(run); 
        Thread t2 = new Thread(run); 
        t1.start(); 
        t2.start(); 
    } 
}
/*
class MyThread extends Thread 
{ 
    MyThread() {} 
    MyThread(Runnable r) {super(r); } 
    public void run() 
    { 
        System.out.print("Inside Thread ");
    } 
} 
class MyRunnable implements Runnable 
{ 
    public void run() 
    { 
        System.out.print(" Inside Runnable"); 
    } 
} 
public class AssertionExample 
{  
    public static void main(String[] args) 
    { 
        new MyThread().start(); 
        new MyThread(new MyRunnable()).start(); 
    } 
}
*/
/*public class AssertionExample {
	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ur age ");

		int value = scanner.nextInt();
		assert value >= 18 : " Not valid";

		System.out.println("value is " + value);
		
		try{
			
		}
		finally{
			
		}
	}

}*/
