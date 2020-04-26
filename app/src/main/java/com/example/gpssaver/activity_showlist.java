package com.example.gpssaver;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class activity_showlist extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DatabaseHelper myDb;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = (ListView) findViewById(R.id.ListviewAddress);

        myDb = new DatabaseHelper(this);
        listItem = new ArrayList<>();

        viewData();
    }

    private void viewData() {

        Cursor cursor = myDb.getAllData();
        if(cursor.getCount() ==0){
            Toast.makeText(this, "I dont find", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "I  find", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
                Log.e("",cursor.getString(1));
            }
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listItem);
            listview.setAdapter(adapter);

        }
    }
}
