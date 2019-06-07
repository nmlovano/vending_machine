package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Log {

	//DataMembers**********************************************************************************************************

	private String action;
	private String startBalance;
	private String endBalance;
	private List<String> logList = new ArrayList<String>();
	
	//CTOR***************************************************************************************************************
	public Log() {

	}
	
	public Log(String action, String startBalance, String endBalance){
		this.action = action;
		this.startBalance = startBalance;
		this.endBalance = endBalance;
	}
	
	//Get/Set************************************************************************************************************

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(String startBalance) {
		this.startBalance = startBalance;
	}

	public String getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(String endBalance) {
		this.endBalance = endBalance;
	}

	public List<String> getLogList() {
		return logList;
	}

	public void setLogList(List<String> logList) {
		this.logList = logList;
	}
	
	//Methods*************************************************************************************************************
	
	public void logMaker(String action, BigDecimal startBalance, BigDecimal endBalance) {

		Date aDate = new Date();
		String strDateFormat = "MM-dd-yy HH-mm-ss";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate = dateFormat.format(aDate);

		String logString = formattedDate + ",$" + startBalance + ",$" + endBalance + "," + action;
		
		logList.add(logString);
	}
	
	public void printToFile() throws FileNotFoundException, IOException{
		
		Date aDate = new Date();
		String strDateFormat = "MM-dd-yy HH-mm-ss";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate = dateFormat.format(aDate);
		

		
		File outputFile = new File("Customer " + formattedDate + ".txt");
		outputFile.createNewFile();
		try(PrintWriter writer = new PrintWriter(outputFile)){
		
		for (String transaction : logList) {
			writer.println(transaction);
			

		} 
		
		writer.close();
		logList.clear();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
			e.printStackTrace();
		}
		
	} 
	
}
