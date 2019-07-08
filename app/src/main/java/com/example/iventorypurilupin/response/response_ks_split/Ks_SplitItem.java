package com.example.iventorypurilupin.response.response_ks_split;

import com.google.gson.annotations.SerializedName;

public class Ks_SplitItem {

    @SerializedName("S_Olah")
    private String sOlah;

    @SerializedName("Split_akhir")
    private String splitAkhir;

    @SerializedName("Split_Awal")
    private String splitAwal;

    public String getSOlah() {
        return sOlah;
    }

    public void setSOlah(String sOlah) {
        this.sOlah = sOlah;
    }

    public String getSplitAkhir() {
        return splitAkhir;
    }

    public void setSplitAkhir(String splitAkhir) {
        this.splitAkhir = splitAkhir;
    }

    public String getSplitAwal() {
        return splitAwal;
    }

    public void setSplitAwal(String splitAwal) {
        this.splitAwal = splitAwal;
    }

    @Override
    public String toString() {
        return
                "Ks_SplitItem{" +
                        "s_Olah = '" + sOlah + '\'' +
                        ",split_akhir = '" + splitAkhir + '\'' +
                        ",split_Awal = '" + splitAwal + '\'' +
                        "}";
    }
}