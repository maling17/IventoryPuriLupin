package com.example.iventorypurilupin.response.response_tahun;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_tahun {

    @SerializedName("tahun")
    private List<TahunItem> tahun;

    @SerializedName("status")
    private boolean status;

    public List<TahunItem> getTahun() {
        return tahun;
    }

    public void setTahun(List<TahunItem> tahun) {
        this.tahun = tahun;
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
                "Response_tahun{" +
                        "tahun = '" + tahun + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}