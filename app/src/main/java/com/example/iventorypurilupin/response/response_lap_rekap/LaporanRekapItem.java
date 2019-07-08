package com.example.iventorypurilupin.response.response_lap_rekap;

import com.google.gson.annotations.SerializedName;


public class LaporanRekapItem {

    @SerializedName("tgl_permintaan")
    private String tglPermintaan;

    @SerializedName("split")
    private String split;

    @SerializedName("daerah_mitra")
    private String daerahMitra;

    public String getTglPermintaan() {
        return tglPermintaan;
    }

    public void setTglPermintaan(String tglPermintaan) {
        this.tglPermintaan = tglPermintaan;
    }

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public String getDaerahMitra() {
        return daerahMitra;
    }

    public void setDaerahMitra(String daerahMitra) {
        this.daerahMitra = daerahMitra;
    }

    @Override
    public String toString() {
        return
                "LaporanRekapItem{" +
                        "tgl_permintaan = '" + tglPermintaan + '\'' +
                        ",split = '" + split + '\'' +
                        ",daerah_mitra = '" + daerahMitra + '\'' +
                        "}";
    }
}