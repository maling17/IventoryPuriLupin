package com.example.iventorypurilupin.response.response_total_rekap_flake;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_total_flake {

    @SerializedName("laporan")
    private List<LaporanTotalFlakeItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<LaporanTotalFlakeItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<LaporanTotalFlakeItem> laporan) {
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
                "Response_total_flake{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}