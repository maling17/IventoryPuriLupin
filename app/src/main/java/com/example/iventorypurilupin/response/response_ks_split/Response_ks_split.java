package com.example.iventorypurilupin.response.response_ks_split;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_ks_split {

    @SerializedName("barang")
    private List<Ks_SplitItem> barang;

    @SerializedName("status")
    private boolean status;

    public List<Ks_SplitItem> getBarang() {
        return barang;
    }

    public void setBarang(List<Ks_SplitItem> barang) {
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
                "Response_ks_split{" +
                        "barang = '" + barang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}