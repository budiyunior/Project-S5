package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.ifcodedeveloper.cakwangcafe.ItemClickSupport;
import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.adapter.AllTransAdapter;
import com.ifcodedeveloper.cakwangcafe.adapter.ListTransAdapter;
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

public class AllTransActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private AllTransAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String date;
    ArrayList<Transaction> transList = new ArrayList<>();
    ProgressBar progressBar;

    SwipeRefreshLayout mSwipeRefreshLayout;
    public static final String EXTRA_TRANS = "extra_trans";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trans);
        mSwipeRefreshLayout = findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = findViewById(R.id.rv_listtrans);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        progressBar = findViewById(R.id.progress_bar);
        ShowTransList();
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(AllTransActivity.this, DetailTransactionActivity.class);
                intent.putExtra(EXTRA_TRANS,transList.get(position));
                startActivity(intent);
            }
        });
        ShowTransList();
        onRefresh();
    }

    public void ShowTransList() {
        mSwipeRefreshLayout.setRefreshing(true);
        progressBar.setVisibility(View.VISIBLE);
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Call<GetTransaction> ItemCall = mApiInterface.getAllTrans(date);
        ItemCall.enqueue(new Callback<GetTransaction>() {
            @Override
            public void onResponse(Call<GetTransaction> call, Response<GetTransaction>
                    response) {
                transList = response.body().getListDataTrans();
                progressBar.setVisibility(View.GONE);
                mAdapter = new AllTransAdapter(transList, AllTransActivity.this);
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
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AllTransActivity.this, ListTransactionActivity.class));
        finish();

    }

    @Override
    public void onRefresh() {
     ShowTransList();
    }
}
