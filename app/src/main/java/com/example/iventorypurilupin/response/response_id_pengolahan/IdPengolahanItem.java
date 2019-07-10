package com.example.iventorypurilupin.response.response_id_pengolahan;

import com.google.gson.annotations.SerializedName;

public class IdPengolahanItem {

    @SerializedName("id_pengolahan")
    private int idPengolahan;

    public int getIdPengolahan() {
        return idPengolahan;
    }

    public void setIdPengolahan(int idPengolahan) {
        this.idPengolahan = idPengolahan;
    }

    @Override
    public String toString() {
        return
                "IdPengolahanItem{" +
                        "id_pengolahan = '" + idPengolahan + '\'' +
                        "}";
    }
}