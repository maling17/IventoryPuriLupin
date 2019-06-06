package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceUpdatePenerimaan {

    @FormUrlEncoded
    @POST("update_penerimaan.php")
    Call<Value> update_penerimaan(
            @Field("qty_penerimaan") String qty_penerimaan);

}
