package com.ifcodedeveloper.cakwangcafe.rest;

import com.ifcodedeveloper.cakwangcafe.model.cart.GetCart;
import com.ifcodedeveloper.cakwangcafe.model.cart.PostPutDelCart;
import com.ifcodedeveloper.cakwangcafe.model.login.ResponseLogin;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.GetOrderProduct;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.PostPutDelOrder;
import com.ifcodedeveloper.cakwangcafe.model.product.GetProduct;
import com.ifcodedeveloper.cakwangcafe.model.transaction.PostTransaction;
import com.ifcodedeveloper.cakwangcafe.model.transaction.TotalHarga;

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
                              @Field("password") String password);

    @GET("api/produk")
    Call<GetProduct> getProduk();

    @FormUrlEncoded
    @POST("api/keranjang_customer")
    Call<GetCart> getCart(@Field("nama_pelanggan") String nama_pelanggan,
                          @Field("no_meja") String no_meja);
    @FormUrlEncoded
    @POST("api/keranjang_customer")
    Call<GetOrderProduct> getOder(@Field("nama_pelanggan") String nama_pelanggan,
                                  @Field("no_meja") String no_meja);

    @FormUrlEncoded
    @POST("api/keranjang")
    Call<PostPutDelCart> postKontak(@Field("id_produk") String id_produk,
                                    @Field("nama_produk") String nama_produk,
                                    @Field("jumlah") String jumlah,
                                    @Field("harga_satuan") String harga_satuan,
                                    @Field("sub_total") String sub_total,
                                    @Field("nama_pelanggan") String nama_pelanggan,
                                    @Field("no_meja") String no_meja);
    @FormUrlEncoded
    @POST("api/pesanan")
    Call<PostPutDelOrder> postOrder(@Field("id_produk") String id_produk,
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

    @FormUrlEncoded
    @POST("api/total_harga")
    Call<TotalHarga> getTotal(@Field("nama_pelanggan") String nama_pelanggan,
                              @Field("no_meja") String no_meja);

    @FormUrlEncoded
    @POST("api/post_transaksi")
    Call<PostTransaction> postTrans(@Field("nama_pelanggan") String nama_pelanggan,
                                    @Field("no_meja") String no_meja,
                                    @Field("jam") String jam,
                                    @Field("tanggal") String tanggal,
                                    @Field("total_harga") String total_harga,
                                    @Field("shift") String shift);
}
