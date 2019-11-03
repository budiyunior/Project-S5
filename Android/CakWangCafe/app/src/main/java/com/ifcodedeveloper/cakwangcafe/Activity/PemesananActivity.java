package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.produk.Produk;

public class PemesananActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_pilih_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        btn_pilih_menu = findViewById(R.id.btn_pilih_menu);
        btn_pilih_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pilih_menu:
                Intent produk = new Intent(PemesananActivity.this, ProdukActivity.class);
                startActivity(produk);
                break;
        }
    }
}
