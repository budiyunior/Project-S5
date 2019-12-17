package com.ifcodedeveloper.cakwangcafe.model.product;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetProduct {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    ArrayList<Product> listDataProduk;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Product> getListDataProduk() {
        return listDataProduk;
    }

    public void setListDataProduk(ArrayList<Product> listDataProduk) {
        this.listDataProduk = listDataProduk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
