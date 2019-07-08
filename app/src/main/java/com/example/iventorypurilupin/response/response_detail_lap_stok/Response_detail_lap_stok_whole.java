package com.example.iventorypurilupin.response.response_detail_lap_stok;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_detail_lap_stok_whole {

    @SerializedName("laporan")
    private List<DetailWholeItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<DetailWholeItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<DetailWholeItem> laporan) {
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
                "Response_detail_lap_stok_whole{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}