package com.managment.data;
// transactions
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class TransactionDB {

    private static final String fileName = "Transactions.xml";
    private static BcatDOMParsingTest parser = new BcatDOMParsingTest(fileName);
    private static Map<String, Transaction> transactions = new HashMap<String, Transaction>();

    public static Set<String> getTransactionKeys(){
        return transactions.keySet();
    }

    public static boolean add(Transaction transaction){
        boolean rowAdded = true;
        try{

            if(transactions.containsKey(transaction.getTransactionID())) {
                rowAdded = false;
            }else {
                transactions.put(transaction.getTransactionID(), transaction);
                NodeList nodes = (parser.FileRootDocumentGet()).getChildNodes();
                Element element = parser.createParentElement("Transaction", nodes.item(0), "TransactionID", transaction.getTransactionID());
                Node node = parser.addNodeElements("Amount", Double.toString(transaction.amount), element);
                Node node2=parser.addNodeElements("Year", Double.toString(transaction.year), element);
                Node node3=parser.addNodeElements("Long", Double.toString(transaction.locationLong), element);
                Node node4=parser.addNodeElements("Lat", Double.toString(transaction.locationLat), element);
                parser.addNode(node, nodes.item(0));
                parser.addNode(node2, nodes.item(1));
                parser.addNode(node3, nodes.item(2));
                parser.addNode(node4, nodes.item(3));
            }

        }catch (Exception e){
            rowAdded = false;
        }
        return rowAdded;
    }

    //TODO: polish this.
    public static boolean remove(Transaction transaction){
        boolean rowRemoved = true;
        try{
            if(!transactions.containsKey(transaction.getTransactionID())) {
                rowRemoved = false;
            }else {
                transactions.remove(transaction.getTransactionID());
//                NodeList nodes = (parser.FileRootDocumentGet()).getChildNodes();
//                Element element = parser.createParentElement("Transaction", nodes.item(0), "TransactionID", transaction.TranscationID);
//                Node node = parser.addNodeElements("Amount", Double.toString(transaction.amount), element);
//                parser.addNode(node, nodes.item(0));
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


