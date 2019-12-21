package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ifcodedeveloper.cakwangcafe.R;

public class OrderOrTransActivity extends AppCompatActivity implements View.OnClickListener {

    CardView pesananaBaru,listPesanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_or_trans);
        pesananaBaru = findViewById(R.id.pesanan_baru);
        pesananaBaru.setOnClickListener(this);
        listPesanan = findViewById(R.id.list_pesanan);
        listPesanan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pesanan_baru:
                Intent pemesanan = new Intent(OrderOrTransActivity.this, OrderActivity.class);
                startActivity(pemesanan);
                break;
            case R.id.list_pesanan:
                Intent list = new Intent(OrderOrTransActivity.this, LoginActivity.class);
                startActivity(list);
                break;
        }

    }
}
