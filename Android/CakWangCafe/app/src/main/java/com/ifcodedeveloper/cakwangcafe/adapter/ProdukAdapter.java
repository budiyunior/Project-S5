package com.ifcodedeveloper.cakwangcafe.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ifcodedeveloper.cakwangcafe.model.produk.Produk;

import java.util.ArrayList;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.MyViewHolder> {

    private ArrayList<Produk> produkList;
    private final Context mContext;

    public ArrayList<Produk> getListProduk() {
        return produkList;
    }

    public void setListProduk(ArrayList<Produk> listProduk) {
        this.produkList = listProduk;
    }

    public ProdukAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ProdukAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
