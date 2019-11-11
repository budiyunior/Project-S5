package com.ifcodedeveloper.cakwangcafe.rest;

import com.ifcodedeveloper.cakwangcafe.model.cart.GetCart;
import com.ifcodedeveloper.cakwangcafe.model.cart.PostPutDelCart;
import com.ifcodedeveloper.cakwangcafe.model.login.ResponseLogin;
import com.ifcodedeveloper.cakwangcafe.model.produk.GetProduct;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseLogin> login(@Field("email") String email,
                              @Field("password") String password
    );

    @GET("api/produk")
    Call<GetProduct> getProduk();

    @FormUrlEncoded
    @POST("api/keranjang_customer")
    Call<GetCart> getCart(@Field("nama_pelanggan") String nama_pelanggan,
                          @Field("no_meja") String no_meja);

    @FormUrlEncoded
    @POST("api/keranjang")
    Call<PostPutDelCart> postKontak(@Field("id_produk") String nama,
                                    @Field("nama_produk") String nama_produk,
                                    @Field("jumlah") String jumlah,
                                    @Field("harga_satuan") String harga_satuan,
                                    @Field("sub_total") String sub_total,
                                    @Field("nama_pelanggan") String nama_pelanggan,
                                    @Field("no_meja") String no_meja);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "api/keranjang", hasBody = true)
    Call<PostPutDelCart> deleteCart(@Field("nama_pelanggan") String nama_pelanggan,
                                    @Field("no_meja") String no_meja);
}
