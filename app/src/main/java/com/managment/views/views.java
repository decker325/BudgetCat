package com.managment.views;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.managment.finance.budgetcat.R;

import java.util.ArrayList;


public class views extends Activity {

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
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");
            transactions.add("Food,10.50");
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");
            transactions.add("Food,10.50");
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");
            transactions.add("Gas,22.87");
            transactions.add("Rent,400");
            transactions.add("Food,10.50");



            //R.layout.list_item_forecast;

            ArrayAdapter<String> mForecastAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.list_item_transactions,
                    R.id.list_item_transactions_textview,
                    transactions
            );
            ListView listView =(ListView)rootView.findViewById(R.id.listView_transactions);
            listView.setAdapter(mForecastAdapter);



            return rootView;
        }

        public void onResume() {
            super.onResume();
            Log.e(TAG, "++ In onResume() ++");
            // Rest of onResume()...
        }
    }
}
