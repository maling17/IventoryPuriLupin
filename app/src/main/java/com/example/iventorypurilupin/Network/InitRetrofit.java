package com.example.iventorypurilupin.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {


    public static String API_URL= "http://192.168.1.8/Gudang/";

    public static Retrofit setInit(){
        return  new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                }
                public static ApiService getInstance(){
        return setInit().create(ApiService.class);
    }
    public static ApiServiceMitra getInstanceEntri(){
        return setInit().create(ApiServiceMitra.class);
    }
}
