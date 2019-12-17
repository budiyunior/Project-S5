package com.ifcodedeveloper.cakwangcafe.model.orderProduct;

import com.google.gson.annotations.SerializedName;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;

public class PostPutDelOrder {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    OrderProduct mOrder;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderProduct getmOrder() {
        return mOrder;
    }

    public void setmOrder(OrderProduct mOrder) {
        this.mOrder = mOrder;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
