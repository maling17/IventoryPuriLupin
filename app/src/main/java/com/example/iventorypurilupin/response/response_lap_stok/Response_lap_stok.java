package com.example.iventorypurilupin.response.response_lap_stok;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_lap_stok {

    @SerializedName("laporan")
    private List<LaporanStokItem> laporan;

    @SerializedName("status")
    private boolean status;

    public List<LaporanStokItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<LaporanStokItem> laporan) {
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
                "Response_lap_stok{" +
                        "laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}