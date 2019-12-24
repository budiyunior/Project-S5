package com.ifcodedeveloper.cakwangcafe.model.transaction;

import com.google.gson.annotations.SerializedName;
import com.ifcodedeveloper.cakwangcafe.model.product.Product;

import java.util.ArrayList;

public class GetTransaction {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    ArrayList<Transaction> listDataTrans;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Transaction> getListDataTrans() {
        return listDataTrans;
    }

    public void setListDataTrans(ArrayList<Transaction> listDataTrans) {
        this.listDataTrans = listDataTrans;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
