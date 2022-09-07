package com.vladnickgofj.hotel.controller.dto;

import java.time.LocalDate;
import java.util.*;

import static com.vladnickgofj.hotel.ApplicationConstant.NUMBER_OF_DAYS_AVAILABLE_FOR_ORDER;

public class RoomStatusDtoRequest {

    private final String hotelId;
    private final String hotelName;
    private final String sorting;
    private final String ordering;
    private final String numberOfPage;
    private final String itemsOnPage;
    private final String statusFree;
    private final String statusBooked;
    private final String signInStr;
    private final String signOutStr;

    private RoomStatusDtoRequest(Builder builder) {
        hotelId = builder.hotelId;
        hotelName = builder.hotelName;
        sorting = builder.sorting;
        ordering = builder.ordering;
        numberOfPage = builder.numberOfPage;
        itemsOnPage = builder.itemsOnPage;
        statusFree = builder.statusFree;
        statusBooked = builder.statusBooked;
        signInStr = builder.signInStr;
        signOutStr = builder.signOutStr;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String hotelId;
        private String hotelName;
        private String sorting;
        private String ordering;
        private String numberOfPage;
        private String itemsOnPage;
        private String statusFree;
        private String statusBooked;
        private String signInStr;
        private String signOutStr;

        private Builder() {
        }

        public Builder hotelId(String val) {
            hotelId = val;
            return this;
        }

        public Builder hotelName(String val) {
            hotelName = val;
            return this;
        }

        public Builder sorting(String val) {
            sorting = val;
            return this;
        }

        public Builder ordering(String val) {
            ordering = val;
            return this;
        }

        public Builder numberOfPage(String val) {
            numberOfPage = val;
            return this;
        }

        public Builder itemsOnPage(String val) {
            itemsOnPage = val;
            return this;
        }

        public Builder statusFree(String val) {
            statusFree = val;
            return this;
        }

        public Builder statusBooked(String val) {
            statusBooked = val;
            return this;
        }

        public Builder signInStr(String val) {
            signInStr = val;
            return this;
        }

        public Builder signOutStr(String val) {
            signOutStr = val;
            return this;
        }

        public RoomStatusDtoRequest build() {
            return new RoomStatusDtoRequest(this);
        }
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getSorting() {
        return sorting;
    }

    public String getOrdering() {
        return ordering;
    }

    public String getNumberOfPage() {
        return numberOfPage;
    }

    public String getItemsOnPage() {
        return itemsOnPage;
    }

    public String getStatusFree() {
        return statusFree;
    }

    public String getStatusBooked() {
        return statusBooked;
    }

    public String getSignInStr() {
        return signInStr;
    }

    public String getSignOutStr() {
        return signOutStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomStatusDtoRequest that = (RoomStatusDtoRequest) o;
        return Objects.equals(hotelId, that.hotelId) && Objects.equals(hotelName, that.hotelName) && Objects.equals(sorting, that.sorting) && Objects.equals(ordering, that.ordering) && Objects.equals(numberOfPage, that.numberOfPage) && Objects.equals(itemsOnPage, that.itemsOnPage) && Objects.equals(statusFree, that.statusFree) && Objects.equals(statusBooked, that.statusBooked) && Objects.equals(signInStr, that.signInStr) && Objects.equals(signOutStr, that.signOutStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, hotelName, sorting, ordering, numberOfPage, itemsOnPage, statusFree, statusBooked, signInStr, signOutStr);
    }

    @Override
    public String toString() {
        return "RoomStatusDtoRequest{" +
                "hotelId='" + hotelId + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", sorting='" + sorting + '\'' +
                ", ordering='" + ordering + '\'' +
                ", numberOfPage='" + numberOfPage + '\'' +
                ", itemsOnPage='" + itemsOnPage + '\'' +
                ", statusFree='" + statusFree + '\'' +
                ", statusBooked='" + statusBooked + '\'' +
                ", signInStr='" + signInStr + '\'' +
                ", signOutStr='" + signOutStr + '\'' +
                '}';
    }
}
