package com.example.lambda;

public interface MyInterface {
	
	default public void doSomething()
	{
		System.out.println("Inside doSomthing method");
	}

	/*default public void doSomething1()
	{
		System.out.println("Inside doSomthing2 method");
	}*/
}
