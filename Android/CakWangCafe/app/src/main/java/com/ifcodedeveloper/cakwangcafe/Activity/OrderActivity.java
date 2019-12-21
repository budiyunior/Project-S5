package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.customer.Customer;

import java.util.Date;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_pilih_menu;
    TextView tv_nama,tv_meja;
    public static final String EXTRA_CUSTOMER = "extra_customer";
    Customer customer = new Customer();
    SharedPreferences sharedPreferences;
    String id_customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        btn_pilih_menu = findViewById(R.id.btn_pilih_menu);
        btn_pilih_menu.setOnClickListener(this);
        tv_nama = findViewById(R.id.tv_nama);
        tv_meja = findViewById(R.id.tv_meja);


        sharedPreferences = OrderActivity.this.getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pilih_menu:
                String sUsername = tv_nama.getText().toString();
                String sPassword = tv_meja.getText().toString();
                if (sUsername.matches("")) {
                    Toast.makeText(this, "Masukan Nama Pelanggan Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (sPassword.matches("")){
                    Toast.makeText(this, "Masukan No Meja Dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Pelanggan();
                    Log.e("customer", "onClick: "+id_customer );
//                customer.setNama_pelanggan(tv_nama.getText().toString());
//                customer.setNo_meja(tv_meja.getText().toString());
                    Intent produk = new Intent(OrderActivity.this, ProductActivity.class);
//                produk.putExtra(EXTRA_CUSTOMER,customer);
                    startActivity(produk);
                }

                break;
        }
    }
    void Pelanggan(){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        id_customer= String.valueOf(((new Date().getTime() / 1000L) % Integer.MAX_VALUE));
        editor.putString("id_transaksi",id_customer);
        editor.putString("nama_pelanggan",tv_nama.getText().toString());
        editor.putString("no_meja",tv_meja.getText().toString());
        editor.apply();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.cart, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.cart) {
//            Intent produk = new Intent(OrderActivity.this, CartActivity.class);
//            startActivity(produk);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
