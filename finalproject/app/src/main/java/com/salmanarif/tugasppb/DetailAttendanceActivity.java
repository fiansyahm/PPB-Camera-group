package com.salmanarif.tugasppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.squareup.picasso.Picasso;

public class DetailAttendanceActivity extends AppCompatActivity {

    TextView nama,posisi,waktu,workfromhome,statusabsen;
    ImageView signature,photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_attendance);
        Bundle extras = getIntent().getExtras();
        nama=findViewById(R.id.detailnamastr);
        nama.setText(extras.getString("nama"));
        posisi=findViewById(R.id.detailjabatanstr);
        posisi.setText(extras.getString("posisi"));
        waktu=findViewById(R.id.detailwaktustr);
        waktu.setText(extras.getString("waktu"));
        workfromhome=findViewById(R.id.detailworkfromhomestr);
        workfromhome.setText(extras.getString("workfromhome"));
        statusabsen=findViewById(R.id.detailstatusabsensistr);
        statusabsen.setText(extras.getString("statusabsen"));
        photo=findViewById(R.id.detailpicturetv);
        signature=findViewById(R.id.detailsignaturetv);
        Picasso.get()
                .load("https://ostensible-berry.000webhostapp.com/file_php/images/"+extras.getString("photo")+".jpg")
                .into(photo);
        Picasso.get()
                .load("https://ostensible-berry.000webhostapp.com/file_php/signatureAttendance/"+extras.getString("signature")+".jpg")
                .into(signature);


    }
}