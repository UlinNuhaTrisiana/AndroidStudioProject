package com.example.myapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String BASE_URL = "https://corona.lmao.ninja/v2/";
    @GET("countries")
    Call<List<Model>> getdatanegara();
}
