package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang.Response_permintaan_barang;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServicesTampilSj {

    @GET("tampil_permintaan2.php")
    Call<Response_permintaan_barang> requestSplitPermintaan();

}
