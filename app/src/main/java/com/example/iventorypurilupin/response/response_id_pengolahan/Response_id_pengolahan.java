package com.example.iventorypurilupin.response.response_id_pengolahan;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response_id_pengolahan {

    @SerializedName("pengolahan")
    private List<IdPengolahanItem> pengolahan;

    @SerializedName("status")
    private boolean status;

    public List<IdPengolahanItem> getPengolahan() {
        return pengolahan;
    }

    public void setPengolahan(List<IdPengolahanItem> pengolahan) {
        this.pengolahan = pengolahan;
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
                "Response_id_pengolahan{" +
                        "pengolahan = '" + pengolahan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}