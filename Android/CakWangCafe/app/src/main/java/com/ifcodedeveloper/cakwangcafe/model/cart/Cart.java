package com.ifcodedeveloper.cakwangcafe.model.cart;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("id_keranjang")
    private String id_keranjang;
    @SerializedName("id_produk")
    private String id_produk;
    @SerializedName("nama_produk")
    private String nama_produk;
    @SerializedName("jumlah")
    private String jumlah;
    @SerializedName("harga_satuan")
    private String harga_satuan;
    @SerializedName("subtotal")
    private String subtotal;
    @SerializedName("nama_pelanggan")
    private String nama_pelanggan;
    @SerializedName("no_meja")
    private String no_meja;

    public Cart(String id_keranjang, String id_produk, String nama_produk, String jumlah, String harga_satuan, String subtotal, String nama_pelanggan, String no_meja) {
        this.id_keranjang = id_keranjang;
        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.jumlah = jumlah;
        this.harga_satuan = harga_satuan;
        this.subtotal = subtotal;
        this.nama_pelanggan = nama_pelanggan;
        this.no_meja = no_meja;
    }

    public String getId_keranjang() {
        return id_keranjang;
    }

    public void setId_keranjang(String id_keranjang) {
        this.id_keranjang = id_keranjang;
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

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(String harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
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
}
