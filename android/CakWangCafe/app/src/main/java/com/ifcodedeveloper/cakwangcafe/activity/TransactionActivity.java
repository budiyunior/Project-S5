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
import com.ifcodedeveloper.cakwangcafe.adapter.OrderProductAdapter;
import com.ifcodedeveloper.cakwangcafe.adapter.TransAdapter;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;
import com.ifcodedeveloper.cakwangcafe.model.cart.GetCart;
import com.ifcodedeveloper.cakwangcafe.model.cart.PostPutDelCart;
import com.ifcodedeveloper.cakwangcafe.model.customer.Customer;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.GetOrderProduct;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.OrderProduct;
import com.ifcodedeveloper.cakwangcafe.model.transaction.GetTransaction;
import com.ifcodedeveloper.cakwangcafe.model.transaction.PostTransaction;
import com.ifcodedeveloper.cakwangcafe.model.transaction.TotalHarga;
import com.ifcodedeveloper.cakwangcafe.model.transaction.Transaction;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {

    TextView tv_total_harga, tv_pelanggan, tv_meja, tv_jam, tv_tanggal;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ApiInterface mApiInterface;
    Context mContext;
    ArrayList<OrderProduct> orderList = new ArrayList<>();
    Customer customer = new Customer();
    public static final String EXTRA_CUSTOMER = "extra_customer";
    SharedPreferences sharedPreferences;
    String nama_pelanggan, no_meja, id_transaksi;
    Integer total_harga;
    String pelanggan, meja, jam, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        tv_total_harga = findViewById(R.id.tv_total_harga);
        tv_jam = findViewById(R.id.tv_jam);
        tv_pelanggan = findViewById(R.id.tv_pelanggan);
        tv_meja = findViewById(R.id.tv_nomeja);
        tv_tanggal = findViewById(R.id.tv_tanggal);
        mRecyclerView = findViewById(R.id.rv_trans);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
//        customer = getIntent().getParcelableExtra(EXTRA_CUSTOMER);


        sharedPreferences = getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
        nama_pelanggan = sharedPreferences.getString("nama_pelanggan", "0");
        no_meja = sharedPreferences.getString("no_meja", "0");
        id_transaksi = sharedPreferences.getString("id_transaksi", "0");
//        GrandTotal();
//        tv_total_harga.setText(String.valueOf(GrandTotal()));


        ShowCart();
        TotalHarga();
        GetTrans();
    }

    void GetTrans() {
        Call<Transaction> TransCall = mApiInterface.getTrans(id_transaksi);
        TransCall.enqueue(new Callback<Transaction>() {

            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                pelanggan = response.body().getNama_pelanggan();
                meja = response.body().getNo_meja();
                jam = response.body().getJam();
                tanggal = response.body().getTanggal();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date testDate = null;
                try {
                    testDate = sdf.parse(tanggal);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
                String newFormat = formatter.format(testDate);
                tv_pelanggan.setText(pelanggan);
                tv_meja.setText("Meja No."+meja);
                tv_jam.setText(jam);
                tv_tanggal.setText(newFormat);
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {

            }
        });
    }

    public void ShowCart() {
        Call<GetOrderProduct> ItemCall = mApiInterface.getOder(id_transaksi);
        ItemCall.enqueue(new Callback<GetOrderProduct>() {
            @Override
            public void onResponse(Call<GetOrderProduct> call, Response<GetOrderProduct>
                    response) {
                orderList = response.body().getListDataOrder();

                Log.d("Retrofit Get", "Jumlah data Item: " + String.valueOf(orderList.size()));
                mAdapter = new TransAdapter(orderList, mContext);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetOrderProduct> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    void TotalHarga() {
        final Call<TotalHarga> getTotal = mApiInterface.getTotal(id_transaksi);
        getTotal.enqueue(new Callback<TotalHarga>() {
            @Override
            public void onResponse(Call<TotalHarga> call, Response<TotalHarga> response) {
                total_harga = response.body().getTotal_harga();
                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                tv_total_harga.setText(formatRupiah.format(total_harga));
            }

            @Override
            public void onFailure(Call<TotalHarga> call, Throwable t) {
                Log.e("gagal", "gagal" + t);
            }
        });
    }
//    public int GrandTotal() {
//        int totalPrice = 10;
//        for (int i = 0; i < cartList.size(); i++) {
//            String sub = cartList.get(i).getSub_total();
//            int subt = Integer.parseInt(sub);
//            totalPrice = totalPrice + subt;
//        }
////        Log.e("total pay : ", String.valueOf(totalPrice));
////        tv_total_harga.setText(String.valueOf(totalPrice));
//        return totalPrice;
//    }

}
