package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceFlake {
    @FormUrlEncoded
    @POST("update_flake_barang.php")
    Call<Value> update_flake_brg(
            @Field("flake_entri") String flake_entri);
}
