package com.example.tipun_android_app.api;

import com.example.tipun_android_app.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

//    Link API: http://localhost:8080/api/
    Retrofit retrofit =  new Retrofit.Builder()
            .baseUrl("http://192.168.1.65:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

}
