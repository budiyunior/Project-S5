package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
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

public class ListTransactionActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

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
    ProgressBar progressBar;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transaction);
        btn_sumbit = findViewById(R.id.btn_submit);
        btn_sumbit.setOnClickListener(this);
        mSwipeRefreshLayout = findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        progressBar = findViewById(R.id.progress_bar);
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
                intent.putExtra(EXTRA_TRANS, transList.get(position));
                startActivity(intent);
            }
        });
onRefresh();
    }

    public void ShowTransList() {
        mSwipeRefreshLayout.setRefreshing(true);
        progressBar.setVisibility(View.VISIBLE);
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Call<GetTransaction> ItemCall = mApiInterface.getTransList(date, "1");
        ItemCall.enqueue(new Callback<GetTransaction>() {
            @Override
            public void onResponse(Call<GetTransaction> call, Response<GetTransaction>
                    response) {
                progressBar.setVisibility(View.GONE);
                transList = response.body().getListDataTrans();
                mAdapter = new ListTransAdapter(transList, ListTransactionActivity.this);

                Log.e("Retrofit Get", "Jumlah data Item: " + transList.size());
                mRecyclerView.setAdapter(mAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetTransaction> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                Intent trans = new Intent(ListTransactionActivity.this, AllTransActivity.class);
                startActivity(trans);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ListTransactionActivity.this, OrderOrTransActivity.class));
        finish();

    }

    @Override
    public void onRefresh() {
        ShowTransList();
    }
}
