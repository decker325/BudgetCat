package com.managment.data;
// transactions
import android.content.Context;
import android.util.Log;
//import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class TransactionDB {
    public static File file;
    private static final String fileName = "Transactions.xml";


    public static BcatDOMParsingTest parser;
    private static Map<String, Transaction> transactions = new HashMap<String, Transaction>();

    public static void getSessionData(List<HashMap<String, String>> maps){
        for(int i = 0; i < maps.size(); i++) {
            HashMap<String, String> map = maps.get(i);
            Transaction transaction = new Transaction();
            transaction.transcationID = map.get("TransactionID");
            transaction.amount = Double.parseDouble(map.get("Amount"));
            transaction.locationLat = Double.parseDouble(map.get("Lat"));
            transaction.locationLong = Double.parseDouble(map.get("Long"));
            transaction.year = Integer.parseInt(map.get("Year"));
            transaction.month = map.get("Month")==null?1:Integer.parseInt(map.get("Month"));
            transaction.day = map.get("Day")==null?1:Integer.parseInt(map.get("Day"));
            transactions.put(transaction.transcationID, transaction);
        }


    }

    public static Set<String> getTransactionKeys(){
        return transactions.keySet();
    }

    public static boolean add(Transaction transaction){
        boolean rowAdded = true;
        try{

            if(transactions.containsKey(transaction.getTransactionID())) {
                rowAdded = false;
            }else {
                parser = new BcatDOMParsingTest(file);
                transactions.put(transaction.getTransactionID(), transaction);
                Node parent = parser.FileRootDocumentGet();
                NodeList nodes = (parent).getChildNodes();
                Element element = parser.createParentElement("Transaction", nodes.item(0), "TransactionID", transaction.transcationID);
                Node node = parser.addNodeElements("TransactionID", transaction.transcationID, element);
                Node node5 = parser.addNodeElements("Amount", Double.toString(transaction.amount), element);
                Node node2=parser.addNodeElements("Year", Integer.toString(transaction.year), element);
                Node node3=parser.addNodeElements("Long", Double.toString(transaction.locationLong), element);
                Node node4=parser.addNodeElements("Lat", Double.toString(transaction.locationLat), element);
                Node node6=parser.addNodeElements("Month", Integer.toString(transaction.month), element);
                Node node7= parser.addNodeElements("Day", Integer.toString(transaction.day), element);


                parser.addNode(element, nodes.item(0));



            }

        }catch (Exception e){
            rowAdded = false;
        }
        return rowAdded;
    }

    //TODO: polish this.
    public static boolean remove(Transaction transaction){
        boolean rowRemoved = true;
        parser = new BcatDOMParsingTest(file);

        Log.e("com.management.finance.budgetcat.TDB.remove", file.getAbsoluteFile().toString());
        try{
            if(!transactions.containsKey(transaction.getTransactionID())) {
                rowRemoved = false;

            }else {

                PrintWriter writer = new PrintWriter(file);
                writer.print("");
                writer.close();
                parser =new BcatDOMParsingTest(file);
                BufferedWriter writer2 = new BufferedWriter(new FileWriter(file, true /*append*/));
                writer2.write("<?xml version=\"1.0\"?>\n" +
                        "<BudgetCat>\n" +
                        "\n" +
                        "\n" +
                        "</BudgetCat>");
                writer2.close();


                transactions.remove(transaction.getTransactionID());

                for (String x: transactions.keySet()){
                    transaction=transactions.get(x);
                    transactions.put(transaction.getTransactionID(), transaction);
                    Node parent = parser.FileRootDocumentGet();
                    NodeList nodes = (parent).getChildNodes();
                    Element element = parser.createParentElement("Transaction", nodes.item(0), "TransactionID", transaction.transcationID);
                    Node node = parser.addNodeElements("TransactionID", transaction.transcationID, element);
                    Node node5 = parser.addNodeElements("Amount", Double.toString(transaction.amount), element);
                    Node node2=parser.addNodeElements("Year", Integer.toString(transaction.year), element);
                    Node node3=parser.addNodeElements("Long", Double.toString(transaction.locationLong), element);
                    Node node4=parser.addNodeElements("Lat", Double.toString(transaction.locationLat), element);
                    Node node6=parser.addNodeElements("Month", Integer.toString(transaction.month), element);
                    Node node7= parser.addNodeElements("Day", Integer.toString(transaction.day), element);


                    parser.addNode(element, nodes.item(0));
                }

            }


        }catch (Exception e){

        }

        return rowRemoved;
    }

    public static boolean update(Transaction transaction){
        boolean rowUpdated = true;
        try{
            transactions.remove(transaction.getTransactionID());
            transactions.put(transaction.getTransactionID(), transaction);

        }catch (Exception e){
            rowUpdated = false;
        }
        return rowUpdated;
    }

//@required key contained in keyset.

    public static Transaction get(String key){

        return transactions.get(key);
    }
}


