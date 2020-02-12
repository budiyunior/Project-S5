package com.ifcodedeveloper.cakwangcafe.model.transaction;

import com.google.gson.annotations.SerializedName;

public class Belanja {
    @SerializedName("barang")
    private String barang;
    @SerializedName("ambil")
    private String ambil;
    @SerializedName("kembali")
    private String kembali;
    @SerializedName("total_belanja")
    private String total_belanja;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("shift")
    private String shift;

    public Belanja() {
    }

    public Belanja(String barang, String ambil, String kembali, String total_belanja, String keterangan, String tanggal, String shift) {
        this.barang = barang;
        this.ambil = ambil;
        this.kembali = kembali;
        this.total_belanja = total_belanja;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
        this.shift = shift;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }


    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getAmbil() {
        return ambil;
    }

    public void setAmbil(String ambil) {
        this.ambil = ambil;
    }

    public String getKembali() {
        return kembali;
    }

    public void setKembali(String kembali) {
        this.kembali = kembali;
    }

    public String getTotal_belanja() {
        return total_belanja;
    }

    public void setTotal_belanja(String total_belanja) {
        this.total_belanja = total_belanja;
    }
}
