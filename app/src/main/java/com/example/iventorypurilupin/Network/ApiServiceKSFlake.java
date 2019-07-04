package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_ks_flake.Response_ks_flake;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceKSFlake {
@GET("tampil_flake_kartu,php.php")
    Call<Response_ks_flake> flakeks();

}
