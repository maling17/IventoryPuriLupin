package com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_permintaan_flake {

    @SerializedName("permintaan")
    private List<FlakeItem> permintaan;

    @SerializedName("status")
    private boolean status;

    public List<FlakeItem> getPermintaan() {
        return permintaan;
    }

    public void setPermintaan(List<FlakeItem> permintaan) {
        this.permintaan = permintaan;
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
                "Response_permintaan_flake{" +
                        "permintaan = '" + permintaan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}