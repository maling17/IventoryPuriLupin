package com.example.iventorypurilupin.response.response_form_sj;

import com.google.gson.annotations.SerializedName;


public class BarangItem {

    @SerializedName("id_permintaan")
    private String idPermintaan;

    @SerializedName("tgl_permintaan")
    private String tglPermintaan;

    @SerializedName("split")
    private String split;

    @SerializedName("flake")
    private String flake;

    @SerializedName("tujuan")
    private String tujuan;

    public String getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

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

    public String getFlake() {
        return flake;
    }

    public void setFlake(String flake) {
        this.flake = flake;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    @Override
    public String toString() {
        return
                "BarangSplitItem{" +
                        "id_permintaan = '" + idPermintaan + '\'' +
                        ",tgl_permintaan = '" + tglPermintaan + '\'' +
                        ",split = '" + split + '\'' +
                        ",flake = '" + flake + '\'' +
                        ",tujuan = '" + tujuan + '\'' +
                        "}";
    }
}