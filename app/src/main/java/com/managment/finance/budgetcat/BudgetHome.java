package com.managment.finance.budgetcat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import com.managment.views.MapsActivity;
import com.managment.views.TableView;
import com.managment.views.views;
import com.managment.data.SQLDatabase;


public class BudgetHome extends Activity {
    private SQLDatabase budgetCatDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent intent = new Intent(this, TableView.class);
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

        budgetCatDatabase = new SQLDatabase(this);
    }


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
        }
        return super.onOptionsItemSelected(item);
    }
}
