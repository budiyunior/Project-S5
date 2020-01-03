package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.login.Wifi;
import com.ifcodedeveloper.cakwangcafe.model.transaction.Transaction;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_cpassword;
    Button btn_konfirmasi, btn_batal;
    ApiInterface mApiInterface;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        tv_cpassword = findViewById(R.id.tv_cpassword);
        btn_konfirmasi = findViewById(R.id.btn_konfirmasi);
        btn_konfirmasi.setOnClickListener(this);
        btn_batal = findViewById(R.id.btn_batal);
        btn_batal.setOnClickListener(this);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        cekWifi();
    }

    void cekWifi() {
        Call<Wifi> TransCall = mApiInterface.cekWifi("1");
        TransCall.enqueue(new Callback<Wifi>() {

            @Override
            public void onResponse(Call<Wifi> call, Response<Wifi> response) {
                password = response.body().getPassword();
                tv_cpassword.setText(password);
                Log.e("pass", "onResponse: " + password);
            }

            @Override
            public void onFailure(Call<Wifi> call, Throwable t) {

                Log.e("fail", "onResponse: " + tv_cpassword.toString());
            }
        });
    }

    void updateWifi() {
        Call<Wifi> TransCall = mApiInterface.updateWifi(tv_cpassword.getText().toString());
        TransCall.enqueue(new Callback<Wifi>() {

            @Override
            public void onResponse(Call<Wifi> call, Response<Wifi> response) {
                Log.e("pass", "onResponse: " + tv_cpassword);
            }

            @Override
            public void onFailure(Call<Wifi> call, Throwable t) {
                Log.e("fail", "onResponse: " + tv_cpassword);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_konfirmasi:
                if (tv_cpassword.getText().toString().length() < 8) {
                    Toast.makeText(this, "Paswword haru lebih dari 8 karakter", Toast.LENGTH_SHORT).show();
                } else {
                    updateWifi();
                    Intent wifi = new Intent(ChangePasswordActivity.this, OrderOrTransActivity.class);
                    startActivity(wifi);
                }


        }
    }
}
