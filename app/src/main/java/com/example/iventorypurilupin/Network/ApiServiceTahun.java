package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_tahun.Response_tahun;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceTahun {

    @GET("tampil_tahun_sj.php")
    Call<Response_tahun>getTahun();

    @GET("tampil_tahun_olah.php")
    Call<Response_tahun>getTahunOlah();

    @GET("tampil_tahun_rekap.php")
    Call<Response_tahun>getTahunRekap();
}
