package com.managment.ORM;



import android.support.v4.util.ArrayMap;

import java.util.Map;

import com.managment.data.Account;
import com.managment.dataFile.BCatDOMParsing;

public class AccountDB {

    private static final String fileName = "Accounts.xml";
    private static BCatDOMParsing parser = new BCatDOMParsing(fileName);
    private static Map<String, Account> accounts = new ArrayMap<String, Account>();
	
	public static boolean add(Account account){
		boolean rowAdded = true;
		try{

            if(accounts.containsKey(account.accountName)) {
                rowAdded = false;
            }else {
                accounts.put(account.accountName, account);

            }
			
		}catch (Exception e){
			System.out.println("There was a problem adding data in AccountDB: "+ e);
			rowAdded = false;
		}
		return rowAdded;
	}
	
	public static boolean remove(Account account){
		boolean rowRemoved = true;
		try{
			

			
		}catch (Exception e){

		}
		return rowRemoved;
	}
	
	public static boolean update(Account account){
		boolean rowUpdated = true;
		try{

			
		}catch (Exception e){
			System.out.println("There was a problem updating data in AccountDB: "+ e);
			rowUpdated = false;
		}
		return rowUpdated;
	}
}
