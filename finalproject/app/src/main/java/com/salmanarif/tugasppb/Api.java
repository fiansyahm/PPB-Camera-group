package com.salmanarif.tugasppb;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Api {
    @FormUrlEncoded
    @POST("upload_image.php")
    Call<com.salmanarif.tugasppb.Response> uploadImage(
            @Field("EN_IMAGE") String encodedImage
    );

    @FormUrlEncoded
    @POST("upload_formmedcheck.php")
    Call<Response> uploadMedcheck(
            @Field("EN_MED") String encoded
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<Response> login(
            @Field("EN_LOGIN") String encodedRegis
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Response> regis(
            @Field("EN_REGIS") String encodedRegis
    );


    @FormUrlEncoded
    @POST("uploadAttendance.php")
    Call<Response> uploadAttendance(
            @Field("EN_ATT") String encodedAtt
    );

    @FormUrlEncoded
    @POST("schedule.php")
    Call<Response> schedule(
            @Field("EN_SCH") String encodedSch
    );

    @FormUrlEncoded
    @POST("scheduledetail.php")
    Call<Response> scheduledetail(
            @Field("EN_SCHDET") String encodedSchdet
    );


}
