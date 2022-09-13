package com.vladnickgofj.hotel.controller.dto;

import java.util.Objects;

public class RoomDto {
    private final Integer id;
    private final RoomTypeDto roomType;
    private final Integer numberOfBeds;
    private final Integer price;
    private final HotelDto hotel;

    private RoomDto(Builder builder) {
        id = builder.id;
        roomType = builder.roomType;
        numberOfBeds = builder.numberOfBeds;
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

        public Builder price(Integer val) {
            price = val;
            return this;
        }

        public Builder hotel(HotelDto val) {
            hotel = val;
            return this;
        }

        public RoomDto build() {
            return new RoomDto(this);
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
        RoomDto roomDto = (RoomDto) o;
        return Objects.equals(id, roomDto.id) && Objects.equals(roomType, roomDto.roomType) && Objects.equals(numberOfBeds, roomDto.numberOfBeds) && Objects.equals(price, roomDto.price) && Objects.equals(hotel, roomDto.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomType, numberOfBeds, price, hotel);
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", roomType=" + roomType +
                ", numberOfBeds=" + numberOfBeds +
                ", price=" + price +
                ", hotel=" + hotel +
                '}';
    }
}
