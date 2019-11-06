package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.adapter.CartAdapter;
import com.ifcodedeveloper.cakwangcafe.adapter.ProductAdapter;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;
import com.ifcodedeveloper.cakwangcafe.model.cart.GetCart;
import com.ifcodedeveloper.cakwangcafe.model.produk.GetProduct;
import com.ifcodedeveloper.cakwangcafe.model.produk.Product;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ArrayList<Cart> cartList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mRecyclerView = findViewById(R.id.rv_cart);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        ShowCart();
    }

    public void ShowCart(){
        Call<GetCart> ItemCall = mApiInterface.getCart();
        ItemCall.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart>
                    response) {
                cartList = response.body().getListDataCart();
                Log.d("Retrofit Get", "Jumlah data Item: " +String.valueOf(cartList.size()));
                mAdapter = new CartAdapter(cartList, mContext);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
