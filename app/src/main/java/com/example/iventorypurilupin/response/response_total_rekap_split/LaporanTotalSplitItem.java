package com.example.iventorypurilupin.response.response_total_rekap_split;

import com.google.gson.annotations.SerializedName;

public class LaporanTotalSplitItem {

    @SerializedName("split")
    private String split;

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    @Override
    public String toString() {
        return
                "LaporanTotalSplitItem{" +
                        "split = '" + split + '\'' +
                        "}";
    }
}