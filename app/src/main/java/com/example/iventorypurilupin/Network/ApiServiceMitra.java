package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceMitra {
    @FormUrlEncoded
    @POST("tambah_mitra.php")
    Call<Value> insert_mitra(@Field("id_mitra") String id_mitra,
                             @Field("daerah_mitra") String daerah_mitra,
                             @Field("pic_mitra") String PIC,
                             @Field("tlp_mitra") String noTelp,
                             @Field("alamat_mitra") String alamat);

    @FormUrlEncoded
    @POST("update.php")
    Call<Value> ubah_mitra(@Field("id_mitra") String id_mitra,
                           @Field("daerah_mitra") String daerah_mitra,
                           @Field("pic_mitra") String PIC,
                           @Field("tlp_mitra") String noTelp,
                           @Field("alamat_mitra") String alamat);

    @FormUrlEncoded
    @POST("hapus_mitra.php")
    Call<Value> hapus(@Field("id_mitra") String id_mitra);

}

