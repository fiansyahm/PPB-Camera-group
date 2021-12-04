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
    @POST("upload_attendance.php")
    Call<Response> uploadAttendance(
            @Field("EN_ATT") String encoded
    );

}
