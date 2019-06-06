package com.techelevator;

import java.math.BigDecimal;

public class Buy {

	//Data Members*****************************
	private BigDecimal currentBalance;
	
	
	//CTOR*************************************
	public Buy() {
		currentBalance = new BigDecimal ("0.00");
	}
	
	//get/set**********************************
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	//Methods**********************************
	public BigDecimal takeMoney() {
		
		System.out.println("");
		
		return currentBalance;
	}


	
}
