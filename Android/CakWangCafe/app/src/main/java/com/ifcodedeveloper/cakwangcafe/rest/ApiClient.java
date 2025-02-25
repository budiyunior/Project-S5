package com.ifcodedeveloper.cakwangcafe.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //    public static final String BASE_URL = "http://cakwangcafe.com/";
    public static final String BASE_URL = "http://kasir.cakwangcafe.com/";
    public static final String BASE_URL_FOTO = "http://kasir.cakwangcafe.com/assets/img/foto_produk/";
//    public static final String BASE_URL = "http://192.168.43.153/project_s5/website/";
//    public static final String BASE_URL_FOTO = "http://192.168.43.153/project_s5/website/assets/img/foto_produk/";
    private static Retrofit retrofit = null;

    //    public static Retrofit getClient() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        if (retrofit==null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
//                    .build();
//        }
//        return retrofit;
//
//    }
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}