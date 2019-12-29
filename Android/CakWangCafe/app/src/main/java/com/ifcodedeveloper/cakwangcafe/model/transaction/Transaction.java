package com.ifcodedeveloper.cakwangcafe.model.transaction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Transaction implements Parcelable {
    @SerializedName("id_transaksi")
    private String id_transaksi;
    @SerializedName("nama_pelanggan")
    private String nama_pelanggan;
    @SerializedName("no_meja")
    private String no_meja;
    @SerializedName("jam")
    private String jam;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("total_harga")
    private String total_harga;
    @SerializedName("shift")
    private String shift;
    @SerializedName("status_pesanan")
    private String status_pesanan;

    public Transaction(String id_transaksi, String nama_pelanggan, String no_meja, String jam, String tanggal, String total_harga, String shift, String status_pesanan) {
        this.id_transaksi = id_transaksi;
        this.nama_pelanggan = nama_pelanggan;
        this.no_meja = no_meja;
        this.jam = jam;
        this.tanggal = tanggal;
        this.total_harga = total_harga;
        this.shift = shift;
        this.status_pesanan = status_pesanan;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getNo_meja() {
        return no_meja;
    }

    public void setNo_meja(String no_meja) {
        this.no_meja = no_meja;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(String total_harga) {
        this.total_harga = total_harga;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getStatus_pesanan() {
        return status_pesanan;
    }

    public void setStatus_pesanan(String status_pesanan) {
        this.status_pesanan = status_pesanan;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_transaksi);
        dest.writeString(this.nama_pelanggan);
        dest.writeString(this.no_meja);
        dest.writeString(this.jam);
        dest.writeString(this.tanggal);
        dest.writeString(this.total_harga);
        dest.writeString(this.shift);
        dest.writeString(this.status_pesanan);
    }

    protected Transaction(Parcel in) {
        this.id_transaksi = in.readString();
        this.nama_pelanggan = in.readString();
        this.no_meja = in.readString();
        this.jam = in.readString();
        this.tanggal = in.readString();
        this.total_harga = in.readString();
        this.shift = in.readString();
        this.status_pesanan = in.readString();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel source) {
            return new Transaction(source);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
