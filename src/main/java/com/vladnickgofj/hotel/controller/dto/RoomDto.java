package com.vladnickgofj.hotel.controller.dto;

import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.entity.RoomType;

import java.util.Objects;

public class RoomDto {
    private final int id;
    private final int roomTypeId;
    private final int numberOfBeds;
    private final int roomStatusId;
    private final int price;
    private final int hotelId;

    private RoomDto(Builder builder) {
        id = builder.id;
        roomTypeId = builder.roomTypeId;
        numberOfBeds = builder.numberOfBeds;
        roomStatusId = builder.roomStatusId;
        price = builder.price;
        hotelId = builder.hotelId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private int roomTypeId;
        private int numberOfBeds;
        private int roomStatusId;
        private int price;
        private int hotelId;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder roomTypeId(int val) {
            roomTypeId = val;
            return this;
        }

        public Builder numberOfBeds(int val) {
            numberOfBeds = val;
            return this;
        }

        public Builder roomStatusId(int val) {
            roomStatusId = val;
            return this;
        }

        public Builder price(int val) {
            price = val;
            return this;
        }

        public Builder hotelId(int val) {
            hotelId = val;
            return this;
        }

        public RoomDto build() {
            return new RoomDto(this);
        }
    }

    public int getId() {
        return id;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public int getRoomStatusId() {
        return roomStatusId;
    }

    public int getPrice() {
        return price;
    }

    public int getHotelId() {
        return hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDto roomDto = (RoomDto) o;
        return id == roomDto.id && roomTypeId == roomDto.roomTypeId && numberOfBeds == roomDto.numberOfBeds && roomStatusId == roomDto.roomStatusId && price == roomDto.price && hotelId == roomDto.hotelId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomTypeId, numberOfBeds, roomStatusId, price, hotelId);
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", roomTypeId=" + roomTypeId +
                ", numberOfBeds=" + numberOfBeds +
                ", roomStatusId=" + roomStatusId +
                ", price=" + price +
                ", hotelId=" + hotelId +
                '}';
    }
}
