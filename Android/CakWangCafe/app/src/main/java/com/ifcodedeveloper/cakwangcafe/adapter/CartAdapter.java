package com.ifcodedeveloper.cakwangcafe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.activity.DeleteCartActivity;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;
import com.ifcodedeveloper.cakwangcafe.model.produk.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
    private ArrayList<Cart> cartList;
    private final Context mContext;

//    public ArrayList<Product> getListProduk() {
//        return produkList;
//    }
//
//    public void setListProduk(ArrayList<Product> listProduk) {
//        this.produkList = listProduk;
//    }

    public CartAdapter(ArrayList<Cart> CartList, Context context) {
        cartList = CartList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_produk.setText(cartList.get(position).getNama_produk());
        holder.tv_harga.setText(cartList.get(position).getHarga_satuan());
        holder.tv_jumlah.setText(cartList.get(position).getJumlah());
//        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mIntent = new Intent(v.getContext(), DeleteCartActivity.class);
//                mIntent.putExtra("id_desain", cartList.get(position).getId_desain());
//                v.getContext().startActivity(mIntent);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
//        return (produkList == null) ? 0 : produkList.size();
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_harga, tv_produk, tv_jumlah;
        ImageView img_menu;
        Button btn_hapus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_produk = itemView.findViewById(R.id.tv_produk);
            tv_harga = itemView.findViewById(R.id.tv_harga);
            tv_jumlah = itemView.findViewById(R.id.tv_jumlah);
            img_menu = itemView.findViewById(R.id.img_produk);
            btn_hapus = itemView.findViewById(R.id.btn_hapus);

        }
    }
}
