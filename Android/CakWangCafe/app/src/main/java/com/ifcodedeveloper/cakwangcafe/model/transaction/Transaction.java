package com.ifcodedeveloper.cakwangcafe.model.transaction;

import com.google.gson.annotations.SerializedName;

public class Transaction {
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

    public Transaction(String id_transaksi, String nama_pelanggan, String no_meja, String jam, String tanggal, String total_harga, String shift) {
        this.id_transaksi = id_transaksi;
        this.nama_pelanggan = nama_pelanggan;
        this.no_meja = no_meja;
        this.jam = jam;
        this.tanggal = tanggal;
        this.total_harga = total_harga;
        this.shift = shift;
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
}
