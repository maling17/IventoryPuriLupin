package com.example.iventorypurilupin.response.response_permintaan;

import com.google.gson.annotations.SerializedName;


public class AntrianItem {

    @SerializedName("id_permintaan")
    private String id_permintaan;
    @SerializedName("split_permintaan")
    private String splitPermintaan;
    @SerializedName("flake_permintaan")
    private String flakePermintaan;
    @SerializedName("tujuan")
    private String tujuan;

    public String getId_permintaan() {
        return id_permintaan;
    }

    public void setId_permintaan(String id_permintaan) {
        this.id_permintaan = id_permintaan;
    }

    public String getSplitPermintaan() {
        return splitPermintaan;
    }

    public void setSplitPermintaan(String splitPermintaan) {
        this.splitPermintaan = splitPermintaan;
    }

    public String getFlakePermintaan() {
        return flakePermintaan;
    }

    public void setFlakePermintaan(String flakePermintaan) {
        this.flakePermintaan = flakePermintaan;
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
                "AntrianItem{" +
                        "id_permintaan = '"+id_permintaan+'\''+
                        "split_permintaan = '" + splitPermintaan + '\'' +
                        ",flake_permintaan = '" + flakePermintaan + '\'' +
                        ",tujuan = '" + tujuan + '\'' +
                        "}";
    }
}