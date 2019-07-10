package com.example.iventorypurilupin.response.response_kacang.response_flake;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_barang_flake {

    @SerializedName("barang")
    private List<BarangFlakeItem> barang;

    @SerializedName("status")
    private boolean status;

    public List<BarangFlakeItem> getBarang() {
        return barang;
    }

    public void setBarang(List<BarangFlakeItem> barang) {
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
                "Response_barang_flake{" +
                        "barang = '" + barang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}