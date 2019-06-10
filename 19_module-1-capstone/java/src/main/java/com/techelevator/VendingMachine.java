package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;

public class VendingMachine {
	
	//this should be where we take a product and stock it in the machine

	//Data Members********************************************************************************************

	private Map<String, Product> location = new TreeMap<String, Product>();
	private Map<String, String> sounds = new HashMap<String, String>();
	private BigDecimal currentBalance;
	private List<BigDecimal> acceptedValues = new ArrayList<BigDecimal>();
	private List <Product> purchasedItems = new ArrayList<Product>();
	private BigDecimal currentSelection;
	private BigDecimal initialBalance;
	private Log auditForm = new Log();
	
	//CTOR****************************************************************************************************
	public VendingMachine() {
		File inputFile = new File("vendingmachine.txt");
		 currentSelection = new BigDecimal("0.00");
		 currentBalance = new BigDecimal("0.00");
		 initialBalance = new BigDecimal("0.00");
		 sounds.put("Chip", "Crunch Crunch, Yum!");
		 sounds.put("Candy", "Munch Munch, Yum!");
		 sounds.put("Drink", "Glug Glug, Yum!");
		 sounds.put("Gum", "Chew Chew, Yum!");
		 
		
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
	
	public List<Product> getPurchasedItems() {
		return purchasedItems;
	}

	public void setPurchasedItems(List<Product> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}
	
	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}
	
	
	//Methods***********************************************************************************************


		public void displayStock() {
			
			Set<String> locationSet = location.keySet();
			
			for(String item : locationSet) {
				
				
				System.out.println(item + " " + location.get(item));
			}
		}

		public void productSelector() {
			
			Scanner userInput = new Scanner(System.in);
			boolean done = false;
			initialBalance = getCurrentBalance();
			
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
				purchasedItems.add(location.get(selection));
				System.out.println("New qty in " + selection + " is " + location.get(selection).getQty());
				System.out.println("Dispensing " + location.get(selection).getProductName() + " from " + selection);
				
				auditForm.logMaker(location.get(selection).getProductName() + " " + selection  , getInitialBalance(), getCurrentBalance());
				
				
				
				} 
			
		}
		
		public BigDecimal takeMoney() {
			
			Scanner userInput = new Scanner(System.in);
			boolean done = false;
			
			do {
			initialBalance = getCurrentBalance();
			System.out.println("************************************************************");
			System.out.println("Your current balance is : " + currentBalance);
			System.out.println("To increase balance, add money in the following Denoimations:");
			System.out.println("1.00, 2.00, 5.00, 10.00");
			System.out.println("When finished select (exit)");
			
			String value = userInput.nextLine();
			

			
			if (value.contains("exit")) {
				done = true;
			} else if(value.contains("1.00") || value.contains("2.00") || value.contains("5.00") || value.contains("10.00")){
				currentBalance = currentBalance.add(new BigDecimal(value));
				auditForm.logMaker("FEED MONEY", getInitialBalance(), getCurrentBalance());
			} else {
				System.out.println("Invalid input, try again.");
			}
			} while(done == false);
			
			return currentBalance;
		}
		
		public void moneyTransactComplete() throws FileNotFoundException, IOException {
			
			BigDecimal initialBalance = getCurrentBalance();
			int quarters, dimes, nickels;
			initialBalance = getCurrentBalance();
			
			//change = getCurrentBalance().doubleValue();
			
			System.out.println("Current balance is: " + getCurrentBalance() + "\n");
			System.out.println ("Here is your change:");
			double coins = getCurrentBalance().doubleValue();
			
			quarters = (int) (getCurrentBalance().doubleValue()/.25 + .001);
			coins %= .25; 
			dimes = (int) (coins/.10 + .001);
			coins %= .10; 
			nickels = (int) (coins/.05 + .001);
			coins %= .05; 
			
			System.out.println ("Quarters = " + quarters + "\nDimes = " + dimes + "\nNickels = " + nickels);
			setCurrentBalance(new BigDecimal ("0.00"));
			auditForm.logMaker("MAKE CHANGE", initialBalance, getCurrentBalance());
			auditForm.printToFile();
			
		}
		
		public void makeSound() {
			for (Product item : purchasedItems) {
				System.out.println(sounds.get(item.getProductType()));
			}
			purchasedItems.clear();
					
		}
		
		public void salesReport() throws IOException {
			Map<String, Integer> salesMap = new TreeMap <String, Integer>();
			Set<String> salesKey = location.keySet();
			BigDecimal totalSales = new BigDecimal("0.00");
			
			for(String loopValue : salesKey) {
				
				String tempName = location.get(loopValue).getProductName();
				BigDecimal tempPrice = location.get(loopValue).getProductPrice();
				int tempQty = location.get(loopValue).getQty();
				int soldQty = 5-tempQty;
				Date aDate = new Date();
				String strDateFormat = "MM-dd-yy HH-mm-ss";
				DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
				String formattedDate = dateFormat.format(aDate);

				
				if (soldQty > 0) {
					salesMap.put(tempName, soldQty);
					for(int i = 0; i < soldQty; i++) {
					totalSales = totalSales.add(tempPrice);
					}
				}
				
				File outputFile = new File("SalesReport " + formattedDate + ".txt");
				outputFile.createNewFile();
				try(PrintWriter writer = new PrintWriter(outputFile)){
				
					Set<String> reportKey = salesMap.keySet();
					
				for (String transaction : reportKey) {
					writer.println(transaction + "|" + salesMap.get(transaction));
				} 
				writer.println("**TOTAL SALES** $" + totalSales);
				writer.close();
				
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("file not found");
					e.printStackTrace();
				}
				
			}
		}
			
			
		}
	


