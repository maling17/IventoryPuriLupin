package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_form_sj.Response_form_sj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceFormSj {

    @GET("tampil_permintaan.php")
    Call<Response_form_sj>getFormSj(@Query("id_permintaan")String id_permintaan);
}
