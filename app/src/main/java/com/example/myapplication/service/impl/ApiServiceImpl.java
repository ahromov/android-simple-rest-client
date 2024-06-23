package com.example.myapplication.service.impl;

import com.example.myapplication.model.Sources;
import com.example.myapplication.service.ApiService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class ApiServiceImpl {

    private static String baseUrl = "http://192.168.0.5:9090/";
    private ApiService apiService;

    public ApiServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.apiService = retrofit.create(ApiService.class);
    }

    public Call<List<Sources>> getSources() {
        return apiService.getSources();
    }

    public Call<Void> startSync(Sources source) {
        return apiService.startSync(source.getJobName());
    }
}
