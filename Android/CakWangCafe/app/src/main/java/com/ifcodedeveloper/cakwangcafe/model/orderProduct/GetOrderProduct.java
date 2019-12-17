package com.ifcodedeveloper.cakwangcafe.model.orderProduct;

import com.google.gson.annotations.SerializedName;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;

import java.util.ArrayList;

public class GetOrderProduct {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    ArrayList<OrderProduct> listDataOrder;
    @SerializedName("message")
    String message;
    @SerializedName("nama_pelanggan")
    String nama_pelanggan;
    @SerializedName("no_meja")
    String no_meja;
    @SerializedName("sub_total")
    String sub_total;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<OrderProduct> getListDataOrder() {
        return listDataOrder;
    }

    public void setListDataOrder(ArrayList<OrderProduct> listDataOrder) {
        this.listDataOrder = listDataOrder;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }
}
