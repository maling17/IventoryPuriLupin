package com.example.iventorypurilupin.response.response_tujuan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("mitra")
    private List<MitraItem> mitra;

    @SerializedName("status")
    private boolean status;

    public List<MitraItem> getMitra() {
        return mitra;
    }

    public void setMitra(List<MitraItem> mitra) {
        this.mitra = mitra;
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
                "ResponsePermintaan{" +
                        "mitra = '" + mitra + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}