package com.example.iventorypurilupin.response.response_lap_rekap2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_rekap {

    @SerializedName("laporan")
    private List<LaporanRekap2Item> laporan;

    @SerializedName("status")
    private boolean status;

    public List<LaporanRekap2Item> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<LaporanRekap2Item> laporan) {
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
                "Response_rekap{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}