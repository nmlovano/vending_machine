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
	private BigDecimal currentBalance;
	private List<BigDecimal> acceptedValues = new ArrayList<BigDecimal>();
	BigDecimal currentSelection;
	
	//CTOR****************************************************************************************************
	public VendingMachine() {
		File inputFile = new File("vendingmachine.txt");
		 currentSelection = new BigDecimal("0.00");
		 currentBalance = new BigDecimal("0.00");
		
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
	
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	public BigDecimal getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(BigDecimal currentSelection) {
		this.currentSelection = currentSelection;
	}
	
	
	
	//Methods************************************************************************************************

		public void displayStock() {
			
			Set<String> locationSet = location.keySet();
			
			for(String item : locationSet) {
				
				
				System.out.println(item + " " + location.get(item));
			}
		}

		public void productSelector() {
			
			Scanner userInput = new Scanner(System.in);
			boolean done = false;
			
			System.out.println("Select a product.");
			String selection = userInput.nextLine();
			
			if(!location.containsKey(selection)){
				System.out.println("Invalid selection");
			} else if (location.get(selection).getProductPrice().doubleValue() > getCurrentBalance().doubleValue()) {
				System.out.print("Not enough $. Please make another selection or add more money.");
			}  else if (location.get(selection).getQty() == 0) {
				System.out.println("Item out of stock");
			} else {
				currentBalance = currentBalance.subtract(location.get(selection).getProductPrice());
				System.out.println("Remaining balance: $"+ getCurrentBalance());
				location.get(selection).setQty(location.get(selection).getQty()-1);
				System.out.println("New qty is: " + location.get(selection).getQty());
				
				
				
				} 
			
		}
		
		public BigDecimal takeMoney() {
			
			Scanner userInput = new Scanner(System.in);
			boolean done = false;
			
			do {
			
			System.out.println("Your current balance is : " + currentBalance);
			System.out.println("To increase balance, add money in the following Denoimations:");
			System.out.println("1.00, 2.00, 5.00, 10.00");
			System.out.println("When finished select (exit)");
			
			String value = userInput.nextLine();
			
			System.out.println(value);
			
			if (value.contains("exit")) {
				done = true;
			} else if(value.contains("1.00") || value.contains("2.00") || value.contains("5.00") || value.contains("10.00")){
				currentBalance = currentBalance.add(new BigDecimal(value));
				
			} else {
				System.out.println("Invalid input, try again.");
			}
			} while(done == false);
			
			return currentBalance;
		}
		
		public void moneyTransact() {
			
			
			
//			location.get(selection).setQty(location.get(selection).getQty()-1);
//			System.out.println("Product selected is: " + location.get(selection).getProductName());
//			System.out.println("Product price is: " + location.get(selection).getProductPrice());
//			System.out.println("New qty is: " + location.get(selection).getQty());
			
		}
			
			
		}
	


