package com.ifcodedeveloper.cakwangcafe.rest;

import com.ifcodedeveloper.cakwangcafe.model.produk.GetProduk;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/produk")
    Call<GetProduk> getProduk();
}
