package com.salmanarif.tugasppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1=(Button) findViewById(R.id.login);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToServer();
            }
        });
    }

    public void uploadToServer(){
        Call<Response> call=RetrofitClient.getInstance().getApi().login("tes");
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(LoginActivity.this, "Upload Sukses", Toast.LENGTH_SHORT).show();

//                if(response.body().isStatus()){
//
//                }else{
//
//                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Upload Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }
}