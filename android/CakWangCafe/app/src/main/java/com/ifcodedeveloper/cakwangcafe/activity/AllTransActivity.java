package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class AllTransActivity extends AppCompatActivity {
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private AllTransAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String date;
    ArrayList<Transaction> transList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trans);

        mRecyclerView = findViewById(R.id.rv_listtrans);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        ShowTransList();
    }

    public void ShowTransList() {

        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Call<GetTransaction> ItemCall = mApiInterface.getAllTrans(date);
        ItemCall.enqueue(new Callback<GetTransaction>() {
            @Override
            public void onResponse(Call<GetTransaction> call, Response<GetTransaction>
                    response) {
                transList = response.body().getListDataTrans();
                mAdapter = new AllTransAdapter(transList, AllTransActivity.this);
                Log.e("Retrofit Get", "Jumlah data Item: " + transList.size());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetTransaction> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
