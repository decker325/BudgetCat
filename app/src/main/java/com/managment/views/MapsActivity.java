package com.managment.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.managment.data.Transaction;
import com.managment.data.TransactionDB;
import com.managment.finance.budgetcat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MapsActivity extends FragmentActivity {

    private TransactionDB DBtrans =new TransactionDB();

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


        Spinner spinner = (Spinner) findViewById(R.id.entry_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayList<String> type = new ArrayList<String>();
        type.add("---      Gas     ---");
        type.add("---      Rent    ---");
        type.add("*** Add New Type ***");
        getFragmentManager();
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_type,
                type
        );


// Specify the layout to use when the list of choices appears
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(typeAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getSelectedItem().toString().equals(("*** Add New Type ***"))){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MapsActivity.this);

                    alert.setTitle("Enter a New type;");
                    alert.setMessage("Message~bla bla bla");

// Set an EditText view to get user input
                    final EditText input = new EditText(MapsActivity.this);
                    alert.setView(input);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String value = input.getText().toString();
                            //TODO get value to types
                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Canceled.
                        }
                    });

                    alert.show();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final Button buttonEnter = (Button) findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText textAmount =  (EditText)findViewById(R.id.entry_editText);
                DatePicker dateSelect = (DatePicker)findViewById(R.id.map_datePicker);
                String valueText =textAmount.getText().toString();
                String alertMessage;
                if(valueText.length()==0){
                    alertMessage="Amount cannot be empty";
                }else{
                    alertMessage="Data added";
                    textAmount.setText("");
                    Transaction newTran = new Transaction();
                    newTran.amount=Double.parseDouble(valueText);
                    newTran.year= dateSelect.getYear();
                    newTran.month=dateSelect.getMonth();
                    newTran.day= dateSelect.getDayOfMonth();
                    LatLng currentLatLng =currentLocation();
                    newTran.locationLat=currentLatLng.latitude;
                    newTran.locationLong=currentLatLng.longitude;
                    newTran.add();
                }
                showMessage(alertMessage, MapsActivity.this);
            }


        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
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
            currentLocation();
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


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

//        Location myLocation = mMap.getMyLocation();
//        LatLng myLatLng = new LatLng(myLocation.getLatitude(),
//                myLocation.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(myLatLng));
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);
    }

    /**
     * Network connected
     */


    private boolean isNetConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo ni : info) {
                    if (ni.isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Wifi connected
     */
    private boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * check 3g network
     *
     *
     */
    private boolean is3gConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     *check gps enabled
     *
     */
    private boolean isGpsEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> accessibleProviders = lm.getProviders(true);
        for (String name : accessibleProviders) {
            if ("gps".equals(name)) {
                return true;
            }
        }
        return false;
    }

    private void showMessage(String message,Context context){
        final String dialogText = message;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.setTitle(message)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })
                .create();
        dialog.show();

        new CountDownTimer(3500, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                dialog.setTitle(dialogText+"  ("+millisUntilFinished/1000+")");
                // TODO Auto-generated method stub
            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }.start();
    }
    private LatLng currentLocation(){
        LatLng result=new LatLng(0,0);
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }

//            mMap.getMyLocation();
        if(isGpsEnabled()&&isNetConnected()) {

            Location myLocation;
            try{
                myLocation= mMap.getMyLocation();
                result= new LatLng(myLocation.getLatitude(),
                        myLocation.getLongitude());

                CameraPosition myPosition = new CameraPosition.Builder()
                        .target(result).zoom(10).bearing(0).tilt(0).build();
                mMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(myPosition));
//getActivity()

                //debug use
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                AlertDialog dialog = builder.setTitle("Lat:" + myLocation.getLatitude() +
//                        "\nLat:" + myLocation.getLongitude())
//
//                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        })
//
//                        .create();
//                dialog.show();



            }catch (NullPointerException e){

            }

        }else if (!isNetConnected()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder.setTitle("The Network service is not connected.")

                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    })

                    .create();
            dialog.show();
        }
        else if (!isGpsEnabled()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder.setTitle("The GPS service is not enabled.")

                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    })

                    .create();
            dialog.show();

        }

        return result;

    }

}
