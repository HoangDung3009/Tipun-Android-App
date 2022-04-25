package com.example.tipun_android_app.models;


import java.util.List;

public class Room {
    private Long id;
    private String title;
    private String description;
    private String phone_contact;
    private double acreage;
    private String type;
    private int capacity;
    private String gender_allow;
    private double price;
    private double water_price;
    private double electric_price;
    private String street_name;
    private String building;
    private String district;
    private String province;
    private String commune;
    private List<RoomImages> room_image;
    private List<User> rentedUsers;
    private List<User> favoriteUsers;
    private List<Utilities> roomUtilities;
}
