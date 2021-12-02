package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AttandenceActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attandence);
        String list[]={"Reguler","Bulan Puasa"};
        Spinner spinner = (Spinner) findViewById(R.id.spinnerWorkCode);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(AdapterList);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng ITS = new LatLng(-7.2819705,112.795323);
        mMap.addMarker(new MarkerOptions().position(ITS).title("Marker in ITS"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(ITS));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ITS,15));
    }
}