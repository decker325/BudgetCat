package com.managment.data;

/*
Parts of this code reference and uses methods from DomParsingTest from Rajiv Ramnath distributed enterprise computing course.
 */

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.*;

public class BcatDOMParsingTest {

    private File file;

    public BcatDOMParsingTest(File file) {
        this.file = file;
        Log.e("com.management.finance.budgetcat.Dom.constuctor", file.getAbsoluteFile().toString());


    }


    public List<HashMap<String, String>> getParsedData() {
        List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setValidating(true);

            factory.setNamespaceAware(true);

            Document theDocument = FileRootDocumentGet();


            Node documentNode = DocumentNodeGet(theDocument);

            String nodeName = documentNode.getNodeName();

            //System.out.println("Document node name = " + nodeName);
            NodeList ElementArray = documentNode.getChildNodes();
            for (int i = 0; i < ElementArray.getLength(); i++) {
                NodeList CATEGORYELEMENTS = ElementArray.item(i).getChildNodes();
                String categoryNodeName = ElementArray.item(i).getNodeName();
                if (!categoryNodeName.equalsIgnoreCase("#text")) {
                    //System.out.println("Element Node Name: " + categoryNodeName);
                }
                map = new HashMap<String, String>();

                for (int j = 0; j < CATEGORYELEMENTS.getLength(); j++) {
                    Node currentNode = CATEGORYELEMENTS.item(j);
                    String columnName = currentNode.getNodeName();
                    if (!columnName.equalsIgnoreCase("#text")) {
                        String columnValue = currentNode.getTextContent();
                        map.put(columnName, columnValue);
                        //System.out.println(columnName + " = " + columnValue);
                    }
                }
                if (map.size() > 0) {
                    map.put("Node_Name", categoryNodeName);
                    maps.add(map);
                }
                //System.out.println();
            }

            //System.out.println("Parsing complete");

        } catch (Exception E) {
            System.out.println("Error in parsing: " + E.getMessage());
        }

        return maps;
    }

    //from Rajiv Ramnath
    public Document FileRootDocumentGet()
            throws Exception {
        Document TheDocument = null;
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
//        TheDocument = parser.parse(file.getAbsolutePath());//changed to file
        TheDocument = parser.parse(file);
        Log.e("com.management.finance.budgetcat.Dom.FileRootDocument", file.getAbsoluteFile().toString());
        Log.e("com.management.finance.budgetcat.Dom.FileRootDocument", parser.toString());

        return (TheDocument);
    }

    //from Rajiv Ramnath
    public Node DocumentNodeGet(Document TheDocument) {
        Node theNode = null;
        theNode = TheDocument.getDocumentElement();
        return (theNode);
    }

    //from Rajiv Ramnath
    public static Node[] NodeElementSelectManyGivenName(Node TheNode,
                                                        String TheName) {
        int count = 0;
        Node[] SelectedNodes = null;

        NodeList children = TheNode.getChildNodes();

        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                short nodeType = children.item(i).getNodeType();
                Node oneChild = children.item(i);
                if (nodeType == Node.ELEMENT_NODE) {
                    String nodeName = oneChild.getNodeName();
                    if (nodeName.equals(TheName)) {
                        count++;
                    }
                }
            }

            SelectedNodes = new Node[count];
            count = 0;
            for (int i = 0; i < len; i++) {
                short nodeType = children.item(i).getNodeType();
                Node oneChild = children.item(i);
                if (nodeType == Node.ELEMENT_NODE) {
                    String nodeName = oneChild.getNodeName();
                    if (nodeName.equals(TheName)) {
                        SelectedNodes[count] = oneChild;
                        count++;
                    }
                }
            }

        }

        return (SelectedNodes);
    }

    // from Rajiv Ramnath
    public static String NodeAttributeValueGet(Node TheNode,
                                               String AttributeName) {
        String theValue = null;
        NamedNodeMap attrs = TheNode.getAttributes();
        Attr nameAttribute = (Attr) attrs.getNamedItem(AttributeName);
        if (nameAttribute != null) theValue = nameAttribute.getValue();
        return theValue;
    }

    // from Rajiv Ramnath
    public static Node[] NodeSelectManyGivenAttributeValue(Node[] Nodes,
                                                           String AttributeName,
                                                           String Value) {
        Node[] selectedNodes = null;
        ArrayList<Node> selectedNodesList = new ArrayList<Node>();

        int len = Nodes.length;
        for (int i = 0; i < len; i++) {
            String AttributeValue = NodeAttributeValueGet(Nodes[i],
                    AttributeName);
            if (AttributeValue.equals(Value)) {
                selectedNodesList.add(Nodes[i]);
            }
        }
        selectedNodes = new Node[len = selectedNodesList.size()];

        for (int i = 0; i < len; i++) {
            selectedNodes[i] = (Node) selectedNodesList.get(i);
        }

        return selectedNodes;
    }

    // uses code from http://www.mkyong.com/java/how-to-modify-xml-file-in-java-dom-parser/
    public Node addNodeElements(String tagName, String value, Element parent) {
        Document document = parent.getOwnerDocument();

        // Create a new Node with the given tag name
        Node node = document.createElement(tagName);

        // Add the node value as a child text node
        Text nodeVal = document.createTextNode(value);
        Node c = node.appendChild(nodeVal);

        // Add the new node structure to the parent node
        parent.appendChild(node);

        return parent;
    }

    public boolean addNode(Node node, Node parent){
        boolean addedNode = true;
        try {
            Log.e("com.management.finance.budgetcat.DOM.addNode", parent.toString());
            Document document = parent.getOwnerDocument();

            // Add the new node structure to the parent node
            parent.appendChild(node);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            addedNode = false;
        }
        return addedNode;
    }

    public Element createParentElement(String tagName, Node parent, String attributeName, String attributeValue){


        Document document = parent.getOwnerDocument();

        // Create a new Node with the given tag name
        Element element = document.createElement(tagName);
        element.setAttribute(attributeName, attributeValue);

        return element;
    }

}




