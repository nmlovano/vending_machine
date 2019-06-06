package com.techelevator;

import java.math.BigDecimal;

public class Product {
	
	//Data Members********************************************************************************************
	
	private String productType;
	private String productName;
	private BigDecimal productPrice;
	private int qty;
	
	//CTOR****************************************************************************************************
	public Product() {
		productType = "Unassigned";
		productName = "Unidentified";
		productPrice = new BigDecimal("0.00");
	}
	
	public Product(String productName, BigDecimal productPrice, String productType) {
		this.productType = productType;
		this.productName = productName;
		this.productPrice = productPrice;
		qty = 5;
		
	}

	//Get/Set*************************************************************************************************
	
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return productName + " $" + productPrice + " (" + qty + ")";
	}
	
	
	
	//Methods*************************************************************************************************
	
	//this is where we take an item and pass it to the vending machine class
	

}
