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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.Spref;
import com.ifcodedeveloper.cakwangcafe.adapter.ListTransAdapter;
import com.ifcodedeveloper.cakwangcafe.model.login.Wifi;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderOrTransActivity extends AppCompatActivity implements View.OnClickListener {

    CardView pesananaBaru, listPesanan,belanja;
    String shift, nama_pengguna,password,id_login;
    TextView tv_pegawai, tv_shift,tv_password,logout;
    SharedPreferences sharedPreferences,sharedPreferences2;
    ApiInterface mApiInterface;

    Spref spref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Kasir Cakwang Cafe");
        setContentView(R.layout.activity_order_or_trans);

        pesananaBaru = findViewById(R.id.pesanan_baru);
        pesananaBaru.setOnClickListener(this);
        listPesanan = findViewById(R.id.list_pesanan);
        listPesanan.setOnClickListener(this);
        belanja = findViewById(R.id.belanja);
        belanja.setOnClickListener(this);
        tv_pegawai = findViewById(R.id.tv_nama);
        tv_shift = findViewById(R.id.tv_shift);
        tv_password = findViewById(R.id.tv_password);
        logout = findViewById(R.id.tv_logout);
        logout.setOnClickListener(this);
        TimeSet();
        spref = new Spref(OrderOrTransActivity.this);
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
//                if (shift.equals("Cafe Tutup")){
//                    Toast.makeText(getApplicationContext(), "Cafe Tutup, Tidak Dapat Melakukan Pelayanan", Toast.LENGTH_LONG).show();
//                }else {
                    Intent pemesanan = new Intent(OrderOrTransActivity.this, OrderActivity.class);
                    startActivity(pemesanan);
//                }
                break;
            case R.id.list_pesanan:
//                if (shift.equals("Cafe Tutup")){
//                    Toast.makeText(getApplicationContext(), "Cafe Tutup, Tidak Dapat Melakukan Pelayanan", Toast.LENGTH_LONG).show();
//                }else {
                    Intent list = new Intent(OrderOrTransActivity.this, ListTransactionActivity.class);
                    startActivity(list);
//                }
                break;
            case R.id.tv_logout:
                spref.saveSPBoolean(Spref.SP_Sukses_Login, false);
                startActivity(new Intent(OrderOrTransActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
//                Intent logout = new Intent(OrderOrTransActivity.this,LoginActivity.class);
//                startActivity(logout);
                break;
            case R.id.belanja:
                if (shift.equals("Cafe Tutup")){
                    Toast.makeText(getApplicationContext(), "Cafe Tutup, Tidak Dapat Melakukan Pelayanan", Toast.LENGTH_LONG).show();
                }else {
                    Intent belanja = new Intent(OrderOrTransActivity.this, BelanjaActivity.class);
                    startActivity(belanja);
                }
                break;
        }

    }

    void TimeSet() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 8 && timeOfDay < 17) {
            shift = "Pagi";
        } else if (timeOfDay >= 17) {
            shift = "Sore";
        } else if (timeOfDay == 0) {
            shift = "Sore";
        } else {
            shift = "Cafe Tutup";

//            pesananaBaru.setEnabled(false);
//            listPesanan.setEnabled(false);
//            belanja.setEnabled(false);
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
