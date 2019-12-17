package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.cart.PostPutDelCart;
import com.ifcodedeveloper.cakwangcafe.model.customer.Customer;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.PostPutDelOrder;
import com.ifcodedeveloper.cakwangcafe.model.product.Product;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderProductActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_PRODUCT = "extra_product";
    public static final String EXTRA_CUSTOMER = "extra_customer";
    TextView tv_nama_produk;
    Button btn_pesan, btn_batal;
    ElegantNumberButton jumlah_item;
    String nama_produk;
    Product product;
    Customer customer = new Customer();
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;
    String nama_pelanggan, no_meja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_product);
        tv_nama_produk = findViewById(R.id.nama_produk);
        jumlah_item = findViewById(R.id.jumlah_item);
        btn_pesan = findViewById(R.id.btn_pesan);
        btn_pesan.setOnClickListener(this);
        btn_batal = findViewById(R.id.btn_batal);
        btn_batal.setOnClickListener(this);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        getWindow().setLayout((int) (width * .5), (int) (height * .1));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

//        customer = getIntent().getParcelableExtra(EXTRA_CUSTOMER);
        product = getIntent().getParcelableExtra(EXTRA_PRODUCT);
        nama_produk = product.getNama_produk();
        tv_nama_produk.setText(product.getNama_produk());

        sharedPreferences = getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
        nama_pelanggan = sharedPreferences.getString("nama_pelanggan", "0");
        no_meja = sharedPreferences.getString("no_meja", "0");
    }

    void PostCart() {
//        final String harga_satuan = product.getHarga_satuan();
        Double harga_satuan = Double.parseDouble(product.getHarga_satuan());
        String hargaOld = jumlah_item.getNumber();
        Double hargaNew = Double.parseDouble(hargaOld);
        final Double sub_total = harga_satuan * hargaNew;
        Call<PostPutDelCart> postCartCall = mApiInterface.postKontak(product.getId_produk(), product.getNama_produk(), jumlah_item.getNumber(), product.getHarga_satuan()
                , sub_total.toString(), nama_pelanggan, no_meja);
        postCartCall.enqueue(new Callback<PostPutDelCart>() {
            @Override
            public void onResponse(Call<PostPutDelCart> call, Response<PostPutDelCart> response) {
                Toast.makeText(getApplicationContext(), "Berhasil ditambahkan", Toast.LENGTH_LONG).show();
                Log.e("Berhasil", "berhasil desain cart" + sub_total + product.getHarga_satuan() + jumlah_item.getNumber());
                finish();
            }

            @Override
            public void onFailure(Call<PostPutDelCart> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
    void PostOrder() {
//        final String harga_satuan = product.getHarga_satuan();
        Double harga_satuan = Double.parseDouble(product.getHarga_satuan());
        String hargaOld = jumlah_item.getNumber();
        Double hargaNew = Double.parseDouble(hargaOld);
        final Double sub_total = harga_satuan * hargaNew;
        Call<PostPutDelOrder> postCartCall = mApiInterface.postOrder(product.getId_produk(), product.getNama_produk(),
                jumlah_item.getNumber(), product.getHarga_satuan(),sub_total.toString(), nama_pelanggan, no_meja);
        postCartCall.enqueue(new Callback<PostPutDelOrder>() {
            @Override
            public void onResponse(Call<PostPutDelOrder> call, Response<PostPutDelOrder> response) {
                Toast.makeText(getApplicationContext(), "Berhasil ditambahkan", Toast.LENGTH_LONG).show();
                Log.e("Berhasil", "berhasil desain order" + sub_total + product.getHarga_satuan() + jumlah_item.getNumber());
                finish();
            }

            @Override
            public void onFailure(Call<PostPutDelOrder> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pesan:
                PostCart();
                PostOrder();
                Intent produk = new Intent(OrderProductActivity.this, ProductActivity.class);
                customer.setNama_pelanggan(customer.getNama_pelanggan());
                customer.setNo_meja(customer.getNo_meja());
                produk.putExtra(EXTRA_CUSTOMER, customer);
                startActivity(produk);
                break;
            case R.id.btn_batal:
                onBackPressed();
                break;

        }
    }


}
