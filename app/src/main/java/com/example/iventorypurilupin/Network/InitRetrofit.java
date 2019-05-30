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
}
