package com.ifcodedeveloper.cakwangcafe.model.customer;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer implements Parcelable {
    private String nama_pelanggan;
    private String no_meja;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama_pelanggan);
        dest.writeString(this.no_meja);
    }

    public Customer() {
    }

    protected Customer(Parcel in) {
        this.nama_pelanggan = in.readString();
        this.no_meja = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel source) {
            return new Customer(source);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
}
