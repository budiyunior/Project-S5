package com.ifcodedeveloper.cakwangcafe.model.login;

import com.google.gson.annotations.SerializedName;

public class Wifi {
    @SerializedName("status")
    Boolean status;

    @SerializedName("message")
    String message;

    @SerializedName("id_wifi")
    String id_wifi;

    @SerializedName("password")
    String password;

    @SerializedName("ssid")
    String ssid;

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

    public String getId_wifi() {
        return id_wifi;
    }

    public void setId_wifi(String id_wifi) {
        this.id_wifi = id_wifi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
}
