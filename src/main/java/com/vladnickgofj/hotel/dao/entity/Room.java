package com.vladnickgofj.hotel.dao.entity;

import java.lang.reflect.Type;
import java.util.Objects;

public class Room {
    private final int id;
    private final RoomType roomType;
    private final int numberOfBeds;
    private final RoomStatus roomStatus;
    private final int price;
    private final Hotel hotel;

    private Room(Builder builder) {
        id = builder.id;
        roomType = builder.roomType;
        numberOfBeds = builder.numberOfBeds;
        roomStatus = builder.roomStatus;
        price = builder.price;
        hotel = builder.hotel;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private RoomType roomType;
        private int numberOfBeds;
        private RoomStatus roomStatus;
        private int price;
        private Hotel hotel;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder roomType(RoomType val) {
            roomType = val;
            return this;
        }

        public Builder numberOfBeds(int val) {
            numberOfBeds = val;
            return this;
        }

        public Builder roomStatus(RoomStatus val) {
            roomStatus = val;
            return this;
        }

        public Builder price(int val) {
            price = val;
            return this;
        }

        public Builder hotel(Hotel val) {
            hotel = val;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }

    public int getId() {
        return id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public int getPrice() {
        return price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && numberOfBeds == room.numberOfBeds && price == room.price && Objects.equals(roomType, room.roomType) && Objects.equals(roomStatus, room.roomStatus) && Objects.equals(hotel, room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomType, numberOfBeds, roomStatus, price, hotel);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomType=" + roomType +
                ", numberOfBeds=" + numberOfBeds +
                ", roomStatus=" + roomStatus +
                ", price=" + price +
                ", hotel=" + hotel +
                '}';
    }
}
