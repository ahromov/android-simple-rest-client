package com.example.myapplication.service;

import com.example.myapplication.model.Sources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;


public interface ApiService {

    @GET("sources")
    Call<List<Sources>> getSources();

    @POST("synchronizations/{jobName}/run")
    Call<Void> startSync(@Path("jobName") String jobName);
}
