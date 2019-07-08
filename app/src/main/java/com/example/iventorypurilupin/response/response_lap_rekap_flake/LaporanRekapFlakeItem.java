package com.example.iventorypurilupin.response.response_lap_rekap_flake;

import com.google.gson.annotations.SerializedName;


public class LaporanRekapFlakeItem {

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
                "LaporanRekapFlakeItem{" +
                        "flake = '" + flake + '\'' +
                        "}";
    }
}