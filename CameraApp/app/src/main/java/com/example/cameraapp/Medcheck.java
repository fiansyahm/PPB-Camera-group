package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class Medcheck extends AppCompatActivity {

    private CheckBox gejala1, gejala2, gejala3,gejala4,gejala5,gejala6,gejala7,gejala8,gejala9;
    private Button kirim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medcheck);

        gejala1 = findViewById(R.id.gejala1);
        gejala2 = findViewById(R.id.gejala2);
        gejala3 = findViewById(R.id.gejala3);
        gejala4 = findViewById(R.id.gejala4);
        gejala5 = findViewById(R.id.gejala5);
        gejala6 = findViewById(R.id.gejala6);
        gejala7 = findViewById(R.id.gejala7);
        gejala8 = findViewById(R.id.gejala8);
        gejala9 = findViewById(R.id.gejala9);
        kirim=findViewById(R.id.kirim);


        // Saat Tombol Diklik Maka Program Akan mengeksekusi Pesanan dari User
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Melihat Status Kondisi Pada Pilihan Menu Ayam Goreng
                if(gejala1.isChecked()){

                }else if(!gejala1.isChecked()){

                }

                if(gejala2.isChecked()){

                }else if(!gejala2.isChecked()){

                }

                if(gejala3.isChecked()){

                }else if(!gejala3.isChecked()){

                }

                if(gejala4.isChecked()){

                }else if(!gejala4.isChecked()){

                }

                if(gejala5.isChecked()){

                }else if(!gejala5.isChecked()){

                }

                if(gejala6.isChecked()){

                }else if(!gejala6.isChecked()){

                }

                if(gejala7.isChecked()){

                }else if(!gejala7.isChecked()){

                }

                if(gejala8.isChecked()){

                }else if(!gejala8.isChecked()){

                }

            }
        });

        String list[]={"list pertama","list kedua","list ketiga"};
        Spinner spinner = (Spinner) findViewById(R.id.spinnerPernyataan);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(AdapterList);

        String list1[]={"list pertama","list kedua","list ketiga"};
        Spinner spinner1 = (Spinner) findViewById(R.id.spinnerRiwayat);
        ArrayAdapter<String> AdapterList1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list1);
        spinner1.setAdapter(AdapterList1);

    }

}