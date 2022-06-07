package com.vladnickgofj.hotel.controller.dto;

import java.util.Objects;

public class PaginateHotelDto {
    private final int hotelsOnPage;
    private final int numberOfPage;

    private PaginateHotelDto(Builder builder) {
        hotelsOnPage = builder.hotelsOnPage;
        numberOfPage = builder.numberOfPage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int hotelsOnPage;
        private int numberOfPage;

        private Builder() {
        }

        public Builder hotelsOnPage(int val) {
            hotelsOnPage = val;
            return this;
        }

        public Builder numberOfPage(int val) {
            numberOfPage = val;
            return this;
        }

        public PaginateHotelDto build() {
            return new PaginateHotelDto(this);
        }
    }

    public int getHotelsOnPage() {
        return hotelsOnPage;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginateHotelDto that = (PaginateHotelDto) o;
        return hotelsOnPage == that.hotelsOnPage && numberOfPage == that.numberOfPage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelsOnPage, numberOfPage);
    }

    @Override
    public String toString() {
        return "PaginateHotelDto{" +
                "hotelsOnPage=" + hotelsOnPage +
                ", numberOfPage=" + numberOfPage +
                '}';
    }
}
