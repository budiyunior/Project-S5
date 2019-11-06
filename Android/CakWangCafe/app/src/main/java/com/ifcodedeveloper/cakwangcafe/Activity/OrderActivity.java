package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.customer.Customer;
import com.ifcodedeveloper.cakwangcafe.model.produk.Product;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_pilih_menu;
    TextView tv_nama,tv_meja;
    public static final String EXTRA_CUSTOMER = "extra_customer";
    Customer customer = new Customer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        btn_pilih_menu = findViewById(R.id.btn_pilih_menu);
        btn_pilih_menu.setOnClickListener(this);
        tv_nama = findViewById(R.id.tv_nama);
        tv_meja = findViewById(R.id.tv_meja);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pilih_menu:
                customer.setNama_pelanggan(tv_nama.getText().toString());
                customer.setNo_meja(tv_meja.getText().toString());
                Intent produk = new Intent(OrderActivity.this, ProductActivity.class);
                produk.putExtra(EXTRA_CUSTOMER,customer);
                startActivity(produk);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.cart) {
            Intent produk = new Intent(OrderActivity.this, CartActivity.class);
            startActivity(produk);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
