package com.salmanarif.tugasppb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    public EditText EmailLogin,PasswordLogin;
    public String  EmailLoginStr,PasswordLoginStr,send;
    public Button Loginbtn,regisbtn;
    public String id,nama,posisi,myimage;

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
                    id=response.body().getID();
                    nama=response.body().getNama();
                    posisi=response.body().getPosisi();
                    myimage=response.body().getMyimage();


                    if(response.body().getTotalrow()==0){
                        Intent i = new Intent(LoginActivity.this, MedcheckActivity.class);
                        i.putExtra("id",id);
                        i.putExtra("nama",nama);
                        i.putExtra("posisi",posisi);
                        i.putExtra("myimage",myimage);
                        startActivity(i);
                    }
                    else{
                        Intent i = new Intent(LoginActivity.this, NavigationActivity.class);
                        i.putExtra("id",id);
                        i.putExtra("nama",nama);
                        i.putExtra("posisi",posisi);
                        i.putExtra("myimage",myimage);
                        startActivity(i);
                    }
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