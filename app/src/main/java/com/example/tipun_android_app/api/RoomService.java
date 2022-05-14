package com.example.tipun_android_app.api;

import com.example.tipun_android_app.dto.SearchModel;
import com.example.tipun_android_app.models.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RoomService {
    RoomService roomService = ApiService.retrofit.create(RoomService.class);

    //    http://192.168.1.1:8080/api/

    @GET("rooms")
    Call<List<Room>> getAllRooms();

    @POST("rooms/add")
    Call<Room> addRoom(@Body Room room);

    @GET("rooms/{id}")
    Call<Room> getRoomById(@Path("id") Long id);

    @POST("rooms/search")
    Call<List<Room>> searchRoom(@Body SearchModel searchModel);
}
