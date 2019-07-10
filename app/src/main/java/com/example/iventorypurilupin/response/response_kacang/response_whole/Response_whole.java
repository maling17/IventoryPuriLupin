package com.example.iventorypurilupin.response.response_kacang.response_whole;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_whole {

    @SerializedName("barang")
    private List<WholeItem> barang;

    @SerializedName("status")
    private boolean status;

    public List<WholeItem> getBarang() {
        return barang;
    }

    public void setBarang(List<WholeItem> barang) {
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
                "Response_whole{" +
                        "barang = '" + barang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}