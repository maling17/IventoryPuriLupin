package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceDetailPengolahanSplit {

    @FormUrlEncoded
    @POST("tambah_detail_pengolahan_split.php")
    Call<Value> getSplit(@Field("id_brg") int id_brg,
                         @Field("id_pengolahan") String id_pengolahan,
                         @Field("split") String split);
}
