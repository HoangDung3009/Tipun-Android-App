package com.example.tipun_android_app.models;


import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {
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
    private String city;
    private List<RoomImages> room_image;
    private User postUser;
    private List<User> favoriteUsers;
    private List<Utilities> roomUtilities;
    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone_contact() {
        return phone_contact;
    }

    public void setPhone_contact(String phone_contact) {
        this.phone_contact = phone_contact;
    }

    public double getAcreage() {
        return acreage;
    }

    public void setAcreage(double acreage) {
        this.acreage = acreage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getGender_allow() {
        return gender_allow;
    }

    public void setGender_allow(String gender_allow) {
        this.gender_allow = gender_allow;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWater_price() {
        return water_price;
    }

    public void setWater_price(double water_price) {
        this.water_price = water_price;
    }

    public double getElectric_price() {
        return electric_price;
    }

    public void setElectric_price(double electric_price) {
        this.electric_price = electric_price;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<RoomImages> getRoom_image() {
        return room_image;
    }

    public void setRoom_image(List<RoomImages> room_image) {
        this.room_image = room_image;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

    public List<User> getFavoriteUsers() {
        return favoriteUsers;
    }

    public void setFavoriteUsers(List<User> favoriteUsers) {
        this.favoriteUsers = favoriteUsers;
    }

    public List<Utilities> getRoomUtilities() {
        return roomUtilities;
    }

    public void setRoomUtilities(List<Utilities> roomUtilities) {
        this.roomUtilities = roomUtilities;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", phone_contact='" + phone_contact + '\'' +
                ", acreage=" + acreage +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", gender_allow='" + gender_allow + '\'' +
                ", price=" + price +
                ", water_price=" + water_price +
                ", electric_price=" + electric_price +
                ", street_name='" + street_name + '\'' +
                ", building='" + building + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", commune='" + commune + '\'' +
                ", city='" + city + '\'' +
                ", room_image=" + room_image +
                ", postUser=" + postUser +
                ", favoriteUsers=" + favoriteUsers +
                ", roomUtilities=" + roomUtilities +
                '}';
    }

    public Room(Long id, String title, String description, String phone_contact, double acreage, String type, int capacity, String gender_allow, double price, double water_price, double electric_price, String street_name, String building, String district, String province, String commune, String city, List<RoomImages> room_image, User postUser, List<User> favoriteUsers, List<Utilities> roomUtilities, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.phone_contact = phone_contact;
        this.acreage = acreage;
        this.type = type;
        this.capacity = capacity;
        this.gender_allow = gender_allow;
        this.price = price;
        this.water_price = water_price;
        this.electric_price = electric_price;
        this.street_name = street_name;
        this.building = building;
        this.district = district;
        this.province = province;
        this.commune = commune;
        this.city = city;
        this.room_image = room_image;
        this.postUser = postUser;
        this.favoriteUsers = favoriteUsers;
        this.roomUtilities = roomUtilities;
        this.status = status;
    }

    public Room() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
