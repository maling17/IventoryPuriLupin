package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceOlah {

    @FormUrlEncoded
    @POST("tambah_pengolahan.php")
    Call<Value> tambah_pengolahan(
            @Field("id_pengolahan") String id_pengolahan,
            @Field("tgl_pengolahan") String tgl_pengolahan,
            @Field("qty_olah") String qty_olah);
}
