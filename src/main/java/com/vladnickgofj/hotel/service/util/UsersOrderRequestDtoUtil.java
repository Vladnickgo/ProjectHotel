package com.vladnickgofj.hotel.service.util;

import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderRequestDto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UsersOrderRequestDtoUtil {
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 5;
    private static final String DEFAULT_SORTING_PARAMETER = "order_date";
    private static final String DEFAULT_ORDERING_PARAMETER = "DESC";
    private final UsersOrderRequestDto usersOrderRequestDto;

    public UsersOrderRequestDtoUtil(UsersOrderRequestDto usersOrderRequestDto) {
        this.usersOrderRequestDto = usersOrderRequestDto;
    }

    public String getStatusNotDone() {
        return Objects.equals(usersOrderRequestDto.getSorting(), null) && Objects.equals(getStatusCompleted(), null) ? "notDone" : usersOrderRequestDto.getStatusNotDone();
    }

    public String getStatusCompleted() {
        return usersOrderRequestDto.getStatusCompleted();
    }

    public String getSorting() {
        String sorting = usersOrderRequestDto.getSorting();
        return initSorting().getOrDefault(sorting, DEFAULT_SORTING_PARAMETER);
    }

    public String getOrdering() {
        String ordering = usersOrderRequestDto.getOrdering();
        return initOrdering().getOrDefault(ordering, DEFAULT_ORDERING_PARAMETER);
    }

    public Integer getNumberOfPage() {
        return initParameterValue(usersOrderRequestDto.getNumberOfPage(), DEFAULT_PAGE_NUMBER);
    }

    public Integer getItemsOnPage() {
        return initParameterValue(usersOrderRequestDto.getItemsOnPage(), DEFAULT_ITEMS_ON_PAGE);
    }

    public Integer[] getStatusStatement() {
        Integer[] roomStatusStatementsArray = new Integer[2];
        roomStatusStatementsArray[0] = Objects.equals(getStatusNotDone(), "notDone") ? 1 : 0;
        roomStatusStatementsArray[1] = Objects.equals(getStatusCompleted(), "completed") ? 2 : 0;
        return roomStatusStatementsArray;
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

    private Integer initParameterValue(String parameter, Integer defaultValue) {
        try {
            return Objects.equals(null, parameter) ? defaultValue : Integer.valueOf(parameter);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static Map<String, String> initSorting() {
        Map<String, String> sortingMap = new HashMap<>();
        sortingMap.put("order_date", "order_date");
        sortingMap.put("number_of_persons", "number_of_persons");
        sortingMap.put("date_start", "date_start");
        sortingMap.put("hotel_name", "hotel_name");
        sortingMap.put("type_name", "type_name");
        return sortingMap;
    }

    private static Map<String, String> initOrdering() {
        Map<String, String> orderingMap = new HashMap<>();
        orderingMap.put("ASC", "ASC");
        orderingMap.put("DESC", "DESC");
        return orderingMap;
    }

}
