package com.salmanarif.tugasppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DetailAttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_attendance);
        Bundle extras = getIntent().getExtras();
        extras.getString("id");
        extras.getString("nama");
        extras.getString("posisi");
        extras.getString("waktu");
        extras.getString("workfromhome");
        extras.getString("statusabsen");
        extras.getString("photo");
        extras.getString("signature");


    }
}