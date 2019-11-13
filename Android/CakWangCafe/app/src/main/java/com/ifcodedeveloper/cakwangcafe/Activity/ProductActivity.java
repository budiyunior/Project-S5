package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.ItemClickSupport;
import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.adapter.ProductAdapter;
import com.ifcodedeveloper.cakwangcafe.model.customer.Customer;
import com.ifcodedeveloper.cakwangcafe.model.produk.GetProduct;
import com.ifcodedeveloper.cakwangcafe.model.produk.Product;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    TextView tv_nama,tv_no_meja;
    public static final String EXTRA_CUSTOMER = "extra_customer";
    public static final String EXTRA_PRODUCT = "extra_product";
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ArrayList<Product> productList = new ArrayList<>();
    Customer customer = new Customer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        tv_nama = findViewById(R.id.tv_nama);
        tv_no_meja = findViewById(R.id.tv_meja);
        mRecyclerView = findViewById(R.id.rv_produk);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        customer = getIntent().getParcelableExtra(EXTRA_CUSTOMER);
        tv_nama.setText(customer.getNama_pelanggan());
        tv_no_meja.setText(customer.getNo_meja());

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Customer customer = new Customer();
                customer.setNama_pelanggan(tv_nama.getText().toString());
                customer.setNo_meja(tv_no_meja.getText().toString());
                Intent intent = new Intent(ProductActivity.this,OrderProductActivity.class);
                intent.putExtra(EXTRA_PRODUCT, productList.get(position));
                intent.putExtra(EXTRA_CUSTOMER,customer);
                startActivity(intent);
            }
        });

        ShowProduk();
    }

    public void ShowProduk() {
        Call<GetProduct> ItemCall = mApiInterface.getProduk();
        ItemCall.enqueue(new Callback<GetProduct>() {
            @Override
            public void onResponse(Call<GetProduct> call, Response<GetProduct>
                    response) {
                productList = response.body().getListDataProduk();
                Log.d("Retrofit Get", "Jumlah data Item: " +String.valueOf(productList.size()));
                mAdapter = new ProductAdapter(productList, mContext);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetProduct> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
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
            Intent produk = new Intent(ProductActivity.this, CartActivity.class);
            startActivity(produk);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}