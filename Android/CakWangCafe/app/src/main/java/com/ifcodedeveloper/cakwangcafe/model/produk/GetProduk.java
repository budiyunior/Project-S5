package com.ifcodedeveloper.cakwangcafe.model.produk;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetProduk {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    ArrayList<Produk> listDataProduk;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Produk> getListDataProduk() {
        return listDataProduk;
    }

    public void setListDataProduk(ArrayList<Produk> listDataProduk) {
        this.listDataProduk = listDataProduk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
