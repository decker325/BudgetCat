package com.managment.data;

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

            if(transactions.containsKey(transaction.TranscationID)) {
                rowAdded = false;
            }else {
                transactions.put(transaction.TranscationID, transaction);
                NodeList nodes = (parser.FileRootDocumentGet()).getChildNodes();
                Element element = parser.createParentElement("Transaction", nodes.item(0), "TransactionID", transaction.TranscationID);
                Node node = parser.addNodeElements("Amount", Double.toString(transaction.amount), element);
                parser.addNode(node, nodes.item(0));
            }

        }catch (Exception e){
            rowAdded = false;
        }
        return rowAdded;
    }

    public static boolean remove(Transaction transaction){
        boolean rowRemoved = true;
        try{



        }catch (Exception e){

        }
        return rowRemoved;
    }

    public static boolean update(Transaction transaction){
        boolean rowUpdated = true;
        try{


        }catch (Exception e){
            rowUpdated = false;
        }
        return rowUpdated;
    }
}


