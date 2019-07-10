package com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang;

import com.google.gson.annotations.SerializedName;


public class PermintaanItem {

    @SerializedName("id_permintaan")
    private String idPermintaan;

    @SerializedName("tgl_permintaan")
    private String tglPermintaan;

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

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    @Override
    public String toString() {
        return
                "PermintaanItem{" +
                        "id_permintaan = '" + idPermintaan + '\'' +
                        ",tgl_permintaan = '" + tglPermintaan + '\'' +
                        ",tujuan = '" + tujuan + '\'' +
                        "}";
    }
}