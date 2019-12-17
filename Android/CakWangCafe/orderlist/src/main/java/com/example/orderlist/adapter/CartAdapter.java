package com.example.orderlist.adapter;

import android.content.Context;
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
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;

import java.util.ArrayList;

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
        holder.tv_subtotal.setText(cartList.get(position).getSub_total());
        sub = cartList.get(position).getSub_total();
        subs = cartList.get(position).getId_produk();
//        Log.d("total", subs);
//        int totalPrice = 0;
//        for (int i = 0; i < cartList.size(); i++) {
////            String sub = cartList.get(position).getSub_total();
////            int subt = Integer.parseInt(sub);
//
//            totalPrice += Integer.parseInt(sub);
//            Log.e("total pay : ", String.valueOf(totalPrice));
//        }

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

    public void setData(ArrayList<Cart> items) {
        this.cartList = items;
        notifyDataSetChanged();
    }

    public int grandTotal() {
        int totalPrice = 0;
        for (int i = 0; i < cartList.size(); i++) {
            int subt = Integer.parseInt(sub);
            totalPrice = totalPrice + Integer.parseInt(sub);
        }
        Log.d("total", sub);
        return totalPrice;
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
            btn_hapus = itemView.findViewById(R.id.btn_hapus);
        }
    }
}
