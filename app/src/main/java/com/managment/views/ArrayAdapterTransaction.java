package com.managment.views;

/**
 * http://www.javacodegeeks.com/2013/09/android-listview-with-adapter-example.html
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.managment.data.Transaction;
import com.managment.finance.budgetcat.R;

import java.util.ArrayList;


// here's our beautiful adapter

public class ArrayAdapterTransaction extends ArrayAdapter<Transaction> {



    Context mContext;

    int layoutResourceId;

    ArrayList<Transaction> data=new ArrayList<Transaction>();

    public ArrayAdapterTransaction(Context mContext, int layoutResourceId, ArrayList<Transaction> data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;

    }



    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        /*
31
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
32
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
33
         * It will have a non-null value when ListView is asking you recycle the row layout.
34
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
35
         */

        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        Transaction transactionItem = data.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values

        TextView textViewItem = (TextView) convertView.findViewById(R.id.list_item_transactions_plus_amount);
        String amount = String.format("%.2f", transactionItem.amount);
        textViewItem.setText(amount);
        textViewItem.setTag(transactionItem.TranscationID);

        return convertView;

    }

}
