package com.managment.data;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Transaction {
    public String accountName;
    private String transcationID;
    public double amount;
    public int category;
    public double locationLat;
    public double locationLong;
    public String locationName;
    public String notes;
    public int year;
    public int month;
    public int day;

    public Transaction(){
        String newKey=Long.toHexString(Double.doubleToLongBits(Math.random()));
        while(TransactionDB.getTransactionKeys().contains(newKey)){
            newKey=Long.toHexString(Double.doubleToLongBits(Math.random()));
        };
        transcationID=newKey;
    }

    public String getTransactionID(){
        return transcationID;
    }

    public boolean add(){
        return TransactionDB.add(this);
    }

    public boolean remove(){
        return TransactionDB.remove(this);
    }

    public boolean update(){
        return TransactionDB.update(this);
    }
}
