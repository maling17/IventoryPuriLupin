package com.example.iventorypurilupin.response.response_id_permintaan;

import com.google.gson.annotations.SerializedName;


public class IdPermintaanItem {

    @SerializedName("id_permintaan")
    private String idPermintaan;

    public int getIdPermintaan() {
        return Integer.parseInt(idPermintaan);
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

    @Override
    public String toString() {
        return
                "IdPermintaanItem{" +
                        "id_permintaan = '" + idPermintaan + '\'' +
                        "}";
    }
}