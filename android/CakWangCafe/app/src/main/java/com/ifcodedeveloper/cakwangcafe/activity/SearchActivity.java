package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ifcodedeveloper.cakwangcafe.ItemClickSupport;
import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.adapter.ProductAdapter;
import com.ifcodedeveloper.cakwangcafe.adapter.TransAdapter;
import com.ifcodedeveloper.cakwangcafe.model.cart.GetCart;
import com.ifcodedeveloper.cakwangcafe.model.customer.Customer;
import com.ifcodedeveloper.cakwangcafe.model.product.GetProduct;
import com.ifcodedeveloper.cakwangcafe.model.product.Product;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ApiInterface mApiInterface;
    public static final String EXTRA_PRODUCT = "extra_product";
    Context mContext;
    String nama_produk;
    ArrayList<Product> productList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mRecyclerView = findViewById(R.id.rv_menu);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Bundle bundle = getIntent().getExtras();
        nama_produk = bundle.getString("nama_produk");
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Pencarian untuk "+ nama_produk);
        }
        GetSearch();
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(SearchActivity.this, OrderProductActivity.class);
                intent.putExtra(EXTRA_PRODUCT, productList.get(position));
                startActivity(intent);
            }
        });
    }

    void GetSearch() {
        Call<GetProduct> ItemCall = mApiInterface.getSearch(nama_produk);
        ItemCall.enqueue(new Callback<GetProduct>() {
            @Override
            public void onResponse(Call<GetProduct> call, Response<GetProduct>
                    response) {
                productList = response.body().getListDataProduk();
                Log.d("Retrofit Get", "Jumlah data Item: " + String.valueOf(productList.size()));
                mAdapter = new ProductAdapter(productList, mContext);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<GetProduct> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
