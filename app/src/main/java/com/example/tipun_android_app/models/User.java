package com.example.tipun_android_app.models;


import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.util.Date;
import java.util.List;


public class User implements Serializable {
    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String gender;
    private Date dob;
    private String phone;
    private String email;
    private List<Room> postedRooms;
    private List<Room> favoriteRooms;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", postedRooms=" + postedRooms +
                ", favoriteRooms=" + favoriteRooms +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Room> getPostedRooms() {
        return postedRooms;
    }

    public void setPostedRooms(List<Room> postedRooms) {
        this.postedRooms = postedRooms;
    }

    public List<Room> getFavoriteRooms() {
        return favoriteRooms;
    }

    public void setFavoriteRooms(List<Room> favoriteRooms) {
        this.favoriteRooms = favoriteRooms;
    }

    public User() {
    }

    public User(Long id, String fullname, String username, String password, String gender, Date dob, String phone, String email, List<Room> postedRooms, List<Room> favoriteRooms) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.postedRooms = postedRooms;
        this.favoriteRooms = favoriteRooms;
    }
}
