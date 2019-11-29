package com.example.orderlist.model.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ujang Wahyu on 04/01/2018.
 */
public class ResponseLogin {
    @SerializedName("status")
    Boolean status;

    @SerializedName("message")
    String message;

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    @SerializedName("id_pengguna")
    String id_pengguna;

    @SerializedName("id_akses")
    String id_akses;

    @SerializedName("nama_pengguna")
    String nama_pengguna;

    @SerializedName("shift")
    String shift;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getId_akses() {
        return id_akses;
    }

    public void setId_akses(String id_akses) {
        this.id_akses = id_akses;
    }

    public String getNama_pengguna() {
        return nama_pengguna;
    }

    public void setNama_pengguna(String nama_pengguna) {
        this.nama_pengguna = nama_pengguna;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
