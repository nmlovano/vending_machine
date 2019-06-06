package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

public class VendingMachine {
	
	//this should be where we take a product and stock it in the machine

	//Data Members********************************************************************************************
	String[][] itemArray = new String[4][4];
	
	/*
	 * 
	 *  0    1    2    3
	 *  
	 * [a1] [a2] [a3] [a4],	0   
	 * [b1] [b2] [b3] [b4],	1
	 * [c1] [c2] [c3] [c4],	2
	 * [d1] [d2] [d3] [d4],	3
	 */
	
	private Map<String, Product> location = new HashMap<String,Product>();
	//private List<Product> stock = new ArrayList<Product>();
	
	
	//CTOR****************************************************************************************************
	public VendingMachine() {
		File inputFile = new File("vendingmachine9.txt");
		
		try(Scanner fileScanner  = new Scanner(inputFile)) {
			if(inputFile.exists()) {
			
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] lineArray = line.split(","); // 0-location, 1-productName, 2-productPrice, 3-productType
				
				location.put(lineArray[0], new Product(lineArray[1], new BigDecimal(lineArray[2]), lineArray[3]));
				}
			}
			else {
				System.out.println("Error");
				System.exit(0);
			}
				
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("file not found");
		e.printStackTrace();
	}
	}
	
	//Get/Set*************************************************************************************************
	
	
	
	//Methods*************************************************************************************************

		public void displayStock() {
			
			Set<String> locationSet = location.keySet();
			
			for(String item : locationSet) {
				
				
				System.out.println(item + " " + location.get(item));
			}
		}
				
				
			
			
		}
	


