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
    @POST("upload_attendance.php")
    Call<Response> uploadAttendance(
            @Field("EN_ATTENDANCE") String encodedAtt
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

    @FormUrlEncoded
    @POST("attendance.php")
    Call<Response> attendance(
            @Field("EN_ATT") String encodedAtt
    );

    @FormUrlEncoded
    @POST("contoh.php")
    Call<Response> contoh(
            @Field("EN_ATT") String axax
    );

    @FormUrlEncoded
    @POST("updateprofile.php")
    Call<Response> updateprofile(
            @Field("EN_PROF") String updateprofil
    );

    @FormUrlEncoded
    @POST("listmedcheck.php")
    Call<Response> listmedcheck(
            @Field("EN_LMEDCHECK") String listmedcheck
    );
}
