package com.example.iventorypurilupin.Network;

import com.example.iventorypurilupin.response.response_detail.Response_detail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceDetailPengiriman {
//@FormUrlEncoded
    @GET("{tgl_sj}/tampil_detail_laporan_pengiriman.php")
    Call<Response_detail> getDetail(@Path("tgl_sj") String tgl_sj,
                                    @Query("tanggal")String tanggal);

}
