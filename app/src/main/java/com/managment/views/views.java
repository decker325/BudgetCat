package com.managment.views;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.managment.data.Transaction;
import com.managment.data.TransactionDB;
import com.managment.finance.budgetcat.R;

import java.util.ArrayList;


public class views extends Activity {

    private TransactionDB DBtrans =new TransactionDB();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.views, menu);
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.budget_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if(id== R.id.action_quit){
            System.exit(0);
        }else if (id==R.id.action_map){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }else if(id==R.id.action_list){
            Intent intent = new Intent(this, views.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }
        private final String TAG = ((Object) this).getClass().getSimpleName();
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_views, container, false);

            Log.e(TAG, "+++ In onCreate() +++");


            ArrayList<String> transactions = new ArrayList<String>();
            ArrayList<String> transactionID = new ArrayList<String>();

            ArrayList<ObjectItem> transactionItems = new ArrayList<ObjectItem>();

            for(String key:TransactionDB.getTransactionKeys()){

                Transaction toAdded = TransactionDB.get(key);
                transactionID.add(toAdded.getTransactionID());
                transactions.add("X:"+toAdded.amount);
                transactionItems.add(new ObjectItem(toAdded.getTransactionID(),"X:"+toAdded.amount));
            }


            ArrayAdapterItem adapter = new ArrayAdapterItem(getActivity(),
                    R.layout.list_item_transactions,
                    transactionItems );

//            transactions.add("Gas,22.87");
//            transactions.add("Rent,400");
//            transactions.add("Food,10.50");
//            transactions.add("Gas,22.87");
//            transactions.add("Rent,400");
//            transactions.add("Food,10.50");
//            transactions.add("Gas,22.87");
//            transactions.add("Rent,400");
//            transactions.add("Food,10.50");
//            transactions.add("Gas,22.87");
//            transactions.add("Rent,400");
//



            //R.layout.list_item_forecast;

            ArrayAdapter<String> mEntryAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.list_item_transactions,
                    R.id.list_item_transactions_textview,
                    transactions
            );


            ListView listView =(ListView)rootView.findViewById(R.id.listView_transactions);
            listView.setAdapter(adapter);
//            listView.setAdapter(mEntryAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Context context = view.getContext();

                    TextView textViewItem = ((TextView) view.findViewById(R.id.list_item_transactions_textview));

                    // get the clicked item name
                    String listItemText = textViewItem.getText().toString();

                    // get the clicked item ID
                    final String listItemId = textViewItem.getTag().toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    AlertDialog dialog = builder.setTitle(listItemText+listItemId)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            })
                            .setNegativeButton("Delete", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                    TransactionDB.remove(TransactionDB.get(listItemId));
                                }

                            })
                            .create();
                    dialog.show();


                }
            });


            return rootView;
        }

        public void onResume() {
            super.onResume();
            Log.e(TAG, "++ In onResume() ++");
            // Rest of onResume()...
        }
    }
}
