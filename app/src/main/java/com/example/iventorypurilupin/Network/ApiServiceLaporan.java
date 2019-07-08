package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_lap_stok.Response_lap_stok;
import com.example.iventorypurilupin.response.response_laporan_pengiriman.Response_lap_pengiriman;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceLaporan {

    @GET("tampil_laporan_pengiriman.php")
    Call<Response_lap_pengiriman> getLaporan(@Query("bulan") String bulan,
                                             @Query("tahun") String tahun);

    @GET("tampil_laporan_stok.php")
    Call<Response_lap_stok> getLaporanStok(@Query("bulan") String bulan,
                                           @Query("tahun") String tahun);



}
