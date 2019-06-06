package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_tujuan.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceTujuan {

    @GET("tampil_tujuan.php")
    Call<Response>getTujuan();
}
