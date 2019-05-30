package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceUpdateSj {
    @FormUrlEncoded
    @POST("update_split_sj.php")
    Call<Value> update_split_sj(
            @Field("split_sj") String split_sj);

    @FormUrlEncoded
    @POST("update_flake_sj.php")
    Call<Value> update_flake_sj(
            @Field("flake_sj") String flake_sj);
}
