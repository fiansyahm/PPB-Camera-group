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

public class RegisterActivity extends AppCompatActivity {

    EditText Position,Name,EmailSignUp,PasswordLogin;
    String  PositionStr,NameStr,EmailSignUpStr,PasswordLoginStr,send;
    Button SignUp_LogInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Position=findViewById(R.id.Position);
        Name=findViewById(R.id.Name);
        EmailSignUp=findViewById(R.id.EmailSignUp);
        PasswordLogin=findViewById(R.id.PasswordLogin);
        SignUp_LogInBtn=findViewById(R.id.SignUp_LogIn);


        SignUp_LogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PositionStr=Position.getText().toString();
                NameStr=Name.getText().toString();
                EmailSignUpStr=EmailSignUp.getText().toString();
                PasswordLoginStr=PasswordLogin.getText().toString();
                send=NameStr+";"+PositionStr+";"+EmailSignUpStr+";"+PasswordLoginStr;
                uploadToServer(send);

//                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
        });



    }


    public void uploadToServer(String send){
        Call<Response> call=RetrofitClient.getInstance().getApi().regis(send);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.body().isStatus()){
                    Toast.makeText(RegisterActivity.this,response.body().getRemark(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), LoginActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this, response.body().getRemark(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Upload Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }


}