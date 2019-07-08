package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiSearch {
    @FormUrlEncoded
    @POST("search_permintaan.php")
    Call<Value> search(@Field("search") String search);
}
