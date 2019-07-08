package com.example.iventorypurilupin.response.response_lap_rekap;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_lap_rekap {

    @SerializedName("laporan")
    private List<LaporanRekapItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<LaporanRekapItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<LaporanRekapItem> laporan) {
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
                "Response_lap_rekap{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}