package com.example.tipun_android_app.api;

import com.example.tipun_android_app.models.Utilities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UtilService {
    UtilService utilService = ApiService.retrofit.create(UtilService.class);

    //    http://192.168.1.65:8080/api/

    @GET("utils")
    Call<List<Utilities>> getAllUtilities();
}
