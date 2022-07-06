package com.vladnickgofj.hotel.controller.dto;

import java.util.Objects;

public class RoomDto {
    private final Integer id;
    private final Integer roomTypeId;
    private final Integer numberOfBeds;
    private final Integer roomStatusId;
    private final Integer price;
    private final Integer hotelId;

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
        private Integer id;
        private Integer roomTypeId;
        private Integer numberOfBeds;
        private Integer roomStatusId;
        private Integer price;
        private Integer hotelId;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder roomTypeId(Integer val) {
            roomTypeId = val;
            return this;
        }

        public Builder numberOfBeds(Integer val) {
            numberOfBeds = val;
            return this;
        }

        public Builder roomStatusId(Integer val) {
            roomStatusId = val;
            return this;
        }

        public Builder price(Integer val) {
            price = val;
            return this;
        }

        public Builder hotelId(Integer val) {
            hotelId = val;
            return this;
        }

        public RoomDto build() {
            return new RoomDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public Integer getRoomStatusId() {
        return roomStatusId;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDto roomDto = (RoomDto) o;
        return Objects.equals(id, roomDto.id) && Objects.equals(roomTypeId, roomDto.roomTypeId) && Objects.equals(numberOfBeds, roomDto.numberOfBeds) && Objects.equals(roomStatusId, roomDto.roomStatusId) && Objects.equals(price, roomDto.price) && Objects.equals(hotelId, roomDto.hotelId);
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
