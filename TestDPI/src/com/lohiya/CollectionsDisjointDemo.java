package com.lohiya;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.Callable;

 
public class CollectionsDisjointDemo {
    public static void main(String[] args) {
    	
    	Queue q;
    	
    	Iterable t;    	
    	//List<? extends Number> list = new ArrayList<Object>();
    	
    	Collection col = null;
    	Stack b;
    	
    	
    	
    	Map mp = null;
    	
    		mp.keySet().iterator();
    		mp.values().iterator();
    		mp.entrySet().iterator();
    	
        Bean a1 = new Bean("A");
        Bean a2 = new Bean("A");
        Bean a3 = new Bean("A");
        
        Bean a4 = new Bean("D");
        Bean a5 = new Bean("E");
        Bean a6 = new Bean("F");
                
        List list1 = new ArrayList<>();
        
        //list1.
        
        list1.add(a1);
        list1.add(a2);
        list1.add(a3);
        
        List<Bean> list2 = new ArrayList<Bean>();
        
        list2.add(a4);
        list2.add(a5);
        list2.add(a6);
        
        
        //list1.iterator()
        
        System.out.println(Collections.frequency(list1,new Bean("A")));
   }
 
}
 
class Bean {
    public String name;
 
    public Bean(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        return name.equals(((Bean)o).name);
    }
    @Override
    public int hashCode() {
        int hash = 13;
        hash = (31 * hash) + (null == name ? 0 : name.hashCode());
        return hash;
    }
}

class My implements Callable<Integer>
{

	@Override
	public Integer call() throws Exception {
		wait();
		return null;
	}
	
}