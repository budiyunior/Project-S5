package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.login.Wifi;
import com.ifcodedeveloper.cakwangcafe.model.transaction.Belanja;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BelanjaActivity extends AppCompatActivity implements View.OnClickListener {

    String shift, date;
    int total;
    EditText tvbarang, tvambil, tvkembali, tvketerangan;
    Button btnSimpan, btnSelesai;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belanja);

        tvbarang = findViewById(R.id.barang);
        tvambil = findViewById(R.id.ambil);
        tvkembali = findViewById(R.id.kembali);
        tvketerangan = findViewById(R.id.keterangan);

        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(this);
        btnSelesai = findViewById(R.id.btn_selesai);
        btnSelesai.setOnClickListener(this);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        TimeSet();
    }


    void updateWifi() {
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        total = Integer.parseInt(tvambil.getText().toString()) - Integer.parseInt(tvkembali.getText().toString());
        Call<Belanja> TransCall = mApiInterface.belanja(tvbarang.getText().toString(),
                tvambil.getText().toString(), tvkembali.getText().toString(), Integer.toString(total), tvketerangan.getText().toString(), date, shift);

        TransCall.enqueue(new Callback<Belanja>() {
            @Override
            public void onResponse(Call<Belanja> call, Response<Belanja> response) {
//                Log.e("pass", "onResponse: " + tv_cpassword);

                tvbarang.setText("");
                tvambil.setText("");
                tvkembali.setText("");
                tvketerangan.setText("");
                Toast.makeText(BelanjaActivity.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Belanja> call, Throwable t) {
                Log.e("fail", "onResponse: gagal");
            }
        });
    }


    void TimeSet() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 8 && timeOfDay < 17) {
            shift = "1";

//            Toast.makeText(this, "Shift Pagi", Toast.LENGTH_SHORT).show();
        } else if (timeOfDay >= 17) {
            shift = "2";

//            Toast.makeText(this, "Shift Sore", Toast.LENGTH_SHORT).show();
        } else if (timeOfDay == 0) {
            shift = "2";

//            Toast.makeText(this, "Shift Sore", Toast.LENGTH_SHORT).show();
        } else {
            shift = "3";

//            Toast.makeText(this, "Cafe Tutup", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simpan:

                String sBarang = tvbarang.getText().toString();
                String sAmbil = tvambil.getText().toString();
                String sKembali = tvkembali.getText().toString();
                String sKeterangan = tvketerangan.getText().toString();
//                int iAmbil = Integer.parseInt(sAmbil);
//                int iKembali = Integer.parseInt(sKembali);
                if (sBarang.matches("") && sAmbil.matches("") && sKembali.matches("") && sKeterangan.matches("")) {
                    Toast.makeText(this, "Semua Data Wajib Diisi", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Integer.parseInt(sKembali) > Integer.parseInt(sAmbil)){
                    Toast.makeText(this, "Uang Kembali Tidak Boleh Lebih Dari Ambil", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    updateWifi();
                }
            case R.id.btn_selesai:
                Intent selesai = new Intent(BelanjaActivity.this,OrderOrTransActivity.class);
                startActivity(selesai);
        }
    }
}
