package com.example.tipun_android_app.models;

import java.io.Serializable;

public class Payment implements Serializable {
    private Long id;
    private String name;

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

    public Payment(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Payment() {
    }

    @Override
    public String toString() {
        return name;
    }
}
