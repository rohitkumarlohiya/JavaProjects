package com.lohiya;

public class CandidateCode 
{ 
	static int f=0;
	static String myStr="";
    public static String sequences(String input1)
    {
    	if(f == 0)
    	{
    		//Select sequence A
    		f = 1;
    		//pass this input in M1 machine
    		
    		String[] a1 = input1.split(",");
    		String[] a2 = new String[a1.length-1];
    		
    		for(int i=0;i<a1.length-1;i++)
    		{
    			a2[i] =String.valueOf((Integer.parseInt(a1[i+1])-Integer.parseInt(a1[i])));
    		}
    		
    		String str = ""; 
    		for(int i=0;i<a2.length;i++)
    		{
    			if(i < a2.length-1){
    			str = str + a2[i] +",";
    			}else
    			{
    				str = str + a2[i];
    			}
    		}
    		
    		
    		//check if output is of length 1
    		
    		if(a2.length == 1)
    		{
    			f =2;
    			return a2[0];
    		}
    		else
    		{
    			myStr = str;
    			return sequences(str);
    		}
    		
    		
    	}
    	else{
    		//select sequence B
    		
    		String[] a1 = myStr.split(",");
    		String[] a2 = new String[a1.length-1];
    		
    		for(int i=0;i<a1.length-1;i++)
    		{
    			a2[i] =String.valueOf((Integer.parseInt(a1[i+1])-Integer.parseInt(a1[i])));
    		}
    		
    		String str = ""; 
    		for(int i=0;i<a2.length;i++)
    		{
    			if(i < a2.length-1){
    			str = str + a2[i] +",";
    			}else
    			{
    				str = str + a2[i];
    			}
    		}
    		
    		
    		//check if output is of length 1
    		
    		if(a2.length == 1)
    		{
    			f =2;
    			return a2[0];
    		}
    		else
    		{
    			myStr = str;
    			return sequences(str);
    		}
    	}
    }
    
    public static void main(String[] args) {
		String input = "1,5,9,2,3,5,6";
		
		System.out.println(sequences(input));
	}
}