package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.adapter.ListTransAdapter;
import com.ifcodedeveloper.cakwangcafe.model.login.Wifi;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderOrTransActivity extends AppCompatActivity implements View.OnClickListener {

    CardView pesananaBaru, listPesanan;
    String shift, nama_pengguna,password;
    TextView tv_pegawai, tv_shift,tv_password;
    SharedPreferences sharedPreferences;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Kasir Cakwang Cafe");
        setContentView(R.layout.activity_order_or_trans);
        pesananaBaru = findViewById(R.id.pesanan_baru);
        pesananaBaru.setOnClickListener(this);
        listPesanan = findViewById(R.id.list_pesanan);
        listPesanan.setOnClickListener(this);
        tv_pegawai = findViewById(R.id.tv_nama);
        tv_shift = findViewById(R.id.tv_shift);
        tv_password = findViewById(R.id.tv_password);
        TimeSet();
        sharedPreferences = getSharedPreferences("remember", Context.MODE_PRIVATE);
        nama_pengguna = sharedPreferences.getString("nama_pengguna", "0");
        tv_pegawai.setText(nama_pengguna);
        tv_shift.setText(shift);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        cekWifi();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pesanan_baru:
                Intent pemesanan = new Intent(OrderOrTransActivity.this, OrderActivity.class);
                startActivity(pemesanan);
                break;
            case R.id.list_pesanan:
                Intent list = new Intent(OrderOrTransActivity.this, ListTransactionActivity.class);
                startActivity(list);
                break;
        }

    }

    void TimeSet() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 8 && timeOfDay < 17) {
            shift = "Pagi";
            Toast.makeText(this, "Shift Pagi", Toast.LENGTH_SHORT).show();
        } else if (timeOfDay >= 17) {
            shift = "Sore";
            Toast.makeText(this, "Shift Sore", Toast.LENGTH_SHORT).show();
        } else if (timeOfDay == 0) {
            shift = "Sore";
            Toast.makeText(this, "Shift Sore", Toast.LENGTH_SHORT).show();
        } else {
            shift = "Cafe Tutup";
            Toast.makeText(this, "Cafe Tutup", Toast.LENGTH_SHORT).show();
        }

    }
    
    void cekWifi() {
        Call<Wifi> TransCall = mApiInterface.cekWifi("1");
        TransCall.enqueue(new Callback<Wifi>() {

            @Override
            public void onResponse(Call<Wifi> call, Response<Wifi> response) {
                password = response.body().getPassword();
                tv_password.setText(password);
                Log.e("pass", "onResponse: " + password);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("password_wifi", password);
                editor.apply();
            }

            @Override
            public void onFailure(Call<Wifi> call, Throwable t) {

                Log.e("fail", "onResponse: " + tv_password.toString());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Intent produk = new Intent(OrderOrTransActivity.this, ChangePasswordActivity.class);
            startActivity(produk);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
