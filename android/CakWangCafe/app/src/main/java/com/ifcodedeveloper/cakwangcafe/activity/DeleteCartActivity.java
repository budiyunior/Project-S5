package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.cart.PostPutDelCart;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.PostPutDelOrder;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteCartActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    String nama_pelanggan, no_meja,id_produk, id_transaksi,nama_produk;
    ApiInterface mApiInterface;
    Button btn_hapus, btn_batal;
    TextView tvnama_produk;
public DeleteCartActivity de;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_cart);
        btn_hapus = findViewById(R.id.btn_pesan);
        btn_hapus.setOnClickListener(this);
        btn_batal = findViewById(R.id.btn_batals);
        btn_batal.setOnClickListener(this);
        tvnama_produk= findViewById(R.id.nama_produk);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width ), (int) (height * .3));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        sharedPreferences = getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
        nama_pelanggan = sharedPreferences.getString("nama_pelanggan", "0");
        no_meja = sharedPreferences.getString("no_meja", "0");
        id_transaksi = sharedPreferences.getString("id_transaksi", "0");
        id_produk = getIntent().getStringExtra("id_produk");
        nama_produk = getIntent().getStringExtra("nama_produk");
        Log.e("id Produk", "idProduk "+id_produk +"trans"+id_transaksi);
        tvnama_produk.setText("Hapus "+nama_produk+" dari keranjang?");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pesan:
                Intent intent = new Intent(DeleteCartActivity.this,CartActivity.class);
                startActivity(intent);
                DeleteCart();
                DeleteDetail();
                break;
            case R.id.btn_batals:
//                Intent mintent = new Intent(DeleteCartActivity.this,CartActivity.class);
//                startActivity(mintent);
                onBackPressed();
                break;
        }
    }


    public void DeleteCart() {
        Call<PostPutDelCart> deleteCart = mApiInterface.deleteCart(id_transaksi,id_produk);
        deleteCart.enqueue(new Callback<PostPutDelCart>() {
            @Override
            public void onResponse(Call<PostPutDelCart> call, Response<PostPutDelCart> response) {
//                CartActivity.ca.ShowCart();
//                CartActivity.ca.finish();

                Toast.makeText(getApplicationContext(), "Berhasil Dihapus", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PostPutDelCart> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void DeleteDetail() {
        Call<PostPutDelOrder> deleteCart = mApiInterface.deleteDetailTrans(id_transaksi,id_produk);
        deleteCart.enqueue(new Callback<PostPutDelOrder>() {
            @Override
            public void onResponse(Call<PostPutDelOrder> call, Response<PostPutDelOrder> response) {
//                CartActivity.ca.ShowCart();
//                CartActivity.ca.finish();

                Toast.makeText(getApplicationContext(), "Berhasil Dihapus", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PostPutDelOrder> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

}
