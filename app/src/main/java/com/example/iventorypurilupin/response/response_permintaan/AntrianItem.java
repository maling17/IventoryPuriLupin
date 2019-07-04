package com.example.iventorypurilupin.response.response_permintaan;

import com.google.gson.annotations.SerializedName;


public class AntrianItem {

    @SerializedName("id_permintaan")
    private String id_permintaan;

    @SerializedName("tujuan")
    private String tujuan;
//    @SerializedName("id_mitra")
//    private String id_mitra;

    public String getId_permintaan() {
        return id_permintaan;
    }

    public void setId_permintaan(String id_permintaan) {
        this.id_permintaan = id_permintaan;
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
                        "id_permintaan = '" + id_permintaan + '\'' +
                        ",tujuan = '" + tujuan + '\'' +
//                        ",id_mitra = '"+id_mitra+'\''+
                        "}";
    }
}