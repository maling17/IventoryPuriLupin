package com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang;

import com.google.gson.annotations.SerializedName;

public class FlakeItem {

    @SerializedName("flake")
    private String flake;

    public String getFlake() {
        return flake;
    }

    public void setFlake(String flake) {
        this.flake = flake;
    }

    @Override
    public String toString() {
        return
                "FlakeItem{" +
                        "flake = '" + flake + '\'' +
                        "}";
    }
}