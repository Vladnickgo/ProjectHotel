package com.vladnickgofj.hotel.controller.dto;

import java.util.Objects;

public class BookingRequestDto {

    private final String numberOfPage;
    private final String itemsOnPage;
    private final Integer userId;
    private final String sorting;
    private final String ordering;
    private final String statusNotPaid;
    private final String statusPaid;
    private final String statusCanceled;

    private BookingRequestDto(Builder builder) {
        numberOfPage = builder.numberOfPage;
        itemsOnPage = builder.itemsOnPage;
        userId = builder.userId;
        sorting = builder.sorting;
        ordering = builder.ordering;
        statusNotPaid = builder.statusNotPaid;
        statusPaid = builder.statusPaid;
        statusCanceled = builder.statusCanceled;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String numberOfPage;
        private String itemsOnPage;
        private Integer userId;
        private String sorting;
        private String ordering;
        private String statusNotPaid;
        private String statusPaid;
        private String statusCanceled;

        private Builder() {
        }

        public Builder numberOfPage(String val) {
            numberOfPage = val;
            return this;
        }

        public Builder itemsOnPage(String val) {
            itemsOnPage = val;
            return this;
        }

        public Builder userId(Integer val) {
            userId = val;
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

        public Builder statusNotPaid(String val) {
            statusNotPaid = val;
            return this;
        }

        public Builder statusPaid(String val) {
            statusPaid = val;
            return this;
        }

        public Builder statusCanceled(String val) {
            statusCanceled = val;
            return this;
        }

        public BookingRequestDto build() {
            return new BookingRequestDto(this);
        }
    }

    public String getNumberOfPage() {
        return numberOfPage;
    }

    public String getItemsOnPage() {
        return itemsOnPage;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getSorting() {
        return sorting;
    }

    public String getOrdering() {
        return ordering;
    }

    public String getStatusNotPaid() {
        return statusNotPaid;
    }

    public String getStatusPaid() {
        return statusPaid;
    }

    public String getStatusCanceled() {
        return statusCanceled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingRequestDto that = (BookingRequestDto) o;
        return Objects.equals(numberOfPage, that.numberOfPage) && Objects.equals(itemsOnPage, that.itemsOnPage) && Objects.equals(userId, that.userId) && Objects.equals(sorting, that.sorting) && Objects.equals(ordering, that.ordering) && Objects.equals(statusNotPaid, that.statusNotPaid) && Objects.equals(statusPaid, that.statusPaid) && Objects.equals(statusCanceled, that.statusCanceled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPage, itemsOnPage, userId, sorting, ordering, statusNotPaid, statusPaid, statusCanceled);
    }

    @Override
    public String toString() {
        return "BookingRequestDto{" +
                "numberOfPage='" + numberOfPage + '\'' +
                ", itemsOnPage='" + itemsOnPage + '\'' +
                ", userId=" + userId +
                ", sorting='" + sorting + '\'' +
                ", ordering='" + ordering + '\'' +
                ", statusNotPaid='" + statusNotPaid + '\'' +
                ", statusPaid='" + statusPaid + '\'' +
                ", statusCanceled='" + statusCanceled + '\'' +
                '}';
    }
}
