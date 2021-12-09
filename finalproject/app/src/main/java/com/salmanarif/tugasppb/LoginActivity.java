package com.salmanarif.tugasppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    EditText EmailLogin,PasswordLogin;
    String  EmailLoginStr,PasswordLoginStr,send;
    Button Loginbtn,regisbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EmailLogin=findViewById(R.id.Email_Login);
        PasswordLogin=findViewById(R.id.Pass_Login);
        Loginbtn=findViewById(R.id.login_btn);
        regisbtn=findViewById(R.id.SignUp_LogIn);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmailLoginStr=EmailLogin.getText().toString();
                PasswordLoginStr=PasswordLogin.getText().toString();
                send=EmailLoginStr+";"+PasswordLoginStr;
                uploadToServer(send);
            }
        });
        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });

    }


    public void uploadToServer(String send){
        Call<Response> call=RetrofitClient.getInstance().getApi().login(send);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.body().isStatus()){
                    Toast.makeText(LoginActivity.this,response.body().getRemark(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), MedcheckActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, response.body().getRemark(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Upload Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }


}