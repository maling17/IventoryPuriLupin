package com.example.iventorypurilupin.response.response_ks_whole;

import com.google.gson.annotations.SerializedName;


public class Ks_WholeItem {

    @SerializedName("Whole_Awal")
    private String wholeAwal;

    @SerializedName("W_Olah")
    private String wOlah;

    @SerializedName("Whole_akhir")
    private String wholeAkhir;

    @SerializedName("Tanggal_Olah")
    private String tanggalOlah;

    public String getWholeAwal() {
        return wholeAwal;
    }

    public void setWholeAwal(String wholeAwal) {
        this.wholeAwal = wholeAwal;
    }

    public String getWOlah() {
        return wOlah;
    }

    public void setWOlah(String wOlah) {
        this.wOlah = wOlah;
    }

    public String getWholeAkhir() {
        return wholeAkhir;
    }

    public void setWholeAkhir(String wholeAkhir) {
        this.wholeAkhir = wholeAkhir;
    }

    public String getTanggalOlah() {
        return tanggalOlah;
    }

    public void setTanggalOlah(String tanggalOlah) {
        this.tanggalOlah = tanggalOlah;
    }

    @Override
    public String toString() {
        return
                "Ks_WholeItem{" +
                        "whole_Awal = '" + wholeAwal + '\'' +
                        ",w_Olah = '" + wOlah + '\'' +
                        ",whole_akhir = '" + wholeAkhir + '\'' +
                        ",tanggal_Olah = '" + tanggalOlah + '\'' +
                        "}";
    }
}