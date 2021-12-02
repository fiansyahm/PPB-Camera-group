package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AttandenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attandence);
        String list[]={"Reguler","Bulan Puasa"};
        Spinner spinner = (Spinner) findViewById(R.id.spinnerWorkCode);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(AdapterList);
    }
}