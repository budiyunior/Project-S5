package com.ifcodedeveloper.cakwangcafe.model.cart;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetCart {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    ArrayList<Cart> listDataCart;
    @SerializedName("message")
    String message;
    @SerializedName("nama_pelanggan")
    String nama_pelanggan;
    @SerializedName("no_meja")
    String no_meja;
    @SerializedName("sub_total")
    String sub_total;
    @SerializedName("id_transaksi")
    String id_transaksi;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Cart> getListDataCart() {
        return listDataCart;
    }

    public void setListDataCart(ArrayList<Cart> listDataCart) {
        this.listDataCart = listDataCart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }


}
