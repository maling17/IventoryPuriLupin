package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_lap_rekap.Response_lap_rekap;
import com.example.iventorypurilupin.response.response_lap_rekap_flake.Response_lap_rekap_flake;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceLapRekap {

    @GET("tampil_laporan_rekap_split.php")
    Call<Response_lap_rekap> getRekapSplit(@Query("tgl_permintaan")String tgl_permintaan);

    @GET("tampil_laporan_rekap_flake.php")
    Call<Response_lap_rekap_flake> getRekapFlake(@Query("tgl_permintaan")String tgl_permintaan);


}
