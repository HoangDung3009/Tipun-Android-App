package com.example.tipun_android_app.models;

import java.io.Serializable;

public class Bill implements Serializable {
    private Long id;
    private String createDate;
    private User billUser;
    private Room room;
    private Payment payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public User getBillUser() {
        return billUser;
    }

    public void setBillUser(User billUser) {
        this.billUser = billUser;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Bill() {
    }

    public Bill(Long id, String createDate, User billUser, Room room, Payment payment) {
        this.id = id;
        this.createDate = createDate;
        this.billUser = billUser;
        this.room = room;
        this.payment = payment;
    }
}
