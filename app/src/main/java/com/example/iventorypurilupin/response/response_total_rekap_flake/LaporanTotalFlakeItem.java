package com.example.iventorypurilupin.response.response_total_rekap_flake;

import com.google.gson.annotations.SerializedName;

public class LaporanTotalFlakeItem {

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
                "LaporanTotalFlakeItem{" +
                        "flake = '" + flake + '\'' +
                        "}";
    }
}