package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_detail_split.Response_detail_split;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceDetailPermintaanFlake {
    @FormUrlEncoded
    @POST("tambah_detail_permintaan_flake.php")
    Call<Response_detail_split> tambah_detail_flake(@Field("id_permintaan") String id_permintaan,
                                                    @Field("id_brg") int id_brg,
                                                    @Field("flake") String flake);

}
