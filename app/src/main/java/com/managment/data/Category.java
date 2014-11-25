package com.managment.data;

import com.managment.ORM.CategoryDB;

public class Category {

	public int categoryID;
	public String categoryName;
	
	public Category(){
		
	}
	
	public boolean add(){
		return CategoryDB.add(this);
	}
	
	public boolean remove(){
		return CategoryDB.remove(this);
	}
	
	public boolean update(){
		return CategoryDB.update(this);
	}
}
