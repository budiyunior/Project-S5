package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ifcodedeveloper.cakwangcafe.ItemClickSupport;
import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.adapter.ListTransAdapter;
import com.ifcodedeveloper.cakwangcafe.adapter.ProductAdapter;
import com.ifcodedeveloper.cakwangcafe.model.customer.Customer;
import com.ifcodedeveloper.cakwangcafe.model.product.GetProduct;
import com.ifcodedeveloper.cakwangcafe.model.product.Product;
import com.ifcodedeveloper.cakwangcafe.model.transaction.GetTransaction;
import com.ifcodedeveloper.cakwangcafe.model.transaction.Transaction;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTransactionActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_TRANS = "extra_trans";
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private ListTransAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ArrayList<Transaction> transList = new ArrayList<>();
    String date, status;
    Spinner spinner;
    Button btn_sumbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transaction);
        btn_sumbit = findViewById(R.id.btn_submit);
        btn_sumbit.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.rv_listtrans);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        status = "1";
        ShowTransList();
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(ListTransactionActivity.this, DetailTransactionActivity.class);
                intent.putExtra(EXTRA_TRANS,transList.get(position));
                startActivity(intent);
            }
        });
    }

    public void ShowTransList() {

        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Call<GetTransaction> ItemCall = mApiInterface.getTransList(date,"1");
        ItemCall.enqueue(new Callback<GetTransaction>() {
            @Override
            public void onResponse(Call<GetTransaction> call, Response<GetTransaction>
                    response) {
                transList = response.body().getListDataTrans();
                mAdapter = new ListTransAdapter(transList, ListTransactionActivity.this);

                Log.e("Retrofit Get", "Jumlah data Item: " + transList.size());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetTransaction> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                Intent trans = new  Intent(ListTransactionActivity.this, AllTransActivity.class);
                startActivity(trans);
            }

        }

    }
