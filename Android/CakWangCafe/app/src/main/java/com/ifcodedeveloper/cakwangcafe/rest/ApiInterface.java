package com.ifcodedeveloper.cakwangcafe.rest;

import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;
import com.ifcodedeveloper.cakwangcafe.model.cart.GetCart;
import com.ifcodedeveloper.cakwangcafe.model.cart.PostPutDelCart;
import com.ifcodedeveloper.cakwangcafe.model.login.ResponseLogin;
import com.ifcodedeveloper.cakwangcafe.model.login.Wifi;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.GetOrderProduct;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.PostPutDelOrder;
import com.ifcodedeveloper.cakwangcafe.model.product.GetProduct;
import com.ifcodedeveloper.cakwangcafe.model.transaction.GetTransaction;
import com.ifcodedeveloper.cakwangcafe.model.transaction.PostTransaction;
import com.ifcodedeveloper.cakwangcafe.model.transaction.TotalHarga;
import com.ifcodedeveloper.cakwangcafe.model.transaction.Transaction;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseLogin> login(@Field("email") String email,
                              @Field("password") String password);

    @GET("api/produk")
    Call<GetProduct> getProduk();

    @FormUrlEncoded
    @POST("api/keranjang_customer")
    Call<GetCart> getCart(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("api/get_detail_transaksi")
    Call<GetOrderProduct> getOder(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("api/keranjang")
    Call<PostPutDelCart> postKontak(@Field("id_produk") String id_produk,
                                    @Field("id_transaksi") String id_transaksi,
                                    @Field("nama_produk") String nama_produk,
                                    @Field("gambar") String gambar,
                                    @Field("jumlah") String jumlah,
                                    @Field("harga_satuan") String harga_satuan,
                                    @Field("sub_total") String sub_total,
                                    @Field("nama_pelanggan") String nama_pelanggan,
                                    @Field("no_meja") String no_meja);

    @FormUrlEncoded
    @POST("api/delete_keranjang")
    Call<PostPutDelCart> deleteCart(@Field("id_transaksi") String id_transaksi,
                                    @Field("id_produk") String id_produk);

    @FormUrlEncoded
    @POST("api/delete_detail_trans")
    Call<PostPutDelOrder> deleteDetailTrans(@Field("id_transaksi") String id_transaksi,
                                            @Field("id_produk") String id_produk);


    @FormUrlEncoded
    @POST("api/detail_transaksi")
    Call<PostPutDelOrder> postOrder(@Field("id_transaksi") String id_transaksi,
                                    @Field("id_produk") String id_produk,
                                    @Field("nama_produk") String nama_produk,
                                    @Field("gambar") String gambar,
                                    @Field("tanggal") String tanggal,
                                    @Field("jumlah") String jumlah,
                                    @Field("harga") String harga,
                                    @Field("sub_total") String sub_total,
                                    @Field("nama_pelanggan") String nama_pelanggan,
                                    @Field("no_meja") String no_meja);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "api/keranjang", hasBody = true)
    Call<PostPutDelCart> deleteCart(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("api/total_harga")
    Call<TotalHarga> getTotal(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("api/post_transaksi")
    Call<PostTransaction> postTrans(@Field("id_transaksi") String id_transaksi,
                                    @Field("nama_pelanggan") String nama_pelanggan,
                                    @Field("no_meja") String no_meja,
                                    @Field("jam") String jam,
                                    @Field("tanggal") String tanggal,
                                    @Field("total_harga") String total_harga,
                                    @Field("shift") String shift,
                                    @Field("status_pesanan") String status_pesanan);

    @FormUrlEncoded
    @POST("api/get_transaksi")
    Call<Transaction> getTrans(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("api/transaksi")
    Call<GetTransaction> getTransList(@Field("tanggal") String tanggal,
                                      @Field("status_pesanan") String status_pesanan);

    @FormUrlEncoded
    @POST("api/get_all_transaksi")
    Call<GetTransaction> getAllTrans(@Field("tanggal") String tanggal);

    @FormUrlEncoded
    @POST("api/Produk")
    Call<GetProduct> getKategori(@Field("id_kategori") String id_kategori);

    @FormUrlEncoded
    @PUT("api/get_transaksi")
    Call<GetTransaction> updateStatus(@Field("shift") String shift,
                                      @Field("status_pesanan") String status_pesanan);

    @FormUrlEncoded
    @POST("api/update_trans_status")
    Call<GetTransaction> updateTrans(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("api/cek_keranjang")
    Call<Cart> cekCart(@Field("id_transaksi") String id_transaksi,
                       @Field("id_produk") String id_produk);

    @FormUrlEncoded
    @POST("api/update_keranjang")
    Call<PostPutDelCart> updateCart(@Field("jumlah") String jumlah,
                                    @Field("id_transaksi") String id_transaksi,
                                    @Field("id_produk") String id_produk);

    @FormUrlEncoded
    @POST("api/update_wifi")
    Call<Wifi> updateWifi(@Field("password") String password);

    @FormUrlEncoded
    @POST("api/wifi")
    Call<Wifi> cekWifi(@Field("id_wifi") String id_wifi);



}
