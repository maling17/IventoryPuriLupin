package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServicePenerimaan {
    @FormUrlEncoded
    @POST("tambah_penerimaan.php")
    Call<Value> tambah_penerimaan(
            @Field("id_penerimaan") String id_penerimaan,
            @Field("tgl_penerimaan") String tgl_penerimaan,
            @Field("no_po") String no_po,
            @Field("qty_penerimaan") String qty_penerimaan);
}
