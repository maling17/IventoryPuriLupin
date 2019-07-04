package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_ks_whole.Response_ks_whole;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceKSWhole {

    @GET("tampil_whole_kartu.php")
    Call<Response_ks_whole>ksWhole();
}
