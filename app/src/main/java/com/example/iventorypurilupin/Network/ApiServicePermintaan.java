package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_mitra.Value;
import com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang.Response_permintaan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServicePermintaan {

    @FormUrlEncoded
    @POST("hapus_permintaan.php")
    Call<Value> hapus(@Field("id_permintaan") String id_permintaan);

    @GET("tampil_permintaan2.php")
    Call<Response_permintaan> request_all_permintaan();
}
