package com.example.iventorypurilupin.response.response_tahun;

import com.google.gson.annotations.SerializedName;


public class TahunItem {

    @SerializedName("tahun")
    private String tahun;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    @Override
    public String toString() {
        return
                "TahunItem{" +
                        "tahun = '" + tahun + '\'' +
                        "}";
    }
}