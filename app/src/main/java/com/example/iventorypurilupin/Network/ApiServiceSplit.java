package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceSplit {
    @FormUrlEncoded
    @POST("update_split_barang.php")
    Call<Value> update_split_brg(
            @Field("split_entri") String split_entri);


}
