package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_ks_split.Response_ks_split;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceKSSplit {

    @GET("tampil_split_kartu.php")
    Call<Response_ks_split>splitKs();
}
