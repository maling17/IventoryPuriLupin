package com.example.iventorypurilupin.response.response_detail_lap_stok_split;

import com.google.gson.annotations.SerializedName;

public class DetailSplitItem {

    @SerializedName("Split_akhir")
    private String splitAkhir;

    @SerializedName("Split_Masuk")
    private String splitMasuk;

    @SerializedName("Split_Awal")
    private String splitAwal;

    public String getSplitAkhir() {
        return splitAkhir;
    }

    public void setSplitAkhir(String splitAkhir) {
        this.splitAkhir = splitAkhir;
    }


    public String getSplitMasuk() {
        return splitMasuk;
    }

    public void setSplitMasuk(String splitMasuk) {
        this.splitMasuk = splitMasuk;
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
                "DetailSplitItem{" +
                        "split_akhir = '" + splitAkhir + '\'' +
                        ",split_masuk='"+splitMasuk+'\''+
                        ",split_Awal = '" + splitAwal + '\'' +
                        "}";
    }

}