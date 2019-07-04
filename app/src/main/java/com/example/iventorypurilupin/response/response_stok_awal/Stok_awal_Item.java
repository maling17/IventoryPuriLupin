package com.example.iventorypurilupin.response.response_stok_awal;

import com.google.gson.annotations.SerializedName;


public class Stok_awal_Item {

    @SerializedName("stok_awal")
    private String stokAwal;

    @SerializedName("jenis_brg")
    private String jenisBrg;

    public String getStokAwal() {
        return stokAwal;
    }

    public void setStokAwal(String stokAwal) {
        this.stokAwal = stokAwal;
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
                        "stok_awal = '" + stokAwal + '\'' +
                        ",jenis_brg = '" + jenisBrg + '\'' +
                        "}";
    }
}