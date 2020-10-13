package com.ifcodedeveloper.cakwangcafe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.activity.DeleteOrderActivity;
import com.ifcodedeveloper.cakwangcafe.model.transaction.Transaction;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ListTransAdapter extends RecyclerView.Adapter<ListTransAdapter.MyViewHolder>{
    private ArrayList<Transaction> transList;
    private Context mContext;

    public ListTransAdapter(ArrayList<Transaction> TransList, Context context) {
        transList = TransList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ListTransAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list, parent, false);
        return new ListTransAdapter.MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTransAdapter.MyViewHolder holder, final int position) {
        holder.tv_pelanggan.setText(transList.get(position).getNama_pelanggan());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int harga = Integer.parseInt(transList.get(position).getTotal_harga());
        holder.tv_harga.setText(formatRupiah.format(harga));
        holder.tv_jam.setText(transList.get(position).getJam());
        holder.tv_meja.setText(transList.get(position).getNo_meja());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                Toast.makeText(mContext, "Berhasil LOng", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, DeleteOrderActivity.class);
                intent.putExtra("id_transaksi",transList.get(position).getId_transaksi());
                intent.putExtra("nama_pelanggan",transList.get(position).getNama_pelanggan());
                v.getContext().startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return transList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_pelanggan,tv_meja,tv_jam,tv_harga;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pelanggan = itemView.findViewById(R.id.tv_pelanggan);
            tv_meja = itemView.findViewById(R.id.tv_meja);
            tv_harga= itemView.findViewById(R.id.tv_harga);
            tv_jam = itemView.findViewById(R.id.tv_jam);
        }
    }
}
