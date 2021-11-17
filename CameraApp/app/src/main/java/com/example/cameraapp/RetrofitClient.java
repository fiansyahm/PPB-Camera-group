package com.example.cameraapp;



import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String Base_URL="http://192.168.1.5/file_php/";
    private static RetrofitClient myclient;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit=new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static synchronized RetrofitClient getInstance(){
        if(myclient==null){
            myclient=new RetrofitClient();
        }
        return myclient;
    }

    public com.example.cameraapp.Api getApi(){
        return retrofit.create(Api.class);
    }
}
