package com.example.iventorypurilupin.response.response_id_permintaan;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_id_permintaan {

    @SerializedName("permintaan")
    private List<IdPermintaanItem> permintaan;

    @SerializedName("status")
    private boolean status;

    public List<IdPermintaanItem> getPermintaan() {
        return permintaan;
    }

    public void setPermintaan(List<IdPermintaanItem> permintaan) {
        this.permintaan = permintaan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "Response_id_permintaan{" +
                        "permintaan = '" + permintaan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}