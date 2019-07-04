package com.example.iventorypurilupin.response.response_stok_awal;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_stok_awal {

    @SerializedName("barang")
    private List<Stok_awal_Item> barang;

    @SerializedName("status")
    private boolean status;

    public List<Stok_awal_Item> getBarang() {
        return barang;
    }

    public void setBarang(List<Stok_awal_Item> barang) {
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
                "Response_stok_awal{" +
                        "barang = '" + barang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}