package com.example.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.sun.istack.internal.NotNull;

public class MyLamdaTest implements MyInterface {

	
	public static int testlist(@NotNull List<Integer> integerList)
	{
		return integerList.get(0);
	}
	
	
	public static void main(String[] args) {
		
		
		List<Integer> myList = new ArrayList<Integer>();
		
		//System.out.println(testlist(myList));
		
		//myList.add(1);
		
		System.out.println(testlist(myList));
		
		new MyLamdaTest().doSomething();
		
		
		
		
		

		List<Person> pl = Person.createShortList();

		System.out.println("normal way");
		//normal way
		for (Person p : pl) {
			System.out.println(p.getGivenName());
		}
		
		//lamda way
		System.out.println("lamda way");
		pl.stream().forEach(p -> {System.out.println(p.getGivenName());});
		
		System.out.println("sort by first name");
		
		Comparator<Person> byFirstName = (p, o) ->p.getGivenName().compareTo(o.getGivenName());
		
		pl.sort(byFirstName);
		
		pl.forEach(p -> {System.out.println(p.getGivenName());});
		
		
		//forEach(name -> {System.out.println(name);});
		//forEach(p -> {System.out.println(p.getGivenName());});
	}

	@Override
	public void doSomething() {
		System.out.println("overridden method");		
	}
	
	
	
	/*@Override
	public void doSomething()
	{
		System.out.println("Inside doSomthing method in Main Class");
	}*/

}
