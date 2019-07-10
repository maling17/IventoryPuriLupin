package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_kacang.response_flake.Response_barang_flake;
import com.example.iventorypurilupin.response.response_kacang.response_split.Response_split;
import com.example.iventorypurilupin.response.response_kacang.response_whole.Response_whole;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceBarang {

    @GET("tampil_barang_whole.php")
    Call<Response_whole> getBarangWhole();

    @GET("tampil_barang_split.php")
    Call<Response_split> getBarangSplit();

    @GET("tampil_barang_flake.php")
    Call<Response_barang_flake> getBarangFlake();
}
