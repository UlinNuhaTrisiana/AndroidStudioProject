package com.example.myapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UtilityAPI {
    public static Retrofit retrofit=null;

    public static API getAPI(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(API.class);
    }
}
