package com.managment.data;
// transactions
import android.content.Context;
import android.util.Log;

import java.io.File;
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

    public static BcatDOMParsingTest parser;
    private static Map<String, Transaction> transactions = new HashMap<String, Transaction>();

    public static void getSessionData(List<HashMap<String, String>> maps){
        Log.e("com.management.finance.budgetcat.TDB.GetsessionData", maps.toString());
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
//            Log.e("com.management.finance.budgetcat.TDB.tranLlist",  map.get("Transaction_ID")+i*10);
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
                Log.e("com.management.finance.budgetcat.TDB.add", file.getAbsoluteFile().toString());

//
//                transactions.put(transaction.getTransactionID(), transaction);
//                NodeList nodes = (parser.FileRootDocumentGet()).getChildNodes();
//                Log.e("com.management.finance.budgetcat.TDB.add", nodes.toString());
//                Element element = parser.createParentElement("Transaction", nodes.item(0), "TransactionID", transaction.transcationID);
//                Node node = parser.addNodeElements("TransactionID", transaction.transcationID, element);
//                Node node5 = parser.addNodeElements("Amount", Double.toString(transaction.amount), element);
//                Node node2=parser.addNodeElements("Year", Integer.toString(transaction.year), element);
//                Node node3=parser.addNodeElements("Long", Double.toString(transaction.locationLong), element);
//                Node node4=parser.addNodeElements("Lat", Double.toString(transaction.locationLat), element);
//                parser.addNode(node, nodes.item(0));
//                parser.addNode(node2, nodes.item(1));
//                parser.addNode(node3, nodes.item(2));
//                parser.addNode(node4, nodes.item(3));
//                parser.addNode(node5, nodes.item(4));

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
                Log.e("com.management.finance.budgetcat.TDB.add.month", Integer.toString(transaction.month));
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
                transactions.remove(transaction.getTransactionID());
                NodeList nodes = (parser.FileRootDocumentGet()).getChildNodes();
                // Node[] theseNodes;
                //BcatDOMParsingTest.NodeSelectManyGivenAttributeValue(,"TransactionID", transaction.transcationID);

//            	Element element = parser.createParentElement("Transaction", nodes.item(0), "TransactionID", transaction.TranscationID);
//            	Node node = parser.addNodeElements("Amount", Double.toString(transaction.amount), element);
//            	parser.addNode(node, nodes.item(0));
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


