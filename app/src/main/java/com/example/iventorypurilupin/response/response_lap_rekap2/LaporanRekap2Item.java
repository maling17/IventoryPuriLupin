package com.example.iventorypurilupin.response.response_lap_rekap2;

import com.google.gson.annotations.SerializedName;


public class LaporanRekap2Item {

    @SerializedName("tgl_permintaan")
    private String tglPermintaan;

    public String getTglPermintaan() {
        return tglPermintaan;
    }

    public void setTglPermintaan(String tglPermintaan) {
        this.tglPermintaan = tglPermintaan;
    }

    @Override
    public String toString() {
        return
                "LaporanRekap2Item{" +
                        "tgl_permintaan = '" + tglPermintaan + '\'' +
                        "}";
    }
}