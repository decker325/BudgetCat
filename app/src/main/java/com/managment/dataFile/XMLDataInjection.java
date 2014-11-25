package com.managment.dataFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.managment.data.Account;
import com.managment.data.Budget;
import com.managment.data.Category;
import com.managment.data.Transaction;

import com.managment.ORM.*;

public class XMLDataInjection {

	public static boolean injectData(List<HashMap<String,String>> maps){
		boolean dataInjected = true;
		try{
			for(int i = 0; i < maps.size(); i++){
			HashMap<String,String> map = maps.get(i);
			String nodeName = map.get("Node_Name");
			switch(nodeName.charAt(0)){
			case 'B':
			{
				budgetInjectData(map);
			}break;
			case 'A':
			{
					accountInjectData(map);

			}break;
			case 'C':
			{
				categoryInjectData(map);
			}break;
			case 'T':{
				transactionInjectData(map);
			}break;
			}
			}
		}catch (Exception e){
			System.out.println("There was a problem injecting xml data: " + e);
			dataInjected = false;
		}
		
		return dataInjected;
	}
	
	public static boolean transactionInjectData(Map<String,String> map){
		Boolean added = false;
		Transaction transaction = new Transaction();
		try{
			transaction.transactionID = Integer.parseInt(map.get("Transaction_ID"));
			transaction.accountID = Integer.parseInt(map.get("Account_ID"));
			transaction.amount = Double.parseDouble(map.get("Amount"));
			Date date = new SimpleDateFormat("yyyy-dd-mm").parse(map.get("Date_Of_Transaction"));
			transaction.date = new java.sql.Date(date.getTime());
			transaction.categoryID = Integer.parseInt(map.get("Category_ID"));
			transaction.location = map.get("Location");
			transaction.locationName = map.get("Location_Name");
			transaction.notes = map.get("Notes");
			added = transaction.add();
		}catch (Exception e){
			System.out.println("There was a problem injecting xml data in transaction: "+ e);
		}
		return added;
	}

	
	public static boolean categoryInjectData(Map<String,String> map){
		Boolean added = false;
		Category category = new Category();
		try{
			category.categoryID = Integer.parseInt(map.get("Category_ID"));
			category.categoryName = map.get("Category_Name");
			added = category.add();
		}catch (Exception e){
			System.out.println("There was a problem injecting xml Category: "+ e);
		}
		return added;
	}
	
	public static boolean accountInjectData(Map<String, String> map){
		Boolean added = false;
		Account account = new Account();
		try{
			account.accountName = map.get("Account_Name");
			account.balance = Double.parseDouble(map.get("Balance"));
			account.balanceGoal = Double.parseDouble(map.get("Balance_Goal"));;
			account.interestAmount = Double.parseDouble(map.get("Interest_Amount"));;
			account.notes = map.get("Notes");
			
			added = account.add();
		}catch (Exception e){
			System.out.println("There was a problem injecting xml data in account "+ e);
		}
		return added;
	}
	

	public static boolean budgetInjectData(Map<String, String> map){
		boolean dataInjected = true;
		Budget budget = new Budget();
		try{
			budget.budgetName = map.get("Budget_Name");
			budget.userID = Integer.parseInt(map.get("User_ID"));
			Date date = new SimpleDateFormat("yyyy-dd-mm").parse(map.get("Budget_Date"));
			budget.date = new java.sql.Date(date.getTime());
			budget.budgetFileName = map.get("Budget_File_Name");
						
			budget.add();
		}catch (Exception e){
			System.out.println("There was a problem injecting xml data in Budget: "+ e);
			dataInjected = false;
		}
		return dataInjected;
	}
}
