package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_sj.Response_sj;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceSj {

    @GET("tampil_sj.php")
    Call<Response_sj>getSj();
}
