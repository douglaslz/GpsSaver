package com.example.gpssaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {

    LocationManager locationManager;
    Location location;
    double longitudeGPS =0.0, latitudeGPS= 0.0;
    TextView longitudeValueGPS, latitudeValueGPS,name;
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);



        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        name = (TextView) findViewById(R.id.txtName);
        latitudeValueGPS = (TextView) findViewById(R.id.txtlatitude);
        longitudeValueGPS = (TextView) findViewById(R.id.txtlongitude);

        //Check permission
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

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



    public void addData(View view){

        boolean isInserted = myDb.insertData(name.getText().toString(),
                latitudeValueGPS.getText().toString(), longitudeValueGPS.getText().toString());
        if (isInserted == true) {
            Toast.makeText(SaveActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(SaveActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(SaveActivity.this, MainActivity.class));
    }


}





