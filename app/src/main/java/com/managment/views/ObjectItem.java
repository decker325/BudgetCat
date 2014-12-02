package com.managment.views;

/**
 * http://www.javacodegeeks.com/2013/09/android-listview-with-adapter-example.html
 */
//another class to handle item's id and name
public class ObjectItem {

    public String itemId;
    public String itemName;

    // constructor
    public ObjectItem(String itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }
}
