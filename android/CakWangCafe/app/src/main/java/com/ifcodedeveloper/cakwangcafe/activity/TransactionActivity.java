package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.adapter.CartAdapter;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;
import com.ifcodedeveloper.cakwangcafe.model.cart.GetCart;
import com.ifcodedeveloper.cakwangcafe.model.cart.PostPutDelCart;
import com.ifcodedeveloper.cakwangcafe.model.customer.Customer;
import com.ifcodedeveloper.cakwangcafe.model.transaction.PostTransaction;
import com.ifcodedeveloper.cakwangcafe.model.transaction.TotalHarga;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {

    TextView tv_total_harga;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ApiInterface mApiInterface;
    Context mContext;
    ArrayList<Cart> cartList = new ArrayList<>();
    Customer customer = new Customer();
    public static final String EXTRA_CUSTOMER = "extra_customer";
    SharedPreferences sharedPreferences;
    String total_harga,nama_pelanggan,no_meja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        tv_total_harga = findViewById(R.id.tv_total_harga);

        mRecyclerView = findViewById(R.id.rv_trans);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
//        customer = getIntent().getParcelableExtra(EXTRA_CUSTOMER);


        sharedPreferences = getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
        nama_pelanggan = sharedPreferences.getString("nama_pelanggan", "0");
        no_meja = sharedPreferences.getString("no_meja", "0");
//        GrandTotal();
        tv_total_harga.setText(String.valueOf(GrandTotal()));
    }

    void TotalHarga() {
        final Call<TotalHarga> getTotal = mApiInterface.getTotal(nama_pelanggan,no_meja);
        getTotal.enqueue(new Callback<TotalHarga>() {
            @Override
            public void onResponse(Call<TotalHarga> call, Response<TotalHarga> response) {
                total_harga = response.body().getTotal_harga();
                tv_total_harga.setText(total_harga);
            }

            @Override
            public void onFailure(Call<TotalHarga> call, Throwable t) {
                Log.e("gagal", "gagal" + t);
            }
        });
    }
    public int GrandTotal() {
        int totalPrice = 10;
        for (int i = 0; i < cartList.size(); i++) {
            String sub = cartList.get(i).getSub_total();
            int subt = Integer.parseInt(sub);
            totalPrice = totalPrice + subt;
        }
//        Log.e("total pay : ", String.valueOf(totalPrice));
//        tv_total_harga.setText(String.valueOf(totalPrice));
        return totalPrice;
    }
//    void PostTrans() {
//        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        LocalTime time = LocalTime.now();
//
//        Call<PostTransaction> postTrans = mApiInterface.postTrans(nama_pelanggan, no_meja,t,date,"2000","A");
//        postTrans.enqueue(new Callback<PostPutDelCart>() {
//            @Override
//            public void onResponse(Call<PostPutDelCart> call, Response<PostPutDelCart> response) {
//                Toast.makeText(getApplicationContext(), "Berhasil ditambahkan", Toast.LENGTH_LONG).show();
//                Log.e("Berhasil", "berhasil desain cart" + sub_total + product.getHarga_satuan() + jumlah_item.getNumber());
//                finish();
//            }
//
//            @Override
//            public void onFailure(Call<PostPutDelCart> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}
