package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("tampil_mitra.php")

    Call<Response> requset_all_mitra();

}
