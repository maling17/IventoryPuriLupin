package com.example.iventorypurilupin.response.response_tujuan;

import com.google.gson.annotations.SerializedName;

public class MitraItem {
    @SerializedName("id_mitra")
    private String id_mitra;
    @SerializedName("daerah_mitra")
    private String daerahMitra;

    public String getDaerahMitra() {
        return daerahMitra;
    }

    public void setDaerahMitra(String daerahMitra) {
        this.daerahMitra = daerahMitra;
    }

    @Override
    public String toString() {
        return
                "MitraItem{" +
                        "id_mitra='" + id_mitra + '\'' +
                        "daerah_mitra = '" + daerahMitra + '\'' +
                        "}";
    }

    public String getId_mitra() {
        return id_mitra;
    }

    public void setId_mitra(String id_mitra) {
        this.id_mitra = id_mitra;
    }
}