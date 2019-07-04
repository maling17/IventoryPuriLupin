package com.example.iventorypurilupin.response.response_sj;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_sj {

    @SerializedName("laporan")
    private List<LaporanItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<LaporanItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<LaporanItem> laporan) {
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
                "Response_sj{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}