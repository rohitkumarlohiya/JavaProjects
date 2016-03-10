package com.lohiya;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestMemberOuter1 {
	private int data = 30;

	class Inner {
		void msg() {
			System.out.println("data is " + data);
		}
	}

	void display() {
		Inner in = new Inner();
		in.msg();
	}

	public static void main(String args[])  throws Exception {
		
		
		Object obj = new  Object();
		
		System.out.println(obj.hashCode());
		
		System.out.println("-----------------------------------");
				 
				      List stuff = Arrays.asList(new String[] { "a", "b" });
				      List list = new ArrayList(stuff);
				      list = Collections.unmodifiableList(list);
				      try {
				         list.set(0, "new value");
				      } 
					  catch (UnsupportedOperationException e) {
						  System.out.println("inside catch");
				      }
				      Set set = new HashSet(stuff);
				      set = Collections.unmodifiableSet(set);
				      
				      try {
				    	  set.add("new value");
					      } 
						  catch (UnsupportedOperationException e) {
							  System.out.println("inside set catch");
					      }
				      
				      
				      Map map = new HashMap();
				      map = Collections.unmodifiableMap(map);
				      System.out.println("Collection is read-only now.");
		
		
		/*TestMemberOuter1 obj = new TestMemberOuter1();
		obj.display();*/
	}

	/*public static void main(String args[]) {
		TestMemberOuter1 obj = new TestMemberOuter1();
		TestMemberOuter1.Inner in = obj.new Inner();
		in.msg();
	}*/
}