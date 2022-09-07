package com.vladnickgofj.hotel.controller.dto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UsersOrderRequestDto {

    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 5;
    private static final String DEFAULT_SORTING_PARAMETER = "order_date";
    private static final String DEFAULT_ORDERING_PARAMETER = "DESC";


    private final String sorting;
    private final String ordering;
    private final String numberOfPage;
    private final String itemsOnPage;
    private final String statusNotDone;
    private final String statusCompleted;

    private UsersOrderRequestDto(Builder builder) {
        sorting = builder.sorting;
        ordering = builder.ordering;
        numberOfPage = builder.numberOfPage;
        itemsOnPage = builder.itemsOnPage;
        statusNotDone = builder.statusNotDone;
        statusCompleted = builder.statusCompleted;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String sorting;
        private String ordering;
        private String numberOfPage;
        private String itemsOnPage;
        private String statusNotDone;
        private String statusCompleted;

        private Builder() {
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

        public Builder statusNotDone(String val) {
            statusNotDone = val;
            return this;
        }

        public Builder statusCompleted(String val) {
            statusCompleted = val;
            return this;
        }

        public UsersOrderRequestDto build() {
            return new UsersOrderRequestDto(this);
        }
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

    public String getStatusNotDone() {
        return statusNotDone;
    }

    public String getStatusCompleted() {
        return statusCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersOrderRequestDto that = (UsersOrderRequestDto) o;
        return Objects.equals(sorting, that.sorting) && Objects.equals(ordering, that.ordering) && Objects.equals(numberOfPage, that.numberOfPage) && Objects.equals(itemsOnPage, that.itemsOnPage) && Objects.equals(statusNotDone, that.statusNotDone) && Objects.equals(statusCompleted, that.statusCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sorting, ordering, numberOfPage, itemsOnPage, statusNotDone, statusCompleted);
    }

    @Override
    public String toString() {
        return "UsersOrderRequestDto{" +
                "sorting='" + sorting + '\'' +
                ", ordering='" + ordering + '\'' +
                ", numberOfPage='" + numberOfPage + '\'' +
                ", itemsOnPage='" + itemsOnPage + '\'' +
                ", statusNotDone='" + statusNotDone + '\'' +
                ", statusCompleted='" + statusCompleted + '\'' +
                '}';
    }
}
