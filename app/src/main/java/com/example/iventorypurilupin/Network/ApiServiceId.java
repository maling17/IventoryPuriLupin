package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_id_pengolahan.Response_id_pengolahan;
import com.example.iventorypurilupin.response.response_id_permintaan.Response_id_permintaan;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceId {

    @GET("tampil_id_permintaan.php")
    Call<Response_id_permintaan> idPermintaan();
    @GET("tampil_id_pengolahan.php")
    Call<Response_id_pengolahan> idPengolahan();
}
