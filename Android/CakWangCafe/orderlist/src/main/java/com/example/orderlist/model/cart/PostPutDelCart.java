package com.example.orderlist.model.cart;

import com.google.gson.annotations.SerializedName;

public class PostPutDelCart {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Cart mCart;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cart getmCart() {
        return mCart;
    }

    public void setmCart(Cart mCart) {
        this.mCart = mCart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
