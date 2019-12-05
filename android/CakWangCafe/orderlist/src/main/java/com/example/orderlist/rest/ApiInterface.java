package com.example.orderlist.rest;


import com.example.orderlist.model.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseLogin> login(@Field("email") String email,
                              @Field("password") String password);
}
