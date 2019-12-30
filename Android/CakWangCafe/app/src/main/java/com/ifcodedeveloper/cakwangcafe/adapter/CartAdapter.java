package com.ifcodedeveloper.cakwangcafe.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private ArrayList<Cart> cartList;
    private final Context mContext;
    private String sub, subs;
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
    public ArrayList<Cart> getArrayList(){
        return cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_produk.setText(cartList.get(position).getNama_produk());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int harga = Integer.parseInt(cartList.get(position).getHarga_satuan());
        holder.tv_harga.setText(formatRupiah.format(harga));
        holder.tv_jumlah.setText(cartList.get(position).getJumlah());
        int subHarga = Integer.parseInt(cartList.get(position).getSub_total());
        holder.tv_subtotal.setText(formatRupiah.format(subHarga));
        String urlGambar = "http://cakwangcafe.com/assets/img/foto_produk/" + cartList.get(position).getGambar();
        Picasso.get().load(urlGambar).resize(70,70).centerCrop().into(holder.img_menu);
        sub = cartList.get(position).getSub_total();
        subs = cartList.get(position).getId_produk();
        Log.d("id", subs);
        Log.d("total", sub);

        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), DeleteCartActivity.class);
                mIntent.putExtra("id_produk", cartList.get(position).getId_produk());
                v.getContext().startActivity(mIntent);

            }
        });

    }
    public ArrayList<Cart> grandTotal() {
        for (int i = 0; i < cartList.size(); i++) {
            cartList.get(i).getId_produk();
            cartList.get(i).getNama_produk();
        }
        return cartList;
    }

    @Override
    public int getItemCount() {
//        return (produkList == null) ? 0 : produkList.size();
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_harga, tv_produk, tv_jumlah, tv_subtotal;
        ImageView img_menu;
        Button btn_hapus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_produk = itemView.findViewById(R.id.tv_produk);
            tv_harga = itemView.findViewById(R.id.tv_harga);
            tv_jumlah = itemView.findViewById(R.id.tv_jumlah);
            tv_subtotal = itemView.findViewById(R.id.tv_subtotal);
            img_menu = itemView.findViewById(R.id.img_produk);
            btn_hapus = itemView.findViewById(R.id.btn_hapuss);
        }
    }
}
