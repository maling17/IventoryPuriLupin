package com.example.iventorypurilupin.response.response_sj;

import com.google.gson.annotations.SerializedName;


public class LaporanItem {

    @SerializedName("flake_sj")
    private String flakeSj;

    @SerializedName("split_sj")
    private String splitSj;

    @SerializedName("tgl_sj")
    private String tgl_sj;

    @SerializedName("id_sj")
    private String idSj;

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

    public String gettgl_sj() {
        return tgl_sj;
    }

    public void setTgl_sj(String tgl_sj) {
        this.tgl_sj = tgl_sj;
    }

    public String getIdSj() {
        return idSj;
    }

    public void setIdSj(String idSj) {
        this.idSj = idSj;
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
                        ",tgl_sj = '" + tgl_sj + '\'' +
                        ",id_sj = '" + idSj + '\'' +
                        ",tujuan = '" + tujuan + '\'' +
                        "}";
    }
}