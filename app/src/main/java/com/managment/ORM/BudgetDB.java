package com.managment.ORM;

import com.managment.data.Budget;

public class BudgetDB {
	
	public static boolean add(Budget budget){
		boolean rowAdded = true;
		try{

			
		}catch (Exception e){
			System.out.println("There was a problem adding data in BudgetDB: "+ e);
			rowAdded = false;
		}
		return rowAdded;
	}
	
	public static boolean remove(Budget budget){
		boolean rowRemoved = true;
		try{
			

			
		}catch (Exception e){
			System.out.println("There was a problem removing data in BudgetDB: "+ e);
			rowRemoved = false;
		}
		return rowRemoved;
	}
	
	public static boolean update(Budget budget){
		boolean rowUpdated = true;
		try{
			

			
		}catch (Exception e){
			System.out.println("There was a problem updating data in BudgetDB: "+ e);
			rowUpdated = false;
		}
		return rowUpdated;
	}
}
