package com.example.iventorypurilupin.response.response_detail_split;

import com.google.gson.annotations.SerializedName;


public class Response_detail_split {

    @SerializedName("message")
    private String message;

    @SerializedName("value")
    private int value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return
                "Response_detail_split{" +
                        "message = '" + message + '\'' +
                        ",value = '" + value + '\'' +
                        "}";
    }
}