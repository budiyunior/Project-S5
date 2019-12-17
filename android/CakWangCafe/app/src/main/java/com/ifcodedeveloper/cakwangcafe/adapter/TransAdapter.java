package com.ifcodedeveloper.cakwangcafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.OrderProduct;
import com.ifcodedeveloper.cakwangcafe.model.transaction.Transaction;

import java.util.ArrayList;

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.MyViewHolder> {
    private ArrayList<OrderProduct> orderList;
    private Context mContext;

    public TransAdapter(ArrayList<OrderProduct> OrderList, Context context) {
        orderList = OrderList;
        this.mContext = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trans_cart_list, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_produk.setText(orderList.get(position).getNama_produk());
        holder.tv_harga.setText(orderList.get(position).getHarga_satuan());
        holder.tv_jumlah.setText(orderList.get(position).getJumlah());
        holder.tv_subtotal.setText(orderList.get(position).getSub_total());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_harga, tv_produk, tv_jumlah, tv_subtotal;
        ImageView img_menu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_produk = itemView.findViewById(R.id.tv_produk);
            tv_harga = itemView.findViewById(R.id.tv_harga);
            tv_jumlah = itemView.findViewById(R.id.tv_jumlah);
            tv_subtotal = itemView.findViewById(R.id.tv_subtotal);
            img_menu = itemView.findViewById(R.id.img_produk);
        }
    }
}