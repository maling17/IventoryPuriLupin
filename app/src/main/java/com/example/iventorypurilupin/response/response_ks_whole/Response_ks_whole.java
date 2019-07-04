package com.example.iventorypurilupin.response.response_ks_whole;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_ks_whole {

    @SerializedName("barang")
    private List<Ks_WholeItem> barang;

    @SerializedName("status")
    private boolean status;

    public List<Ks_WholeItem> getBarang() {
        return barang;
    }

    public void setBarang(List<Ks_WholeItem> barang) {
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
                "Response_ks_whole{" +
                        "barang = '" + barang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}