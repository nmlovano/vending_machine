package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Log {

	//DataMembers**********************************************************************************************************
	private String date;
	private String time;
	private String action;
	private String startBalance;
	private String endBalance;
	private List<String> logList = new ArrayList<String>();
	
	//CTOR***************************************************************************************************************
	public Log() {

	}
	
	public Log(String date, String time, String action, String startBalance, String endBalance){
		this.date = date;
		this.time = time;
		this.action = action;
		this.startBalance = startBalance;
		this.endBalance = endBalance;
	}
	
	//Get/Set************************************************************************************************************

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

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
		String strDateFormat = "dd/MM/yy HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate = dateFormat.format(aDate);

		String logString = formattedDate + " " + action + " " + startBalance + " " + endBalance;
		
		logList.add(logString);
		
		for(String something : logList) {
			System.out.println(something);
		}
		
	}

	public void printToFile() {
		//String uniqueName =  
	}
	
}
