package com.example.iventorypurilupin.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {

    private static String API_URL = "http://192.168.1.7/Gudang/";

    private static Retrofit setInit() {

        return new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getInstance() {
        return setInit().create(ApiService.class);
    }

    public static ApiServiceBarang getKacangBarang() {
        return setInit().create(ApiServiceBarang.class);

    }

    public static ApiServiceMitra getInstanceEntri() {
        return setInit().create(ApiServiceMitra.class);
    }

    public static ApiServiceGudang getInstanceGudang() {
        return setInit().create(ApiServiceGudang.class);
    }

    public static ApiServiceUpdateSj getUpdateGudang() {
        return setInit().create(ApiServiceUpdateSj.class);
    }

    public static ApiServiceSplit getUpdateSplit() {
        return setInit().create(ApiServiceSplit.class);
    }

    public static ApiServiceFlake getUpdateFlake() {
        return setInit().create(ApiServiceFlake.class);
    }

    public static ApiServiceOlah getTambahOlah() {
        return setInit().create(ApiServiceOlah.class);
    }

    public static ApiServiceUpdateOlah getUpdateolah() {
        return setInit().create(ApiServiceUpdateOlah.class);
    }

    public static ApiServicePenerimaan getTambahPenerimaan() {
        return setInit().create(ApiServicePenerimaan.class);
    }

    public static ApiServiceUpdatePenerimaan getUpdatePenerimaan() {
        return setInit().create(ApiServiceUpdatePenerimaan.class);
    }

    public static ApiServiceTujuan getTujuan() {
        return setInit().create(ApiServiceTujuan.class);
    }

    public static ApiServicePermintaan getPermintaan() {
        return setInit().create(ApiServicePermintaan.class);
    }

    public static ApiSearch getSearch() {
        return setInit().create(ApiSearch.class);
    }

    public static ApiServiceSj getSj() {
        return setInit().create(ApiServiceSj.class);
    }

    public static ApiServiceDetailPengiriman getDetail() {

        return setInit().create(ApiServiceDetailPengiriman.class);
    }

    public static ApiServiceDetailPermintaan getDetailPermintaan() {
        return setInit().create(ApiServiceDetailPermintaan.class);
    }

    public static ApiServiceDetailPermintaanFlake getDetailFlake() {
        return setInit().create(ApiServiceDetailPermintaanFlake.class);
    }

    public static ApiServicesTampilSj getTampilSj() {
        return setInit().create(ApiServicesTampilSj.class);
    }

    public static ApiServiceDetailPengolahanWhole getDetailPengolahanWhole() {
        return setInit().create(ApiServiceDetailPengolahanWhole.class);
    }

    public static ApiServiceDetailPengolahanSplit getDetailPengolahanSplit() {
        return setInit().create(ApiServiceDetailPengolahanSplit.class);
    }

    public static ApiServiceDetailPengolahanFlake getDetailPengolahanFlake() {
        return setInit().create(ApiServiceDetailPengolahanFlake.class);
    }

    public static ApiServiceFormSj getFormSj() {
        return setInit().create(ApiServiceFormSj.class);
    }

    public static ApiServiceStokAwal getStokAwal() {
        return setInit().create(ApiServiceStokAwal.class);
    }

    public static ApiServiceKSWhole getKSWhole() {
        return setInit().create(ApiServiceKSWhole.class);
    }

    public static ApiServiceKSSplit getKSSplit() {
        return setInit().create(ApiServiceKSSplit.class);
    }

    public static ApiServiceKSFlake getKsFlake() {
        return setInit().create(ApiServiceKSFlake.class);
    }

    public static ApiServiceTahun getTahun() {
        return setInit().create(ApiServiceTahun.class);
    }

    public static ApiServiceLaporan getLaporan() {
        return setInit().create(ApiServiceLaporan.class);
    }

    public static ApiServiceDetailLaporanStok getDetailStok() {
        return setInit().create(ApiServiceDetailLaporanStok.class);
    }

    public static ApiServiceLapRekap getRekap() {
        return setInit().create(ApiServiceLapRekap.class);
    }

    public static ApiServiceId getId2() {
        return setInit().create(ApiServiceId.class);
    }

    public static ApiServiceAntrian getAntrian() {
        return setInit().create(ApiServiceAntrian.class);
    }

    public static ApiServiceTotalRekap getTotalRekap() {
        return setInit().create(ApiServiceTotalRekap.class);
    }

}
