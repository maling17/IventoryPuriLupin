package com.example.iventorypurilupin.response.response_detail_lap_stok_flake;

import com.google.gson.annotations.SerializedName;


public class DetailFlakeItem {

    @SerializedName("Flake_akhir")
    private String flakeAkhir;

    @SerializedName("Flake_Awal")
    private String flakeAwal;

    public String getFlakeAkhir() {
        return flakeAkhir;
    }

    public void setFlakeAkhir(String flakeAkhir) {
        this.flakeAkhir = flakeAkhir;
    }

    public String getFlakeAwal() {
        return flakeAwal;
    }

    public void setFlakeAwal(String flakeAwal) {
        this.flakeAwal = flakeAwal;
    }

    @Override
    public String toString() {
        return
                "DetailFlakeItem{" +
                        "flake_akhir = '" + flakeAkhir + '\'' +
                        ",flake_Awal = '" + flakeAwal + '\'' +
                        "}";
    }
}