package com.example.iventorypurilupin.response.response_kacang.response_split;

import com.google.gson.annotations.SerializedName;

public class BarangSplitItem {

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
                "BarangSplitItem{" +
                        "stok = '" + stok + '\'' +
                        "}";
    }
}