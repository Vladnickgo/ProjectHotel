package com.vladnickgofj.hotel.controller.dto;

import java.util.Objects;

public class RoomDtoResponse {
    private final Integer id;
    private final RoomTypeDto roomType;
    private final Integer numberOfBeds;
    private final RoomStatusDto roomStatus;
    private final Integer price;
    private final HotelDto hotel;

    private RoomDtoResponse(Builder builder) {
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
        private Integer id;
        private RoomTypeDto roomType;
        private Integer numberOfBeds;
        private RoomStatusDto roomStatus;
        private Integer price;
        private HotelDto hotel;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder roomType(RoomTypeDto val) {
            roomType = val;
            return this;
        }

        public Builder numberOfBeds(Integer val) {
            numberOfBeds = val;
            return this;
        }

        public Builder roomStatus(RoomStatusDto val) {
            roomStatus = val;
            return this;
        }

        public Builder price(Integer val) {
            price = val;
            return this;
        }

        public Builder hotel(HotelDto val) {
            hotel = val;
            return this;
        }

        public RoomDtoResponse build() {
            return new RoomDtoResponse(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public RoomTypeDto getRoomType() {
        return roomType;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public RoomStatusDto getRoomStatus() {
        return roomStatus;
    }

    public Integer getPrice() {
        return price;
    }

    public HotelDto getHotel() {
        return hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDtoResponse that = (RoomDtoResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(roomType, that.roomType) && Objects.equals(numberOfBeds, that.numberOfBeds) && Objects.equals(roomStatus, that.roomStatus) && Objects.equals(price, that.price) && Objects.equals(hotel, that.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomType, numberOfBeds, roomStatus, price, hotel);
    }

    @Override
    public String toString() {
        return "RoomDtoResponse{" +
                "id=" + id +
                ", roomType=" + roomType +
                ", numberOfBeds=" + numberOfBeds +
                ", roomStatus=" + roomStatus +
                ", price=" + price +
                ", hotel=" + hotel +
                '}';
    }
}
