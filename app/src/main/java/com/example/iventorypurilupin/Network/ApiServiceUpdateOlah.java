package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServiceUpdateOlah {

    @FormUrlEncoded
    @POST ("update_whole_olah.php")
    Call<Value> update_whole_olah (@Field("qty_olah") String qty_olah);

}
