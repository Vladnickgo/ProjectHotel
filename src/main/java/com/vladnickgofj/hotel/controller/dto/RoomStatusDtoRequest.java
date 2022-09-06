package com.vladnickgofj.hotel.controller.dto;

import java.time.LocalDate;
import java.util.*;

import static com.vladnickgofj.hotel.ApplicationConstant.NUMBER_OF_DAYS_AVAILABLE_FOR_ORDER;

public class RoomStatusDtoRequest {

    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ROOMS_ON_PAGE = 5;
    private static final String DEFAULT_SORTING_PARAMETER = "price";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";
    private static final String DEFAULT_SIGN_IN_VALUE = String.valueOf(LocalDate.now());
    private static final String DEFAULT_SIGN_OUT_VALUE = String.valueOf(LocalDate.now().plusDays(1));

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

    public Integer getHotelId() {
        return Integer.valueOf(hotelId);
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getSorting() {
        return initParameterValue(sorting, DEFAULT_SORTING_PARAMETER);
    }

    public String getOrdering() {
        return initParameterValue(ordering, DEFAULT_ORDERING_PARAMETER);
    }

    public Integer getNumberOfPage() {
        return initParameterValue(numberOfPage, DEFAULT_PAGE_NUMBER);
    }

    public Integer getItemsOnPage() {
        return initParameterValue(itemsOnPage, DEFAULT_ROOMS_ON_PAGE);
    }

    public String getStatusFree() {
        return Objects.equals(signInStr, null) ? "free" : statusFree;
    }

    public String getStatusBooked() {
        return statusBooked;
    }

    public LocalDate getSignIn() {
        return LocalDate.parse(initParameterValue(signInStr, DEFAULT_SIGN_IN_VALUE));
    }

    public LocalDate getMinSignIn() {
        return LocalDate.now();
    }

    public LocalDate getMaxSignIn() {
        return LocalDate.now().plusDays(NUMBER_OF_DAYS_AVAILABLE_FOR_ORDER);
    }

    public LocalDate getSignOut() {
        return LocalDate.parse(initParameterValue(signOutStr, DEFAULT_SIGN_OUT_VALUE));
    }

    public LocalDate getMinSignOut() {
        return getSignIn().plusDays(1);
    }

    public LocalDate getMaxSignOut() {
        return getMaxSignIn().plusDays(1);
    }

    public PagenableElementsDto getPagenableElementsDto() {
        return PagenableElementsDto.newBuilder()
                .numberOfPage(getNumberOfPage())
                .itemsOnPage(getItemsOnPage())
                .build();
    }

    private String initParameterValue(String parameter, String defaultValue) {
        return Objects.equals(null, parameter) ? defaultValue : parameter;
    }


    private Integer initParameterValue(String parameter, Integer defaultValue) {
        try {
            return Objects.equals(null, parameter) ? defaultValue : Integer.valueOf(parameter);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public String getQuerySubstitute() {
        return Objects.equals(getStatusFree(), "free") ?
                ("booked".equals(getStatusBooked()) ?
                        "AND (rss.status_statement_id = 1 OR rss.status_statement_id = 2)" : "AND rss.status_statement_id = 1") :
                "booked".equals(getStatusBooked()) ?
                        "AND rss.status_statement_id = 2" : "AND rss.status_statement_id = 0";
    }

    public Comparator<RoomStatusDto> extractedComparator() {
        String sort = getSorting();
        return getOrdering().equals("ASC") ? initNaturalComparatorMap().get(sort) : initReverseComparatorMap().get(sort);
    }

    private Map<String, Comparator<RoomStatusDto>> initReverseComparatorMap() {
        Map<String, Comparator<RoomStatusDto>> reverseComparatorMap = new HashMap<>();
        reverseComparatorMap.put("price", Comparator.comparing(t -> t.getRoomDtoResponse().getPrice(), Comparator.reverseOrder()));
        reverseComparatorMap.put("number_of_beds", Comparator.comparing(t -> t.getRoomDtoResponse().getNumberOfBeds(), Comparator.reverseOrder()));
        reverseComparatorMap.put("type_name", Comparator.comparing(t -> t.getRoomDtoResponse().getRoomType().getTypeName(), Comparator.reverseOrder()));
        return reverseComparatorMap;
    }

    private Map<String, Comparator<RoomStatusDto>> initNaturalComparatorMap() {
        Map<String, Comparator<RoomStatusDto>> naturalComparatorMap = new HashMap<>();
        naturalComparatorMap.put("price", Comparator.comparing(t -> t.getRoomDtoResponse().getPrice(), Comparator.naturalOrder()));
        naturalComparatorMap.put("number_of_beds", Comparator.comparing(t -> t.getRoomDtoResponse().getNumberOfBeds(), Comparator.naturalOrder()));
        naturalComparatorMap.put("type_name", Comparator.comparing(t -> t.getRoomDtoResponse().getRoomType().getTypeName(), Comparator.naturalOrder()));
        return naturalComparatorMap;
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
