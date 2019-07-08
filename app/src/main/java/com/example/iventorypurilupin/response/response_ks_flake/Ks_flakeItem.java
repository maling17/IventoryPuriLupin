package com.example.iventorypurilupin.response.response_ks_flake;

import com.google.gson.annotations.SerializedName;


public class Ks_flakeItem {

    @SerializedName("Flake_Olah")
    private String flakeOlah;

    @SerializedName("Flake_akhir")
    private String flakeAkhir;

    @SerializedName("Flake_Awal")
    private String flakeAwal;

    public String getFlakeOlah() {
        return flakeOlah;
    }

    public void setFlakeOlah(String flakeOlah) {
        this.flakeOlah = flakeOlah;
    }

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
                "Ks_flakeItem{" +
                        "flake_Olah = '" + flakeOlah + '\'' +
                        ",flake_akhir = '" + flakeAkhir + '\'' +
                        ",flake_Awal = '" + flakeAwal + '\'' +
                        "}";
    }
}