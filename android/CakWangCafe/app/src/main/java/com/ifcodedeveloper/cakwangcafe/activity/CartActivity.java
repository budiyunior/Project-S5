package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ifcodedeveloper.cakwangcafe.ItemClickSupport;
import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.adapter.CartAdapter;
import com.ifcodedeveloper.cakwangcafe.adapter.ProductAdapter;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;
import com.ifcodedeveloper.cakwangcafe.model.cart.GetCart;
import com.ifcodedeveloper.cakwangcafe.model.customer.Customer;
import com.ifcodedeveloper.cakwangcafe.model.produk.GetProduct;
import com.ifcodedeveloper.cakwangcafe.model.produk.Product;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCheckout;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private CartAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    //    private CartAdapter cAdapter;
    ArrayList<Cart> cartList = new ArrayList<>();
    Customer customer = new Customer();
    public static final String EXTRA_CUSTOMER = "extra_customer";
    SharedPreferences sharedPreferences;
    String nama_pelanggan, no_meja,sub;
    CartAdapter sAdapter;
    public static boolean add = true;
//    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnCheckout = findViewById(R.id.btn_checkout);
        btnCheckout.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.rv_cart);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        sharedPreferences = getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
        nama_pelanggan = sharedPreferences.getString("nama_pelanggan", "0");
        no_meja = sharedPreferences.getString("no_meja", "0");
//        customer = getIntent().getParcelableExtra(EXTRA_CUSTOMER);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                Customer customer = new Customer();
//                customer.setNama_pelanggan(customer.getNama_pelanggan());
//                customer.setNo_meja(customer.getNo_meja());
                Intent intent = new Intent(CartActivity.this, DeleteCartActivity.class);
//                intent.putExtra(EXTRA_CUSTOMER,customer);
                startActivity(intent);
            }
        });
//        int totalPrice = 0;
//        for (int i = 0; i<cartList.size(); i++)
//        {
//            totalPrice += cartList.get(i).getSubtotal();
//        }
//        int subtotal = mAdapter.grandTotal();

//        grandTotal();
        int price = cartList.size();
        int sum = 0;
        for (int i = 0; i < price; i++) {

            sum = sum + Integer.parseInt(sub);

        }

        Log.d("Retrofit Get",  String.valueOf(sub));
        ShowCart();
    }

    public void ShowCart() {
        Call<GetCart> ItemCall = mApiInterface.getCart(nama_pelanggan, no_meja);
        ItemCall.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart>
                    response) {
                cartList = response.body().getListDataCart();

                Log.d("Retrofit Get", "Jumlah data Item: " + String.valueOf(cartList.size()));
                mAdapter = new CartAdapter(cartList, mContext);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_checkout:
                Intent checkout = new Intent(CartActivity.this, TransactionActivity.class);
                startActivity(checkout);
                break;
        }
    }

    public int grandTotal() {
        int totalPrice = 0;
        for (int i = 0; i < cartList.size(); i++) {
            String sub = cartList.get(i).getSub_total();
            int subt = Integer.parseInt(sub);
            totalPrice += subt;
        }
        Log.e("total bayar : ", String.valueOf(totalPrice));
        return totalPrice;
    }
}
