package com.example.iventorypurilupin.response.response_antrian;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_antrian {

    @SerializedName("laporan")
    private List<AntrianItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<AntrianItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<AntrianItem> laporan) {
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
                "Response_antrian{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}