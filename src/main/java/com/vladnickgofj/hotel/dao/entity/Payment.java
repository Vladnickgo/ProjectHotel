package com.vladnickgofj.hotel.dao.entity;

public class Payment {
    private final int id;
    private final int bookingId;
    private final int userId;
    private final int amount;

    public Payment(int id, int bookingId, int userId, int amount) {
        this.id = id;
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
    }
}
