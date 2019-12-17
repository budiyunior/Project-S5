package com.example.orderlist.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.orderlist.R;
import com.example.orderlist.model.login.ResponseLogin;
import com.example.orderlist.rest.ApiClient;
import com.example.orderlist.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_login, btn_test;
    TextView tv_email, tv_password;
    SharedPreferences sharedPreferences;
    ApiInterface mApiInterface;
    String id_pengguna, email, nama_pengguna, password, shift, id_akses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_email = findViewById(R.id.tv_email);
        tv_password = findViewById(R.id.tv_password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(this);


        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = LoginActivity.this.getSharedPreferences("remember", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "0");
    }

    void login() {
//        pDialog = new ProgressDialog(this);
//        //  pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setMessage("Loading");
//        pDialog.setCancelable(false);
//        // pDialog.setIndeterminate(false);
//        pDialog.show();

        Call<ResponseLogin> user = mApiInterface.login(tv_email.getText().toString(), tv_password.getText().toString());
//        Call<ResponseLogin> user=ApiClient.getApi().auth(txt_username.getText().toString(),txt_password.getText().toString());
        user.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
//                pDialog.dismiss();
                id_pengguna = response.body().getId_pengguna();
                email = response.body().getEmail();
                nama_pengguna = response.body().getNama_pengguna();
                id_akses = response.body().getId_akses();
                password = response.body().getPassword();
                shift = response.body().getShift();

                if (TextUtils.isEmpty(id_pengguna)) {
                    Toast.makeText(LoginActivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Berhasil  Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("id_pengguna", id_pengguna);
                    editor.putString("nama_pengguna", nama_pengguna);
                    editor.putString("email", email);
                    editor.putString("id_akses", id_akses);
                    editor.putString("password", password);
                    editor.putString("shift", shift);
                    editor.apply();
                    startActivity(intent);
                    Log.e("Berhasil", "berhasil" + id_pengguna + nama_pengguna + email + id_akses + shift + password);

                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
//                pDialog.dismiss();
                Log.e("gagal", "gagal" + t);
                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                Intent pemesanan = new Intent(LoginActivity.this, ListOrderActivity.class);
                startActivity(pemesanan);
                break;
            case R.id.btn_login:
                login();
        }

    }

}
