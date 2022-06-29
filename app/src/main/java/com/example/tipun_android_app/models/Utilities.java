package com.example.tipun_android_app.models;


import java.io.Serializable;
import java.util.List;


public class Utilities implements Serializable {
    private Long id;
    private String name;
    private String icon;
    private List<Room> rooms;

    @Override
    public String toString() {
        return "Utilities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", rooms=" + rooms +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Utilities(Long id, String name, String icon, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.rooms = rooms;
    }

    public Utilities() {
    }
}
