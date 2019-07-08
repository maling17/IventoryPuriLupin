package com.example.iventorypurilupin.response.response_detail_lap_stok;

import com.google.gson.annotations.SerializedName;


public class DetailWholeItem {


    @SerializedName("tgl_pengolahan")
    private String tglPengolahan;

    @SerializedName("Whole_Awal")
    private String wholeAwal;

    @SerializedName("Whole_akhir")
    private String wholeAkhir;

    public String getWholeAwal() {
        return wholeAwal;
    }

    public void setWholeAwal(String wholeAwal) {
        this.wholeAwal = wholeAwal;
    }

    public String getTglPengolahan() {
        return tglPengolahan;
    }

    public void setTglPengolahan(String tglPengolahan) {
        this.tglPengolahan = tglPengolahan;
    }

    public String getWholeAkhir() {
        return wholeAkhir;
    }

    public void setWholeAkhir(String wholeAkhir) {
        this.wholeAkhir = wholeAkhir;
    }

    @Override
    public String toString() {
        return
                "DetailWholeItem{" +
                        "tgl_pengolahan='"+tglPengolahan+'\''+
                        "whole_Awal = '" + wholeAwal + '\'' +
                        ",whole_akhir = '" + wholeAkhir + '\'' +
                        "}";
    }


}