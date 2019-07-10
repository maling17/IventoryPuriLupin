package com.example.iventorypurilupin.response.response_kacang.response_flake;

import com.google.gson.annotations.SerializedName;


public class BarangFlakeItem {

    @SerializedName("stok")
    private String stok;

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return
                "BarangFlakeItem{" +
                        "stok = '" + stok + '\'' +
                        "}";
    }
}