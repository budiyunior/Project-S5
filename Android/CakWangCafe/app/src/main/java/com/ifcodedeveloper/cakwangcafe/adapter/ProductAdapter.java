package com.ifcodedeveloper.cakwangcafe.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.product.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.ifcodedeveloper.cakwangcafe.rest.ApiClient.BASE_URL;

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
//        if(mContext != null) {
//            Glide.with(mContext)
//                    .load("http://192.168.1.17/project_s5/website/assets/img/foto_produk/"+productList.get(position).getGambar())
////                    .apply(new RequestOptions().override(70, 70).transform(new RoundedCorners(5)))
//                    .dontAnimate().into(holder.img_menu);
//        }
        String urlGambar = "http://192.168.1.17/project_s5/website/assets/img/foto_produk/" + productList.get(position).getGambar();
        Picasso.get().load(urlGambar).resize(70,70).centerCrop().into(holder.img_menu);
        holder.tv_produk.setText(productList.get(position).getNama_produk());
        holder.tv_harga.setText(productList.get(position).getHarga_satuan());
        Log.e("gambar", "gambar: "+productList.get(position).getGambar() );
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
