package com.example.iventorypurilupin.response.response_form_sj;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_form_sj {

    @SerializedName("permintaan")
    private List<BarangItem> barang;

    @SerializedName("status")
    private boolean status;

    public List<BarangItem> getBarang() {
        return barang;
    }

    public void setBarang(List<BarangItem> barang) {
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
                "Response_form_sj{" +
                        "permintaan = '" + barang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}