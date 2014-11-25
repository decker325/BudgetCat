package com.managment.ORM;



import android.support.v4.util.ArrayMap;

import com.managment.data.Account;
import com.managment.dataFile.BCatDOMParsing;

import java.util.Map;

import com.managment.data.Category;

public class CategoryDB {

    private static final String fileName = "Accounts.xml";
    private static BCatDOMParsing parser = new BCatDOMParsing(fileName);
    private static Map<String, Account> accounts = new ArrayMap<String, Account>();
	public static boolean add(Category category){
		boolean rowAdded = true;
		try{
			

			
		}catch (Exception e){
			System.out.println("There was a problem adding data in categoryDB: "+ e);
			rowAdded = false;
		}
		return rowAdded;
	}
	
	public static boolean remove(Category category){
		boolean rowRemoved = true;
		try{
			

			
		}catch (Exception e){
			System.out.println("There was a problem removing data in categoryDB: "+ e);
			rowRemoved = false;
		}
		return rowRemoved;
	}
	
	public static boolean update(Category category){
		boolean rowUpdated = true;
		try{
			

			
		}catch (Exception e){
			System.out.println("There was a problem updating data in categoryDB: "+ e);
			rowUpdated = false;
		}
		return rowUpdated;
	}
}
