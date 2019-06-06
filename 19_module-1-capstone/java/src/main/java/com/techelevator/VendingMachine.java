package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;

public class VendingMachine {
	
	//this should be where we take a product and stock it in the machine

	//Data Members********************************************************************************************

	private Map<String, Product> location = new TreeMap<String,Product>();
	
	//CTOR****************************************************************************************************
	public VendingMachine() {
		File inputFile = new File("vendingmachine.txt");
		
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
				
		public void productSelector() {
			Scanner userInput = new Scanner(System.in);
			
			System.out.println("Select a product.");
			String value = userInput.nextLine();
			
			if(!location.containsKey(value)){
				System.out.println("Invalid selection");
			} else if(location.containsKey(value) && location.get(value).getQty() > 0) {
				location.get(value).setQty(location.get(value).getQty()-1);
				System.out.println("Product selected is: " + location.get(value).getProductName());
				System.out.println("Product price is: " + location.get(value).getProductPrice());
				System.out.println("New qty is: " + location.get(value).getQty());
				} else if (location.get(value).getQty() == 0) {
					System.out.println("Item out of stock");
				} 
			
		}
			
			
		}
	


