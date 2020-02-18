package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.cart.PostPutDelCart;
import com.ifcodedeveloper.cakwangcafe.model.transaction.PostTransaction;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteOrderActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    String nama_pelanggan, no_meja, id_produk, id_transaksi, nama_produk;
    ApiInterface mApiInterface;
    Button btn_hapus, btn_batal;
    TextView tvnama_produk;
    CardView cart_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_order);
        btn_hapus = findViewById(R.id.btn_pesan);
        btn_hapus.setOnClickListener(this);
        btn_batal = findViewById(R.id.btn_batals);
        btn_batal.setOnClickListener(this);
        cart_close = findViewById(R.id.card_close);
        cart_close.setOnClickListener(this);
        tvnama_produk = findViewById(R.id.nama_produk);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
//        getWindow().setLayout((int) (width ), (int) (height * .3));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Intent mIntent = getIntent();
        nama_produk = mIntent.getStringExtra("id_transaksi");
        tvnama_produk.setText("Hapus " + nama_produk + " dari keranjang?");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pesan:
                Intent intent = new Intent(DeleteOrderActivity.this, ListTransactionActivity.class);
                startActivity(intent);
                DeleteCart();
                break;
            case R.id.btn_batals:
            case R.id.card_close:
//                Intent mintent = new Intent(DeleteCartActivity.this,CartActivity.class);
//                startActivity(mintent);
                onBackPressed();
                break;
        }
    }

    public void DeleteCart() {
        Call<PostTransaction> deleteCart = mApiInterface.deleteOrder(nama_produk);
        deleteCart.enqueue(new Callback<PostTransaction>() {
            @Override
            public void onResponse(Call<PostTransaction> call, Response<PostTransaction> response) {
//                CartActivity.ca.ShowCart();
//                CartActivity.ca.finish();

                Toast.makeText(getApplicationContext(), "Berhasil Dihapus", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PostTransaction> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
