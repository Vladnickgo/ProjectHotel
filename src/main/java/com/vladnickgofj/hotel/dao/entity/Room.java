package com.vladnickgofj.hotel.dao.entity;

public class Room {
    private final int id;
    private final int typeId;
    private final int numberOfBeds;
    private final int statusId;
    private final int price;
    private final int hotelId;

    public Room(int id, int typeId, int numberOfBeds, int statusId, int price, int hotelId) {
        this.id = id;
        this.typeId = typeId;
        this.numberOfBeds = numberOfBeds;
        this.statusId = statusId;
        this.price = price;
        this.hotelId = hotelId;
    }
}
