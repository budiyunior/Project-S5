package com.ifcodedeveloper.cakwangcafe.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ifcodedeveloper.cakwangcafe.ItemClickSupport;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    Button btnCheckout;
    TextView tv_total,tv_harga;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    CartAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    //    private CartAdapter cAdapter;
    ArrayList<Cart> cartList = new ArrayList<>();
    Customer customer = new Customer();
    public static final String EXTRA_CART = "extra_cart";
    SharedPreferences sharedPreferences;
    String nama_pelanggan, no_meja, sub, total, shift, date, time, id_transaksi,dateY,dateNeed;
    Integer total_harga;
    CartAdapter sAdapter;
    public static CartActivity ca;
    public static boolean add = true;
    //    int sum = 0;
    private Calendar fromTime;
    private Calendar toTime;
    private Calendar currentTime;
    boolean inRange;
    String waktu;
    ImageView img_empty_cart;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        tv_total = findViewById(R.id.tv_total_harga);
        tv_harga = findViewById(R.id.total_harga);
        img_empty_cart = findViewById(R.id.img_empty_cart);
        btnCheckout = findViewById(R.id.btn_checkout);
        btnCheckout.setOnClickListener(this);
        btnCheckout = findViewById(R.id.btn_checkout);
        btnCheckout.setOnClickListener(this);
        mSwipeRefreshLayout = findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = findViewById(R.id.rv_cart);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        sharedPreferences = getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
        nama_pelanggan = sharedPreferences.getString("nama_pelanggan", "0");
        no_meja = sharedPreferences.getString("no_meja", "0");
        id_transaksi = sharedPreferences.getString("id_transaksi", "0");

        sAdapter = new CartAdapter(cartList, mContext);
        tv_total.setText(String.valueOf(sAdapter.grandTotal()));
//tv_total.setText(String.valueOf(grandTotal()));

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(CartActivity.this, UpdateCartActivity.class);
                intent.putExtra(EXTRA_CART, cartList.get(position));
                startActivity(intent);
            }
        });
        Date dateS = yesterday();
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        dateY = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(dateS);
        Log.e("tanggal", "onCreate: "+date+" "+dateY );
        ShowCart();
        TotalHarga();
        TimeSet();


    }
    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
    @Override
    public void onRefresh() {
        ShowCart();
        TotalHarga();
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

    //    void Check() {
//        if (cartList == null){
//            Log.e("kosong", "Data Kososng");
//        }else {
//            Log.e("Valid", "Data tidak Kosong");
//            TotalHarga();
//        }
//    }
//    void DeleteCart() {
//        Call<PostPutDelCart> deleteKontak = mApiInterface.deleteCart(id_transaksi);
//        deleteKontak.enqueue(new Callback<PostPutDelCart>() {
//            @Override
//            public void onResponse(Call<PostPutDelCart> call, Response<PostPutDelCart> response) {
//                Log.e("hapus Cart", "sukses hapus");
//            }
//
//            @Override
//            public void onFailure(Call<PostPutDelCart> call, Throwable t) {
////                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

    public void ShowCart() {
        mSwipeRefreshLayout.setRefreshing(true);
        Call<GetCart> ItemCall = mApiInterface.getCart(id_transaksi);
        ItemCall.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart>
                    response) {
                cartList = response.body().getListDataCart();


                Log.d("Retrofit Get", "Jumlah data Item: " + String.valueOf(cartList.size()));
                mAdapter = new CartAdapter(cartList, mContext);
                mRecyclerView.setAdapter(mAdapter);

//                for(int i = 0; i < cartList.size(); i++){
//                    String nama = cartList.get(i).getNama_produk();
//                    String harga = cartList.get(i).getHarga_satuan();
//                    String jumlah = cartList.get(i).getJumlah();
//                    String total = cartList.get(i).getSub_total();
//                    Log.e("test", "onResponse: "+nama+harga+jumlah+total);
//                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_checkout:
                if (tv_total.getText() != "Keranjang Kosong") {
                    Intent checkout = new Intent(CartActivity.this, TransactionActivity.class);
                    PostTrans();
//                    DeleteCart();
                    startActivity(checkout);
                } else {
                    Toast.makeText(CartActivity.this, "Keranjang Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    void TotalHarga() {
        mSwipeRefreshLayout.setRefreshing(true);
        final Call<TotalHarga> getTotal = mApiInterface.getTotal(id_transaksi);
        getTotal.enqueue(new Callback<TotalHarga>() {
            @Override
            public void onResponse(Call<TotalHarga> call, Response<TotalHarga> response) {
                total_harga = response.body().getTotal_harga();

                Log.e("total", "onResponse: " + total_harga);
                if (total_harga == null) {
                    tv_total.setText("Keranjang Kosong");
                    img_empty_cart.setVisibility(View.VISIBLE);
                    tv_harga.setVisibility(View.GONE);
                } else {
                    Locale localeID = new Locale("in", "ID");
                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                    tv_total.setText(formatRupiah.format(total_harga));
                    tv_harga.setVisibility(View.VISIBLE);
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<TotalHarga> call, Throwable t) {
                Log.e("gagal", "gagal" + t);
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    void PostTrans() {
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        time = new SimpleDateFormat("kk:mm:ss", Locale.getDefault()).format(new Date());

        Call<PostTransaction> postTrans = mApiInterface.postTrans(id_transaksi, nama_pelanggan,
                no_meja, time, dateNeed, total_harga.toString(), shift, "1");
        postTrans.enqueue(new Callback<PostTransaction>() {
            @Override
            public void onResponse(Call<PostTransaction> call, Response<PostTransaction> response) {
//                Toast.makeText(getApplicationContext(), "Berhasil ditambahkan", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<PostTransaction> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CartActivity.this, ProductActivity.class));
        finish();

    }


//    public boolean checkTime(String time) {
//        try {
//            String[] times = time.split("-");
//            String[] from = times[0].split(":");
//            String[] until = times[1].split(":");
//
//            fromTime = Calendar.getInstance();
//            fromTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(from[0]));
//            fromTime.set(Calendar.MINUTE, Integer.valueOf(from[1]));
//
//            toTime = Calendar.getInstance();
//            toTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(until[0]));
//            toTime.set(Calendar.MINUTE, Integer.valueOf(until[1]));
//
//            currentTime = Calendar.getInstance();
//            currentTime.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
//            currentTime.set(Calendar.MINUTE, Calendar.MINUTE);
//            if (currentTime.after(fromTime) && currentTime.before(toTime)) {
//                return true;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//        return false;
//    }

//    void Shift() {
//        if (checkTime("01:00-14:00")) {
//            waktu = "a";
//            inRange = true;
//        } else {
//            waktu = "b";
//            inRange = false;
//        }
//        Log.e("shift", "Shift: " + inRange);
//        Log.e("waktu", "waktu " + waktu);
////        if (inRange= true){
////
////        }
//
//    }
}
