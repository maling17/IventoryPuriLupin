package com.example.iventorypurilupin.response.response_stok_awal;

import com.google.gson.annotations.SerializedName;


public class Stok_awal_Item {

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
                "Stok_awal_Item{" +
                        "stok= '" + stok + '\'' +
                        ",jenis_brg = '" + jenisBrg + '\'' +
                        "}";
    }
}