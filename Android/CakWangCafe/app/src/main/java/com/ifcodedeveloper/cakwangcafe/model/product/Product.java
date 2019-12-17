package com.ifcodedeveloper.cakwangcafe.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    @SerializedName("id_produk")
    private String id_produk;
    @SerializedName("nama_produk")
    private String nama_produk;
    @SerializedName("harga_satuan")
    private String harga_satuan;
    @SerializedName("gambar")
    private String gambar;
    @SerializedName("keterangan")
    private String keterangan;

    public Product(String id_produk, String nama_produk, String harga_satuan, String gambar, String keterangan) {
        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.harga_satuan = harga_satuan;
        this.gambar = gambar;
        this.keterangan = keterangan;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(String harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_produk);
        dest.writeString(this.nama_produk);
        dest.writeString(this.harga_satuan);
        dest.writeString(this.gambar);
        dest.writeString(this.keterangan);
    }

    protected Product(Parcel in) {
        this.id_produk = in.readString();
        this.nama_produk = in.readString();
        this.harga_satuan = in.readString();
        this.gambar = in.readString();
        this.keterangan = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
