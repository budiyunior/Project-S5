package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;
import com.ifcodedeveloper.cakwangcafe.model.cart.PostPutDelCart;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateCartActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_nama_produk;
    Button btn_pesan, btn_batal;
    ElegantNumberButton jumlah_item;
    String nama_produk;
    Cart cart;
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;
    String nama_pelanggan, no_meja, id_transaksi;
    CardView cart_close;
    public static final String EXTRA_CART = "extra_cart";

    CartActivity ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cart);

        tv_nama_produk = findViewById(R.id.nama_produk);
        jumlah_item = findViewById(R.id.jumlah_item);
        btn_pesan = findViewById(R.id.btn_pesan);
        btn_pesan.setOnClickListener(this);
        btn_batal = findViewById(R.id.btn_batals);
        btn_batal.setOnClickListener(this);
        cart_close = findViewById(R.id.card_close);
        cart_close.setOnClickListener(this);
        jumlah_item.setRange(1, 99);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
//        getWindow().setLayout((int) (width), (int) (height * 0.3));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        getWindow().setGravity(Gravity.BOTTOM);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
//        customer = getIntent().getParcelableExtra(EXTRA_CUSTOMER);
        cart = getIntent().getParcelableExtra(EXTRA_CART);
        nama_produk = cart.getNama_produk();
        tv_nama_produk.setText(cart.getNama_produk());

        jumlah_item.setNumber(cart.getJumlah());

        sharedPreferences = getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
        nama_pelanggan = sharedPreferences.getString("nama_pelanggan", "0");
        no_meja = sharedPreferences.getString("no_meja", "0");
        id_transaksi = sharedPreferences.getString("id_transaksi", "0");
    }
    void UpdateCart(){
        Double harga_satuan = Double.parseDouble(cart.getHarga_satuan());
        String hargaOld = jumlah_item.getNumber();
        Double hargaNew = Double.parseDouble(hargaOld);
        final Double sub_total = harga_satuan * hargaNew;
        Call<PostPutDelCart> updateKontakCall = mApiInterface.updateCart(jumlah_item.getNumber(),sub_total.toString(),id_transaksi,cart.getId_produk());
        updateKontakCall.enqueue(new Callback<PostPutDelCart>() {
            @Override
            public void onResponse(Call<PostPutDelCart> call, Response<PostPutDelCart> response) {
                Log.e("s", "onResponse: " );

                Log.e("total jumlah", "hasil cek " + jumlah_item.getNumber()+id_transaksi+cart.getId_produk());
//                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PostPutDelCart> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pesan:
                UpdateCart();
//                PostCart();
//                PostOrder();

//                customer.setNama_pelanggan(customer.getNama_pelanggan());
//                customer.setNo_meja(customer.getNo_meja());
//                produk.putExtra(EXTRA_CUSTOMER, customer);
                Intent produk = new Intent(UpdateCartActivity.this, CartActivity.class);
                startActivity(produk);
//                onBackPressed();
                break;
            case R.id.btn_batals:
            case R.id.card_close:
                onBackPressed();
                break;
        }
    }
}
