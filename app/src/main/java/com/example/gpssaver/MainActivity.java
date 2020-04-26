package com.example.gpssaver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    LocationManager locationManager;
    Location location;
    double longitudeGPS =0.0, latitudeGPS= 0.0;
    TextView longitudeValueGPS, latitudeValueGPS;
    DatabaseHelper myDb;
    Button btnViewAll;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        latitudeValueGPS = (TextView) findViewById(R.id.latitud);
        longitudeValueGPS = (TextView) findViewById(R.id.longitud);
        btnViewAll = (Button) findViewById(R.id.btnShowPoints);

        myDb = new DatabaseHelper(this);


    }

    public void getPoint(View view){


        //Check permission
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED){

        }else{
            locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            location= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }

        if(location != null){
            latitudeGPS =  location.getLatitude();
            latitudeValueGPS.setText(""+latitudeGPS);
            longitudeGPS =  location.getLongitude();
            longitudeValueGPS.setText(""+longitudeGPS);
        }


    }


    public void openSave(View view){
        startActivity(new Intent(MainActivity.this, SaveActivity.class));
    }


    public void viewAll(View view)
    {

        startActivity(new Intent(MainActivity.this, activity_showlist.class));


    }


    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}
