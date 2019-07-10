package com.example.iventorypurilupin.response.response_kacang.response_split;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_split {

    @SerializedName("barang")
    private List<BarangSplitItem> barang;

    @SerializedName("status")
    private boolean status;

    public List<BarangSplitItem> getBarang() {
        return barang;
    }

    public void setBarang(List<BarangSplitItem> barang) {
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
                "Response_split{" +
                        "barang = '" + barang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}