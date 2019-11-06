package com.ifcodedeveloper.cakwangcafe.model.cart;

import com.google.gson.annotations.SerializedName;
import com.ifcodedeveloper.cakwangcafe.model.produk.Product;

import java.util.ArrayList;

public class GetCart {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    ArrayList<Cart> listDataCart;
    @SerializedName("message")
    String message;

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
}
