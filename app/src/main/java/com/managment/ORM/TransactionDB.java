package com.managment.ORM;

import android.support.v4.util.ArrayMap;

import com.managment.data.Account;
import com.managment.data.Transaction;
import com.managment.dataFile.BCatDOMParsing;

import java.util.Map;

public class TransactionDB {

    private static final String fileName = "Transactions.xml";
    private static BCatDOMParsing parser = new BCatDOMParsing(fileName);
    private static Map<String, Account> accounts = new ArrayMap<String, Account>();

	public static boolean add(Transaction transaction){
		boolean rowAdded = true;
		try{
			

			
		}catch (Exception e){
			System.out.println("There was a problem adding data in transactionDB: "+ e);
			rowAdded = false;
		}
		return rowAdded;
	}
	
	public static boolean remove(Transaction transaction){
		boolean rowRemoved = true;
		try{
			

			
		}catch (Exception e){
			System.out.println("There was a problem removing data in transactionDB: "+ e);
			rowRemoved = false;
		}
		return rowRemoved;
	}
	
	public static boolean update(Transaction transaction){
		boolean rowUpdated = true;
		try{

			
		}catch (Exception e){
			System.out.println("There was a problem updating data in transactionDB: "+ e);
			rowUpdated = false;
		}
		return rowUpdated;
	}
}
