package com.example.iventorypurilupin.response.response_ks_flake;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_ks_flake {

    @SerializedName("barang")
    private List<Ks_flakeItem> barang;

    @SerializedName("status")
    private boolean status;

    public List<Ks_flakeItem> getBarang() {
        return barang;
    }

    public void setBarang(List<Ks_flakeItem> barang) {
        this.barang = barang;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "Response_ks_flake{" +
                        "barang = '" + barang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}