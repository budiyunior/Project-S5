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
import com.ifcodedeveloper.cakwangcafe.model.transaction.Transaction;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AllTransAdapter extends RecyclerView.Adapter<AllTransAdapter.MyViewHolder> {
    private ArrayList<Transaction> transList;
    private Context mContext;

    public AllTransAdapter(ArrayList<Transaction> TransList, Context context) {
        transList = TransList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public AllTransAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_trans_list, parent, false);
        return new AllTransAdapter.MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTransAdapter.MyViewHolder holder, int position) {
        holder.tv_pelanggan.setText(transList.get(position).getNama_pelanggan());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int harga = Integer.parseInt(transList.get(position).getTotal_harga());
        holder.tv_harga.setText(formatRupiah.format(harga));
        holder.tv_jam.setText(transList.get(position).getJam());
        holder.tv_meja.setText(transList.get(position).getNo_meja());
        String shift = transList.get(position).getShift();
        String status = transList.get(position).getStatus_pesanan();
        String shiftNow = null;
//        if (shift.equals("1")){
//            shiftNow = "Pagi";
//            holder.tv_shift.setVisibility(View.VISIBLE);
//        } else if (shift.equals("2")){
//            shiftNow = "Sore";
//        }
        if (status.equals("1")){
            holder.tv_shift.setImageResource(R.drawable.ic_clear_black_24dp);
        } else if (status.equals("2")){
            holder.tv_shift.setImageResource(R.drawable.ic_check_black_24dp);
        }
//        holder.tv_shift.setText(shiftNow);

    }

    @Override
    public int getItemCount() {
        return transList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_pelanggan, tv_meja, tv_jam, tv_harga;
        ImageView tv_shift;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pelanggan = itemView.findViewById(R.id.tv_pelanggan);
            tv_meja = itemView.findViewById(R.id.tv_meja);
            tv_harga = itemView.findViewById(R.id.tv_harga);
            tv_jam = itemView.findViewById(R.id.tv_jam);
            tv_shift = itemView.findViewById(R.id.tv_shift);
        }
    }
}

