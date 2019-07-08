package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_detail.Response_lap2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceDetailPengiriman {
//@FormUrlEncoded
    @GET("tampil_laporan_pengiriman2.php")
    Call<Response_lap2> getDetail(@Query("tgl_sj")String tgl_sj);

}
