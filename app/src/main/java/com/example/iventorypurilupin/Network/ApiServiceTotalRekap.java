package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_total_rekap_flake.Response_total_flake;
import com.example.iventorypurilupin.response.response_total_rekap_split.Response_total_split;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceTotalRekap {

    @GET("tampil_total_rekap_flake.php")
    Call<Response_total_flake> getTotalRekapFlake(@Query("tgl_permintaan")String tgl_permintaan);

    @GET("tampil_total_rekap_split.php")
    Call<Response_total_split> getTotalRekapSplit(@Query("tgl_permintaan") String tgl_permintaan);


}
