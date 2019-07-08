package com.example.iventorypurilupin.response.response_lap_rekap_flake;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_lap_rekap_flake {

    @SerializedName("laporan")
    private List<LaporanRekapFlakeItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<LaporanRekapFlakeItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<LaporanRekapFlakeItem> laporan) {
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
                "Response_lap_rekap_flake{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}