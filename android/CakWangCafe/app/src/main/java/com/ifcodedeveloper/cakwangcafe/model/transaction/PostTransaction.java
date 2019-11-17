package com.ifcodedeveloper.cakwangcafe.model.transaction;

import com.google.gson.annotations.SerializedName;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;

public class PostTransaction {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Transaction mTransaction;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Transaction getmTransaction() {
        return mTransaction;
    }

    public void setmTransaction(Transaction mTransaction) {
        this.mTransaction = mTransaction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
