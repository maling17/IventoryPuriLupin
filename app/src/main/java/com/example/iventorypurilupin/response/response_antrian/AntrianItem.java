package com.example.iventorypurilupin.response.response_antrian;

import com.google.gson.annotations.SerializedName;

public class AntrianItem {

    @SerializedName("id_permintaan")
    private String idPermintaan;

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

    @SerializedName("tlp_mitra")
    private String tlp_mitra;

    public String getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

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

    public String getTlp_mitra() {
        return tlp_mitra;
    }

    public void setTlp_mitra(String tlp_mitra) {
        this.tlp_mitra = tlp_mitra;
    }

    @Override
    public String toString() {
        return
                "AntrianItem{" +
                        "id_permintaan = '" + idPermintaan + '\'' +
                        ",flake_sj = '" + flakeSj + '\'' +
                        ",split_sj = '" + splitSj + '\'' +
                        ",id_sj = '" + idSj + '\'' +
                        ",tgl_sj = '" + tglSj + '\'' +
                        ",tujuan = '" + tujuan + '\'' +
                        ",tlp_mitra='" + tlp_mitra + '\'' +
                        "}";
    }


}