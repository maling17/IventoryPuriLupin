package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_antrian.Response_antrian;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceAntrian {

    @GET("tampil_antrian.php")
    Call<Response_antrian>getAntrian();
}
