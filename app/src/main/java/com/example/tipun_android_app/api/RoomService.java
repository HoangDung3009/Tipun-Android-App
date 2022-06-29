package com.example.tipun_android_app.api;

import com.example.tipun_android_app.dto.SearchModel;
import com.example.tipun_android_app.models.Bill;
import com.example.tipun_android_app.models.Payment;
import com.example.tipun_android_app.models.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RoomService {
    RoomService roomService = ApiService.retrofit.create(RoomService.class);

    //    http://192.168.1.1:8080/api/

    @GET("rooms")
    Call<List<Room>> getAllRooms(@Query("user_id") Long id);
    @POST("rooms/add/{id}")
    Call<Room> addRoom(@Body Room room, @Path("id") Long id);
    @GET("rooms/{id}")
    Call<Room> getRoomById(@Path("id") Long id);
    @POST("rooms/search")
    Call<List<Room>> searchRoom(@Body SearchModel searchModel);
    @PUT("rooms/update")
    Call<Room> updateRoom(@Body Room room);
    @POST("bills/create")
    Call<Bill> createBill(@Query("user_id") Long user_id, @Query("room_id") Long room_id, @Query("payment_id") Long payment_id);
    @GET("payments")
    Call<List<Payment>> getAllPayments();
    @GET("rooms/user")
    Call<List<Room>> getRoomByUser(@Query("user_id") Long user_id);
}
