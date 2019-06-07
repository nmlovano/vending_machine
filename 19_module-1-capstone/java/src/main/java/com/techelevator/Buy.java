//package com.techelevator;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Buy {
//
//	//Data Members*****************************
//	private BigDecimal currentBalance;
//	private List<BigDecimal> acceptedValues = new ArrayList<BigDecimal>();
//	
//	//CTOR*************************************
//	public Buy() {
//		currentBalance = new BigDecimal ("0.00");
//		acceptedValues.add(new BigDecimal("1.00"));
//		acceptedValues.add(new BigDecimal("2.00"));
//		acceptedValues.add(new BigDecimal("5.00"));
//		acceptedValues.add(new BigDecimal("10.00"));
//	}
//	
//	//get/set**********************************
//	public BigDecimal getCurrentBalance() {
//		return currentBalance;
//	}
//
//	public void setCurrentBalance(BigDecimal currentBalance) {
//		this.currentBalance = currentBalance;
//	}
//	
//	//Methods**********************************
//	public BigDecimal takeMoney() {
//		
//		Scanner userInput = new Scanner(System.in);
//		boolean done = false;
//		
//		do {
//		
//		System.out.println("Your current balance is : " + currentBalance);
//		System.out.println("To increase balance, add money in the following Denoimations:");
//		System.out.println("1.00, 2.00, 5.00, 10.00");
//		System.out.println("When finished select (exit)");
//		
//		String value = userInput.nextLine();
//		
//		System.out.println(value);
//		
//		if (value.contains("exit")) {
//			done = true;
//		} else if(value.contains("1.00") || value.contains("2.00") || value.contains("5.00") || value.contains("10.00")){
//			currentBalance = currentBalance.add(new BigDecimal(value));
//			
//		} else {
//			System.out.println("Invalid input, try again.");
//		}
//		} while(done == false);
//		
//		return currentBalance;
//	}
//	
//
//	
//}
