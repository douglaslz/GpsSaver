package com.example.gpssaver;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class conexionGps extends AppCompatActivity {

    LocationManager locationManager;
    public Location location;


    public void conect(View view){

        Log.e("hola","ppppppppppppppppppppppppppppppp");


        //Check permission
        if(ActivityCompat.checkSelfPermission(conexionGps.this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

        }else{
            locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            location= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }



    }

}
