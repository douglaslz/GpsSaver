package com.example.gpssaver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    Location location;
    double longitudeGPS =0.0, latitudeGPS= 0.0;
    TextView longitudeValueGPS, latitudeValueGPS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        latitudeValueGPS = (TextView) findViewById(R.id.latitud);
        longitudeValueGPS = (TextView) findViewById(R.id.longitud);


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

}
