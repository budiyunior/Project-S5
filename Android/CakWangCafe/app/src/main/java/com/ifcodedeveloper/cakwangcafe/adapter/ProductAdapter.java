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
import com.ifcodedeveloper.cakwangcafe.model.product.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private ArrayList<Product> productList;
    private final Context mContext;
    public static final String EXTRA_PRODUCT = "extra_product";
//    public ArrayList<Product> getListProduk() {
//        return produkList;
//    }
//
//    public void setListProduk(ArrayList<Product> listProduk) {
//        this.produkList = listProduk;
//    }

    public ProductAdapter(ArrayList<Product> ProdukList, Context context) {
        productList = ProdukList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_produk.setText(productList.get(position).getNama_produk());
        holder.tv_harga.setText(productList.get(position).getHarga_satuan());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(view.getContext(), OrderProductActivity.class);
//                mIntent.putExtra(EXTRA_PRODUCT,productList.get(position));
//
//                view.getContext().startActivity(mIntent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return (productList == null) ? 0 : productList.size();

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
