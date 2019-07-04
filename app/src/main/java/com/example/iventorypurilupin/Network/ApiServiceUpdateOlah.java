package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceUpdateOlah {

    @FormUrlEncoded
    @POST("update_whole.php")
    Call<Value> update_whole(@Field("whole") String whole);

    @FormUrlEncoded
    @POST("update_split.php")
    Call<Value> update_split(@Field("split") String split);

    @FormUrlEncoded
    @POST("update_flake.php")
    Call<Value> update_flake(@Field("flake") String flake);

}
