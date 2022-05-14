package com.example.tipun_android_app.dto;

public class SearchModel {
    private String keyword;
    private String location;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SearchModel(String keyword, String location) {
        this.keyword = keyword;
        this.location = location;
    }

    public SearchModel() {
    }
}
