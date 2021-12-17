package com.salmanarif.tugasppb;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class listAttendanceActivity extends AppCompatActivity {
    listAdapter Kontakadapter;
    private SQLiteDatabase dbku;
    private SQLiteOpenHelper dbopen;
    private String nomerHandphone;
    ArrayList<list> listKontak = new ArrayList<>();
    String currentdatetime[];
    String workfromhome[];
    String statusabsen[];
    String photo[];
    String signature[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_attendance);

        ListView lv = findViewById(R.id.listView);
        Kontakadapter= new listAdapter(this,R.layout.item_user,listKontak);
        lv.setAdapter(Kontakadapter);
//        Kontakadapter.add(new list("a","x"));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            public void onItemClick(AdapterView<?> parent, View item, int position, long rowID) {
                Bundle extras = getIntent().getExtras();
                Intent i = new Intent(listAttendanceActivity.this, DetailAttendanceActivity.class);
                i.putExtra("id",extras.getString("id"));
                i.putExtra("nama",extras.getString("nama"));
                i.putExtra("posisi",extras.getString("posisi"));
                i.putExtra("waktu",currentdatetime[position]);
                i.putExtra("workfromhome",workfromhome[position]);
                i.putExtra("statusabsen",statusabsen[position]);
                i.putExtra("photo",photo[position]);
                i.putExtra("signature",signature[position]);
                startActivity(i);
                startActivity(new Intent(getBaseContext(), DetailAttendanceActivity.class));

                //                Cursor cur = dbku.rawQuery("select * from kontak",null);
//                int i=0;if(cur.getCount() > 0) cur.moveToFirst();
//                while(i<=position){
//                    nomerHandphone=cur.getString(cur.getColumnIndex("nohp"));
//                    cur.moveToNext();
//                    i++;
//                }
//                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Uri.encode(nomerHandphone))));
            }
        });
        ambildata();
    }



    private void insertKontak(String nm, String hp){
        list newKontak = new list(nm,hp);
        Kontakadapter.add(newKontak);
    }

    @SuppressLint("Range")
    private void ambildata(){

        Bundle extras = getIntent().getExtras();
        Call<Response> call=RetrofitClient.getInstance().getApi().attendance(extras.getString("id"));
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if(response.body().isStatus()){
                    currentdatetime=response.body().getCurrentdatetime();
                    workfromhome=response.body().getWorkfromhome();
                    statusabsen=response.body().getAttendancestatus();
                    photo=response.body().getPhoto();
                    signature=response.body().getSignature();
                    for(int x=0;x<response.body().getTotalrow();x++)
                        insertKontak(currentdatetime[x], statusabsen[x]);
                    Toast.makeText(listAttendanceActivity.this,"Sukses Load Data", Toast.LENGTH_SHORT).show();
                }else{

                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Toast.makeText(listAttendanceActivity.this, "Gagal Load Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void operasi (View v){

         change_data(v);
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void change_data(View v){
        listKontak.clear();
        ambildata();
        LayoutInflater li = LayoutInflater.from(this);
        View inputDialog = li.inflate(R.layout.input_dialog,null);

        AlertDialog.Builder buat = new AlertDialog.Builder(this);
        buat.setView(inputDialog);

        final EditText nama = inputDialog.findViewById(R.id.nama);
        final EditText noHp = inputDialog.findViewById(R.id.noHp);

        buat
                .setCancelable(false)
                .setPositiveButton("YES", (dialog, which) -> {
//                  if (v.getId() == R.id.btnSearch) {
//                        search_item(nama.getText().toString(), noHp.getText().toString());
//                    }
//                    dialog.dismiss();
                })
                .setNegativeButton("NO", (dialog, which) -> dialog.cancel());
        buat.show();
    }
}