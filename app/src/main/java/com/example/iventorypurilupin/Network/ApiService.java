package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.Response;
import com.example.iventorypurilupin.response_barang.Responsebarang;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("tampil_mitra.php")

    Call<Response> requset_all_mitra();

    @GET("tampil_barang.php")
    Call<Responsebarang>request_all_barang();

}
