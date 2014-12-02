package com.managment.data;


import java.util.Date;

public class Transaction {
    public String accountName;
    public String TranscationID;
    public double amount;
    public String date;
    public int category;
    public double locationLat;
    public double locationLong;
    public String locationName;
    public String notes;

    public Transaction(){

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
