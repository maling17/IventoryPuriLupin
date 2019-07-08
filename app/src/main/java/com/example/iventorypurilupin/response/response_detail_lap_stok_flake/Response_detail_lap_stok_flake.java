package com.example.iventorypurilupin.response.response_detail_lap_stok_flake;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_detail_lap_stok_flake {

    @SerializedName("laporan")
    private List<DetailFlakeItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<DetailFlakeItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<DetailFlakeItem> laporan) {
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
                "Response_detail_lap_stok_flake{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}