package com.example.iventorypurilupin.response.response_lap_stok;

import com.google.gson.annotations.SerializedName;


public class LaporanStokItem {

    @SerializedName("tgl_pengolahan")
    private String tglPengolahan;

    public String getTglPengolahan() {
        return tglPengolahan;
    }

    public void setTglPengolahan(String tglPengolahan) {
        this.tglPengolahan = tglPengolahan;
    }

    @Override
    public String toString() {
        return
                "LaporanStokItem{" +
                        "tgl_pengolahan = '" + tglPengolahan + '\'' +
                        "}";
    }
}