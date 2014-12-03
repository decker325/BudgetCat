package com.managment.finance.budgetcat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import com.managment.views.MapsActivity;
import com.managment.views.TableView;
import com.managment.views.listView;


public class BudgetHome extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent intent = new Intent(this, TableView.class);
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);

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
            Intent intent = new Intent(this, TableView.class);
            startActivity(intent);
        }else if(id== R.id.action_quit){
            System.exit(0);
        }else if (id==R.id.action_map){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }else if(id==R.id.action_list){
            Intent intent = new Intent(this, listView.class);
            startActivity(intent);
        }else if (id==R.id.action_entry){
            Intent intent = new Intent (this, entry.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}
