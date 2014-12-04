package com.managment.finance.budgetcat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.managment.data.Transaction;
import com.managment.views.MapsActivity;
import com.managment.views.MapsView;
import com.managment.views.TableView;
import com.managment.views.listView;


public class BudgetHome extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budge_home);

        //Intent intent = new Intent(this, TableView.class);
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);

        Button buttonEnter = (Button) findViewById(R.id.button_enter_view);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BudgetHome.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        Button buttonMap = (Button) findViewById(R.id.button_map_view);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BudgetHome.this,MapsView.class);
                startActivity(intent);
            }
        });
        Button buttonList = (Button) findViewById(R.id.button_list_view);
        buttonList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BudgetHome.this,listView.class);
                startActivity(intent);
            }
        });
        Button buttonQuit = (Button) findViewById(R.id.button_quit);
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.exit(0);
            }
        });




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
        }else if (id==R.id.action_enter_data){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }else if(id==R.id.action_list){
            Intent intent = new Intent(this, listView.class);
            startActivity(intent);
        }else if (id==R.id.action_map_view){
            Intent intent = new Intent (this,MapsView.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
