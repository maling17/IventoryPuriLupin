package com.example.iventorypurilupin.response;

import com.google.gson.annotations.SerializedName;


public class MitraItem {

    @SerializedName("pic_mitra")
    private String picMitra;

    @SerializedName("alamat_mitra")
    private String alamatMitra;

    @SerializedName("tlp_mitra")
    private String tlpMitra;

    @SerializedName("id_mitra")
    private String idMitra;

    @SerializedName("daerah_mitra")
    private String daerahMitra;

    public void setPicMitra(String picMitra) {
        this.picMitra = picMitra;
    }

    public String getPicMitra() {
        return picMitra;
    }

    public void setAlamatMitra(String alamatMitra) {
        this.alamatMitra = alamatMitra;
    }

    public String getAlamatMitra() {
        return alamatMitra;
    }

    public void setTlpMitra(String tlpMitra) {
        this.tlpMitra = tlpMitra;
    }

    public String getTlpMitra() {
        return tlpMitra;
    }

    public void setIdMitra(String idMitra) {
        this.idMitra = idMitra;
    }

    public String getIdMitra() {
        return idMitra;
    }

    public void setDaerahMitra(String daerahMitra) {
        this.daerahMitra = daerahMitra;
    }

    public String getDaerahMitra() {
        return daerahMitra;
    }

    @Override
    public String toString() {
        return
                "MitraItem{" +
                        "pic_mitra = '" + picMitra + '\'' +
                        ",alamat_mitra = '" + alamatMitra + '\'' +
                        ",tlp_mitra = '" + tlpMitra + '\'' +
                        ",id_mitra = '" + idMitra + '\'' +
                        ",daerah_mitra = '" + daerahMitra + '\'' +
                        "}";
    }
}