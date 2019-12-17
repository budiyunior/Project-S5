package com.ifcodedeveloper.cakwangcafe.model.transaction;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetTotal {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    ArrayList<TotalHarga> totalHarga;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<TotalHarga> getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(ArrayList<TotalHarga> totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
