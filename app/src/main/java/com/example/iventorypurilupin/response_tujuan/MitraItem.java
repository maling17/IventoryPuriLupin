package com.example.iventorypurilupin.response_tujuan;

import com.google.gson.annotations.SerializedName;

public class MitraItem {

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
                        "daerah_mitra = '" + daerahMitra + '\'' +
                        "}";
    }
}