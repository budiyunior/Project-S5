package com.ifcodedeveloper.cakwangcafe.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.produk.Produk;

import java.util.ArrayList;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.MyViewHolder> {

    private ArrayList<Produk> produkList;
    private final Context mContext;

//    public ArrayList<Produk> getListProduk() {
//        return produkList;
//    }
//
//    public void setListProduk(ArrayList<Produk> listProduk) {
//        this.produkList = listProduk;
//    }

    public ProdukAdapter(ArrayList<Produk> ProdukList,Context context) {
        produkList = ProdukList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_produk.setText(produkList.get(position).getNama_produk());
        holder.tv_harga.setText(produkList.get(position).getHarga_satuan());

    }

    @Override
    public int getItemCount() {
//        return (produkList == null) ? 0 : produkList.size();
        return produkList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_harga, tv_produk, tv_jumlah;
        ImageView img_menu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_produk = itemView.findViewById(R.id.tv_produk);
            tv_harga = itemView.findViewById(R.id.tv_harga);
            tv_jumlah = itemView.findViewById(R.id.tv_jumlah);
            img_menu = itemView.findViewById(R.id.img_produk);

        }
    }
}
