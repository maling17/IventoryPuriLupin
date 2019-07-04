package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_stok_awal.Response_stok_awal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceStokAwal {

    @GET("tampil_whole_awal.php")
    Call<Response_stok_awal> stok_awal();
}
