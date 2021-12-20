package com.salmanarif.tugasppb;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

public class MedcheckActivity extends AppCompatActivity {

    private CheckBox gejala1, gejala2, gejala3,gejala4,gejala5,gejala6,gejala7,gejala8,gejala9;
    private Button kirim;
    private String gejala;
    private String Nama;
    private TextView helloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medcheck);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Nama = extras.getString("nama");
            helloTv=findViewById(R.id.hello);
            helloTv.setText("Selamat Datang "+Nama);

            //The key argument here must match that used in the other activity
        }

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

        String list[]={"Sehat","Tidak Sehat"};
        Spinner spinner = (Spinner) findViewById(R.id.spinnerRiwayat);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(AdapterList);

        String list1[]={"Ya","Tidak"};
        Spinner spinner1 = (Spinner) findViewById(R.id.spinnerPernyataan);
        ArrayAdapter<String> AdapterList1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list1);
        spinner1.setAdapter(AdapterList1);

        String list2[]={"Layak untuk mengikuti kegiatan offline hari ini.","Layak mengikuti kegiatan secara online."};
        Spinner spinner2 = (Spinner) findViewById(R.id.spinnerKeputusan);
        ArrayAdapter<String> AdapterList2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list2);
        spinner2.setAdapter(AdapterList2);


        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gejala=extras.getString("id")+";";

                // Melihat Status Kondisi Pada Pilihan Menu Ayam Goreng
                if(gejala1.isChecked()){
                    gejala=gejala+"Ya;";
                }else if(!gejala1.isChecked()){
                    gejala=gejala+"Tidak;";
                }

                if(gejala2.isChecked()){
                    gejala=gejala+"Ya;";
                }else if(!gejala2.isChecked()){
                    gejala=gejala+"Tidak;";
                }

                if(gejala3.isChecked()){
                    gejala=gejala+"Ya;";
                }else if(!gejala3.isChecked()){
                    gejala=gejala+"Tidak;";
                }

                if(gejala4.isChecked()){
                    gejala=gejala+"Ya;";
                }else if(!gejala4.isChecked()){
                    gejala=gejala+"Tidak;";
                }

                if(gejala5.isChecked()){
                    gejala=gejala+"Ya;";
                }else if(!gejala5.isChecked()){
                    gejala=gejala+"Tidak;";
                }

                if(gejala6.isChecked()){
                    gejala=gejala+"Ya;";
                }else if(!gejala6.isChecked()){
                    gejala=gejala+"Tidak;";
                }

                if(gejala7.isChecked()){
                    gejala=gejala+"Ya;";
                }else if(!gejala7.isChecked()){
                    gejala=gejala+"Tidak;";
                }

                if(gejala8.isChecked()){
                    gejala=gejala+"Ya;";
                }else if(!gejala8.isChecked()){
                    gejala=gejala+"Tidak;";
                }

                if(gejala9.isChecked()){
                    gejala=gejala+"Ya;";
                }else if(!gejala9.isChecked()){
                    gejala=gejala+"Tidak;";
                }

//                startActivity(new Intent(getBaseContext(), MainActivity.class));
                String riwayat=spinner.getSelectedItem().toString();
                String pernyataan=spinner1.getSelectedItem().toString();
                String keputusan=spinner2.getSelectedItem().toString();
                gejala=gejala+pernyataan+";"+riwayat+";"+keputusan;

                Call<Response> call=RetrofitClient.getInstance().getApi().uploadMedcheck(gejala);
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(MedcheckActivity.this, response.body().getRemark(), Toast.LENGTH_SHORT).show();

//                        startActivity(new Intent(getBaseContext(), AttendanceActivity.class));
                        Intent i = new Intent(MedcheckActivity.this, NavigationActivity.class);
                        i.putExtra("id",extras.getString("id"));
                        i.putExtra("nama",extras.getString("nama"));
                        i.putExtra("posisi",extras.getString("posisi"));
                        i.putExtra("myimage",extras.getString("myimage"));
                        startActivity(i);
//                if(response.body().isStatus()){
//
//                }else{
//
//                }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(MedcheckActivity.this, "Form Gagal Terkirim", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}