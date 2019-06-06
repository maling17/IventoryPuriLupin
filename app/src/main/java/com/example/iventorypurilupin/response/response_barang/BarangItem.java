package com.example.iventorypurilupin.response.response_barang;

import com.google.gson.annotations.SerializedName;


public class BarangItem {

    @SerializedName("stok")
    private String stok;

    @SerializedName("jenis_brg")
    private String jenisBrg;

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getJenisBrg() {
        return jenisBrg;
    }

    public void setJenisBrg(String jenisBrg) {
        this.jenisBrg = jenisBrg;
    }

    @Override
    public String toString() {
        return
                "AntrianItem{" +
                        "stok = '" + stok + '\'' +
                        ",jenis_brg = '" + jenisBrg + '\'' +
                        "}";
    }
}