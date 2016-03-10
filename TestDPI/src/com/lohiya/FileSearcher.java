package com.lohiya;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSearcher {

	private static List<String> results = new ArrayList<String>();
	private static List<String> matched_results = new ArrayList<String>();

	public static void main(String[] args) {

		String startingFolder = "D:\\Spring4RestWS";

		listfiles(new File(startingFolder));

		System.out.println("All Files\n---------------------------------------------------------\n");
		for (String result : results) {
			System.out.println(result);
		}
		
		System.out.println("Enter pattern file name to search : ");
		Scanner scanner = new Scanner(System.in);
		String patternString = scanner.nextLine();
		
		System.out.println("patternString==>"+patternString);
		
		for (String result : results) {
			
			//String[] ss =  result.split("\\");
			
			//if(result.matches("(.*)classpath(.*)"))
			if(result.matches("(.*)"+patternString+"(.*)"))
				matched_results.add(result);
		}
		
		System.out.println("Matched Files\n---------------------------------------------------------\n");
		for (String matched_result : matched_results) {
			System.out.println(matched_result);
		}
		
		
		
		
		
	}

	public static void listfiles(File file) {

		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				listfiles(f);
			}
		} else {
			results.add(file.getAbsolutePath());
		}
	}
}
