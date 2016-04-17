package com.lohiya;
import java.nio.charset.Charset;

class DemoClass
{
	//final static Object lock = new Object();  
    public  void foo(){
    	synchronized(DemoClass.class){
    	for(int i=0;i<1000;i++)
    	{
    		System.out.println(Thread.currentThread().getName() + " i=" + i);
    	}
    	}
    	
    }
    public  void bar(){
    	synchronized(DemoClass.class){
    	for(int i=0;i<1000;i++)
    	{
    		System.out.println(Thread.currentThread().getName() + " i=" + i);
    	}
    	}
    }
}

public class Test3 {

	public static void tests(long sect){
		System.out.println("sect=="+sect);
	}
	// ExecutorService executorService = Executors.newFixedThreadPool(9);
	public static void main(String[] args) throws Exception {
		
		
		Long sect = null;
		
		if(sect != null){
		tests(sect);
		}
		else{
			tests(0);
		}
		System.out.println("dsfadf");
		
		
	Long l = 1L;
	String sst = l.toString();
	System.out.println(sst);
	
		System.out.println(Charset.defaultCharset());
		
		//"abc".getBytes(Charset.)
		int i=System.in.read();//returns ASCII code of 1st character  
		System.out.println((char)i);//will print the character  
		
		int j=System.in.read();//returns ASCII code of 1st character  
		System.out.println((char)j);//will print the character  
		
		System.out.println(System.in.available());
		
		final DemoClass demoClass = new DemoClass();
		final DemoClass demoClass2 = new DemoClass();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				demoClass.foo();				
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				demoClass2.bar();				
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		

		/*//Lock lock = new 
		System.out.println("Rohit");
		ThreadPool pool = new ThreadPool(5, 10);
		for (int i = 0; i < 10; i++) {

			pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());

				}
			});
		}*/
		
		
	}

}
