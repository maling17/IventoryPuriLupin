package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_detail_lap_stok.Response_detail_lap_stok_whole;
import com.example.iventorypurilupin.response.response_detail_lap_stok_flake.Response_detail_lap_stok_flake;
import com.example.iventorypurilupin.response.response_detail_lap_stok_split.Response_detail_stok_split;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceDetailLaporanStok {

    @GET("tampil_whole_lap_stok.php")
    Call<Response_detail_lap_stok_whole> getWhole(@Query("tgl_pengolahan") String tgl_pengolahan);

    @GET("tampil_split_lap_stok.php")
    Call<Response_detail_stok_split> getSplit(@Query("tgl_pengolahan") String tgl_pengolahan);

    @GET("tampil_flake_lap_stok.php")
    Call<Response_detail_lap_stok_flake> getFlake(@Query("tgl_pengolahan") String tgl_pengolahan);

}
