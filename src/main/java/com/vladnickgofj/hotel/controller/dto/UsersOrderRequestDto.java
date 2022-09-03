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

    public String getStatusNotDone() {
        return Objects.equals(sorting, null) ? "notDone" : statusNotDone;
    }

    public String getStatusCompleted() {
        return statusCompleted;
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
        return initParameterValue(itemsOnPage, DEFAULT_ITEMS_ON_PAGE);
    }


    public PagenableElementsDto getPagenableElementsDto() {
        return PagenableElementsDto.newBuilder()
                .numberOfPage(getNumberOfPage())
                .itemsOnPage(getItemsOnPage())
                .build();
    }

    public String getQuerySubstitute() {
        return Objects.equals(getStatusNotDone(), "notDone") ?
                ("completed".equals(getStatusCompleted()) ?
                        "WHERE uos.order_status_id=1 OR uos.order_status_id=2 " : "WHERE uos.order_status_id=1 ") :
                "completed".equals(getStatusCompleted()) ? "WHERE uos.order_status_id=2 " : "WHERE uos.order_status_id=0 ";
    }

    public Comparator<UsersOrderDto> extractedComparator() {
        String sort = getSorting();
        return getOrdering().equals("ASC") ? initNaturalComparatorMap().get(sort) : initReverseComparatorMap().get(sort);
    }

    private Map<String, Comparator<UsersOrderDto>> initReverseComparatorMap() {
        Map<String, Comparator<UsersOrderDto>> reverseComparatorMap = new HashMap<>();
        reverseComparatorMap.put("order_date", Comparator.comparing(UsersOrderDto::getOrderDate, Comparator.reverseOrder()));
        reverseComparatorMap.put("hotel_name", Comparator.comparing(t -> t.getHotelDto().getName(), Comparator.reverseOrder()));
        reverseComparatorMap.put("type_name", Comparator.comparing(t -> t.getRoomDtoResponse().getRoomType().getTypeName(), Comparator.reverseOrder()));
        reverseComparatorMap.put("number_of_persons", Comparator.comparing(UsersOrderDto::getNumberOfPersons, Comparator.reverseOrder()));
        return reverseComparatorMap;
    }

    private Map<String, Comparator<UsersOrderDto>> initNaturalComparatorMap() {
        Map<String, Comparator<UsersOrderDto>> naturalComparatorMap = new HashMap<>();
        naturalComparatorMap.put("order_date", Comparator.comparing(UsersOrderDto::getOrderDate, Comparator.naturalOrder()));
        naturalComparatorMap.put("hotel_name", Comparator.comparing(t -> t.getHotelDto().getName(), Comparator.naturalOrder()));
        naturalComparatorMap.put("type_name", Comparator.comparing(t -> t.getRoomDtoResponse().getRoomType().getTypeName(), Comparator.naturalOrder()));
        naturalComparatorMap.put("number_of_persons", Comparator.comparing(UsersOrderDto::getNumberOfPersons, Comparator.naturalOrder()));
        return naturalComparatorMap;
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
