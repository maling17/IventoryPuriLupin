package com.example.iventorypurilupin.response.response_detail;

import com.google.gson.annotations.SerializedName;

public class LaporanDetailItem {

    @SerializedName("flake_sj")
    private String flakeSj;

    @SerializedName("split_sj")
    private String splitSj;

    @SerializedName("id_sj")
    private String idSj;

    @SerializedName("tgl_sj")
    private String tglSj;

    @SerializedName("tujuan")
    private String tujuan;

    public String getFlakeSj() {
        return flakeSj;
    }

    public void setFlakeSj(String flakeSj) {
        this.flakeSj = flakeSj;
    }

    public String getSplitSj() {
        return splitSj;
    }

    public void setSplitSj(String splitSj) {
        this.splitSj = splitSj;
    }

    public String getIdSj() {
        return idSj;
    }

    public void setIdSj(String idSj) {
        this.idSj = idSj;
    }

    public String getTglSj() {
        return tglSj;
    }

    public void setTglSj(String tglSj) {
        this.tglSj = tglSj;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    @Override
    public String toString() {
        return
                "LaporanDetailItem{" +
                        "flake_sj = '" + flakeSj + '\'' +
                        ",split_sj = '" + splitSj + '\'' +
                        ",id_sj = '" + idSj + '\'' +
                        ",tgl_sj = '" + tglSj + '\'' +
                        ",tujuan = '" + tujuan + '\'' +
                        "}";
    }
}