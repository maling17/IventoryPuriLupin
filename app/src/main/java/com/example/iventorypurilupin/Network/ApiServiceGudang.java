package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceGudang {

    @FormUrlEncoded
    @POST("tambah_permintaan.php")
    Call<Value> tambah_permintaan(@Field("id_permintaan") String id_permintaan,
                                  @Field("tgl_permintaan") String tgl_permintaan,
                                  @Field("tujuan") String tujuan,
                                  @Field("split_permintaan") String split_permintaan,
                                  @Field("flake_permintaan") String flake_permintaan);

    @FormUrlEncoded
    @POST("tambah_sj.php")
    Call<Value> tambah_sj(@Field("id_sj") String id_sj,
                          @Field("tgl_sj") String tgl_sj,
                          @Field("tujuan") String tujuanm,
                          @Field("split_sj") String split_sj,
                          @Field("flake_sj") String flake_sj);


}
