package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_total_harga, tv_pelanggan, tv_meja, tv_jam, tv_tanggal;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ApiInterface mApiInterface;
    Context mContext;
    ArrayList<Cart> orderList = new ArrayList<>();
    Customer customer = new Customer();
    public static final String EXTRA_CUSTOMER = "extra_customer";
    SharedPreferences sharedPreferences;
    String nama_pelanggan, no_meja, id_transaksi;
    Integer total_harga;
    String pelanggan, meja, jam, tanggal, total,date,dateY,dateNeed,shift;
    Button btn_cetak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        btn_cetak = findViewById(R.id.btn_cetak);
        btn_cetak.setOnClickListener(this);
        tv_total_harga = findViewById(R.id.tv_total_harga);
        tv_jam = findViewById(R.id.tv_jam);
        tv_pelanggan = findViewById(R.id.tv_pelanggan);
        tv_meja = findViewById(R.id.tv_nomeja);
        tv_tanggal = findViewById(R.id.tv_tanggal);
        mRecyclerView = findViewById(R.id.rv_trans);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mApiInterface = ApiClient.getClient().create(ApiInterface.class);


        sharedPreferences = getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
        nama_pelanggan = sharedPreferences.getString("nama_pelanggan", "0");
        no_meja = sharedPreferences.getString("no_meja", "0");
        id_transaksi = sharedPreferences.getString("id_transaksi", "0");

        Date dateS = yesterday();
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        dateY = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(dateS);
        Log.e("tanggal", "onCreate: "+date+" "+dateY );
        TimeSet();
        ShowCart();
        GetTrans();
    }
    void TimeSet() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 8 && timeOfDay < 17) {
            shift = "1";
            dateNeed = date;
//            Toast.makeText(this, "Shift Pagi", Toast.LENGTH_SHORT).show();
        } else if (timeOfDay >= 17) {
            shift = "2";
            dateNeed = date;
//            Toast.makeText(this, "Shift Sore", Toast.LENGTH_SHORT).show();
        } else if (timeOfDay == 0) {
            shift = "2";
            dateNeed = dateY;
//            Toast.makeText(this, "Shift Sore", Toast.LENGTH_SHORT).show();
        } else {
            shift = "3";
            dateNeed = date;
//            Toast.makeText(this, "Cafe Tutup", Toast.LENGTH_SHORT).show();
        }

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
                total = response.body().getTotal_harga();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date testDate = null;
                try {
                    testDate = sdf.parse(tanggal);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
                String newFormat = formatter.format(testDate);
                tv_pelanggan.setText(pelanggan);
                tv_meja.setText("Meja No." + meja);
                tv_jam.setText(jam);
                tv_tanggal.setText(newFormat);
                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                int totalHarga = Integer.parseInt(total);
                tv_total_harga.setText(formatRupiah.format(totalHarga));
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {

            }
        });
    }
    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
    public void ShowCart() {
        Call<GetCart> ItemCall = mApiInterface.getCart(id_transaksi);
        ItemCall.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart>
                    response) {
                orderList = response.body().getListDataCart();

                Log.d("Retrofit Get", "Jumlah data Item: " + String.valueOf(orderList.size()));
                mAdapter = new TransAdapter(orderList, mContext);
                mRecyclerView.setAdapter(mAdapter);
                for(int i = 0; i < orderList.size(); i++){
                    String nama = orderList.get(i).getNama_produk();
                    String harga = orderList.get(i).getHarga_satuan();
                    String jumlah = orderList.get(i).getJumlah();
                    String total = orderList.get(i).getSub_total();
                    Log.e("test", "onResponse: "+nama+harga+jumlah+total);
                }
            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TransactionActivity.this, OrderActivity.class));
        finish();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cetak:
                Bundle bundle = new  Bundle();
                bundle.putString("id_transaksi",id_transaksi);
                Intent cetak = new Intent(TransactionActivity.this,PrintActivity.class);
                cetak.putExtras(bundle);
                startActivity(cetak);
                break;

        }
    }


}
