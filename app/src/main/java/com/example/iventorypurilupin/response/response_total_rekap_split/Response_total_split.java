package com.example.iventorypurilupin.response.response_total_rekap_split;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_total_split {

    @SerializedName("laporan")
    private List<LaporanTotalSplitItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<LaporanTotalSplitItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<LaporanTotalSplitItem> laporan) {
        this.laporan = laporan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "Response_total_split{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}