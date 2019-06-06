package com.example.iventorypurilupin.response.response_permintaan;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponsePermintaan {

    @SerializedName("barang")
    private List<AntrianItem> barang;

    @SerializedName("status")
    private boolean status;

    public List<AntrianItem> getBarang() {
        return barang;
    }

    public void setBarang(List<AntrianItem> barang) {
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
                "ResponsePermintaan{" +
                        "barang = '" + barang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}