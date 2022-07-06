package com.vladnickgofj.hotel.controller.dto;

import java.util.Objects;

public class PaginateRoomDto {
    private final Integer roomsOnPage;
    private final Integer numberOfPage;

    private PaginateRoomDto(Builder builder) {
        roomsOnPage = builder.roomsOnPage;
        numberOfPage = builder.numberOfPage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer roomsOnPage;
        private Integer numberOfPage;

        private Builder() {
        }

        public Builder roomsOnPage(Integer val) {
            roomsOnPage = val;
            return this;
        }

        public Builder numberOfPage(Integer val) {
            numberOfPage = val;
            return this;
        }

        public PaginateRoomDto build() {
            return new PaginateRoomDto(this);
        }
    }

    public Integer getRoomsOnPage() {
        return roomsOnPage;
    }

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginateRoomDto that = (PaginateRoomDto) o;
        return Objects.equals(roomsOnPage, that.roomsOnPage) && Objects.equals(numberOfPage, that.numberOfPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomsOnPage, numberOfPage);
    }

    @Override
    public String toString() {
        return "PaginateRoomDto{" +
                "roomsOnPage=" + roomsOnPage +
                ", numberOfPage=" + numberOfPage +
                '}';
    }
}
