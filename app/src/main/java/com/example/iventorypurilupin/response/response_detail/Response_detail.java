package com.example.iventorypurilupin.response.response_detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_detail {

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("laporan")
    private List<LaporanDetailItem> laporan;

    @SerializedName("status")
    private boolean status;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public List<LaporanDetailItem> getLaporan() {
        return laporan;
    }

    public void setLaporan(List<LaporanDetailItem> laporan) {
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
                "Response_detail{" +
                        "tanggal = '" + tanggal + '\'' +
                        ",laporan = '" + laporan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}