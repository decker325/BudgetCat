package com.managment.finance.budgetcat;

import android.app.Activity;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.managment.data.BcatDOMParsingTest;
import com.managment.data.Transaction;
import com.managment.data.TransactionDB;
import com.managment.views.MapsActivity;
import com.managment.views.MapsView;
import com.managment.views.TableView;
import com.managment.views.listView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class BudgetHome extends Activity {

    private static final String fileName = "Transactions.xml";
    private static BcatDOMParsingTest parser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budge_home);

        //Intent intent = new Intent(this, TableView.class);
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);

        //create menu

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



        //Setup internal xml file for database

//        FileOutputStream outputStream;
//        try {
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//
//            outputStream.close();
//            BcatDOMParsingTest xmlPaser = new BcatDOMParsingTest(filename);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try
        {
            // Creates a trace file in the primary external storage space of the
            // current application.
            // If the file does not exists, it is created.
            File traceFile = new File(((Context)this).getExternalFilesDir(null), fileName);
//            File traceFile = new File(((Context)this).getFilesDir(), fileName);
            TransactionDB.file=traceFile;
            parser =new BcatDOMParsingTest(traceFile);
            if (!traceFile.exists()){
                traceFile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(traceFile, true /*append*/));
                writer.write("<?xml version=\"1.0\"?>\n" +
                        "<BudgetCat>\n" +
                        "\n" +
                        "\n" +
                        "</BudgetCat>");
                writer.close();

            }

            TransactionDB.getSessionData(parser.getParsedData());

            // Adds a line to the trace file

            // Refresh the data so it can seen when the device is plugged in a
            // computer. You may have to unplug and replug the device to see the
            // latest changes. This is not necessary if the user should not modify
            // the files.
            MediaScannerConnection.scanFile((Context) (this),
                    new String[]{traceFile.toString()},
                    null,
                    null);

        }
        catch (IOException e)
        {
            Log.e("com.management.finance.budgetcat.budgethome", "Unable to write to the file.");
        }


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
