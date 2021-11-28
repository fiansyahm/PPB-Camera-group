package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class Medcheck extends AppCompatActivity {

    private CheckBox gejala1, gejala2, gejala3,gejala4,gejala5,gejala6,gejala7,gejala8,gejala9;
    private Button kirim;
    private String gejala;
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


        String list[]={"Ya","Tidak"};
        Spinner spinner = (Spinner) findViewById(R.id.spinnerPernyataan);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(AdapterList);

        String list1[]={"Sehat","Tidak Sehat"};
        Spinner spinner1 = (Spinner) findViewById(R.id.spinnerRiwayat);
        ArrayAdapter<String> AdapterList1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list1);
        spinner1.setAdapter(AdapterList1);

        String list2[]={"Layak untuk mengikuti kegiatan offline hari ini.","Layak mengikuti kegiatan secara online."};
        Spinner spinner2 = (Spinner) findViewById(R.id.spinnerKeputusan);
        ArrayAdapter<String> AdapterList2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list2);
        spinner2.setAdapter(AdapterList2);


        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Melihat Status Kondisi Pada Pilihan Menu Ayam Goreng
                if(gejala1.isChecked()){
                    gejala=gejala+"Demam;";
                }else if(!gejala1.isChecked()){
                    gejala=gejala+";";
                }
                if(gejala2.isChecked()){
                    gejala=gejala+"Batuk-Pilek;";
                }else if(!gejala2.isChecked()){
                    gejala=gejala+";";
                }
                if(gejala3.isChecked()){
                    gejala=gejala+"Tenggorokan Sakit;";
                }else if(!gejala3.isChecked()){
                    gejala=gejala+";";
                }
                if(gejala4.isChecked()){
                    gejala=gejala+"Hidung Tersumbat;";
                }else if(!gejala4.isChecked()){
                    gejala=gejala+";";
                }
                if(gejala5.isChecked()){
                    gejala=gejala+"Nyeri Kepala;";
                }else if(!gejala5.isChecked()){
                    gejala=gejala+";";
                }
                if(gejala6.isChecked()){
                    gejala=gejala+"Sesak Nafas;";
                }else if(!gejala6.isChecked()){
                    gejala=gejala+";";
                }
                if(gejala7.isChecked()){
                    gejala=gejala+"Lemas;";
                }else if(!gejala7.isChecked()){
                    gejala=gejala+";";
                }
                if(gejala8.isChecked()){
                    gejala=gejala+"Hilang Penciuman;";
                }else if(!gejala8.isChecked()){
                    gejala=gejala+";";
                }
                if(gejala9.isChecked()){
                    gejala=gejala+"Diare;";
                }else if(!gejala9.isChecked()){
                    gejala=gejala+";";
                }
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                String pernyataan=spinner.getSelectedItem().toString();
                String riwayat=spinner1.getSelectedItem().toString();
                String keputusan=spinner2.getSelectedItem().toString();
            }
        });
    }

}