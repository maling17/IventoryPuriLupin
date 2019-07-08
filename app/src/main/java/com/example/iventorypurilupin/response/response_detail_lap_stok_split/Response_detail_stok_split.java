package com.example.iventorypurilupin.response.response_detail_lap_stok_split;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_detail_stok_split {

    @SerializedName("laporan")
    private List<DetailSplitItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<DetailSplitItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<DetailSplitItem> laporan) {
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
                "Response_detail_stok_split{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}