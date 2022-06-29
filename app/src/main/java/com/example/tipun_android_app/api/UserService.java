package com.example.tipun_android_app.api;

import com.example.tipun_android_app.dto.LoginModel;
import com.example.tipun_android_app.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    UserService userService = ApiService.retrofit.create(UserService.class);

//    http://192.168.1.1:8080/api/users/auth/google

    @POST("users/login")
    Call<User> checkLogin(@Body LoginModel loginModel);
    @GET("users/{id}")
    Call<User> getUserById(@Path("id") Long id);
    @POST("users/sign-up")
    Call<User> register(@Body User user);
    @POST("users/favorite")
    Call<User> favorite(@Body User user);
    @PUT("users/update")
    Call<User> updateUser(@Body User user);
    @POST("users/auth/google")
    Call<User> loginWithGoogle(@Body String googleToken);
}
