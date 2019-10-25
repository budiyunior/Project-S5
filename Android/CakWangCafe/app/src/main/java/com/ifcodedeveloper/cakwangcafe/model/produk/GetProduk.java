package com.ifcodedeveloper.cakwangcafe.model.produk;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProduk {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Produk> listDataProduk;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Produk> getListDataProduk() {
        return listDataProduk;
    }

    public void setListDataProduk(List<Produk> listDataProduk) {
        this.listDataProduk = listDataProduk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
