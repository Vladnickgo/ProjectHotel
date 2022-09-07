package com.vladnickgofj.hotel.service.util;

import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDtoRequest;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.vladnickgofj.hotel.ApplicationConstant.NUMBER_OF_DAYS_AVAILABLE_FOR_ORDER;

public class RoomStatusDtoRequestUtil {
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ROOMS_ON_PAGE = 5;
    private static final String DEFAULT_SORTING_PARAMETER = "price";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";
    private static final String DEFAULT_SIGN_IN_VALUE = String.valueOf(LocalDate.now());
    private static final String DEFAULT_SIGN_OUT_VALUE = String.valueOf(LocalDate.now().plusDays(1));
    private final RoomStatusDtoRequest roomStatusDtoRequest;

    public RoomStatusDtoRequestUtil(RoomStatusDtoRequest roomStatusDtoRequest) {
        this.roomStatusDtoRequest = roomStatusDtoRequest;
    }


    public Integer getHotelId() {
        String hotelId = roomStatusDtoRequest.getHotelId();
        return Integer.valueOf(hotelId);
    }

    public String getHotelName() {
        return roomStatusDtoRequest.getHotelName();
    }

    public String getSorting() {

        return initParameterValue(roomStatusDtoRequest.getSorting(), DEFAULT_SORTING_PARAMETER);
    }

    public String getOrdering() {
        String ordering = roomStatusDtoRequest.getOrdering();
        return initParameterValue(ordering, DEFAULT_ORDERING_PARAMETER);
    }

    public Integer getNumberOfPage() {
        String numberOfPage = roomStatusDtoRequest.getNumberOfPage();
        return initParameterValue(numberOfPage, DEFAULT_PAGE_NUMBER);
    }

    public Integer getItemsOnPage() {
        String itemsOnPage = roomStatusDtoRequest.getItemsOnPage();
        return initParameterValue(itemsOnPage, DEFAULT_ROOMS_ON_PAGE);
    }

    public String getStatusFree() {
        String signInStr = roomStatusDtoRequest.getSignInStr();
        String statusFree = roomStatusDtoRequest.getStatusFree();
        return Objects.equals(signInStr, null) ? "free" : statusFree;
    }

    public String getStatusBooked() {
        return roomStatusDtoRequest.getStatusBooked();
    }

    public LocalDate getSignIn() {
        String signInStr = roomStatusDtoRequest.getSignInStr();
        return LocalDate.parse(initParameterValue(signInStr, DEFAULT_SIGN_IN_VALUE));
    }

    public LocalDate getMinSignIn() {
        return LocalDate.now();
    }

    public LocalDate getMaxSignIn() {
        return LocalDate.now().plusDays(NUMBER_OF_DAYS_AVAILABLE_FOR_ORDER);
    }

    public LocalDate getSignOut() {
        String signOutStr = roomStatusDtoRequest.getSignOutStr();
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

    public Integer[] getRoomStatusStatementIds() {
        Integer[] roomStatusStatementsArray=new Integer[2];
        roomStatusStatementsArray[0]= Objects.equals(getStatusFree(), "free") ? 1:0;
roomStatusStatementsArray[1]=        Objects.equals(getStatusBooked(),"booked")?2:0;
//                ("booked".equals(getStatusBooked()) ?
//                        "AND (rss.status_statement_id = 1 OR rss.status_statement_id = 2)" : "AND rss.status_statement_id = 1") :
//                "booked".equals(getStatusBooked()) ?
//                        "AND rss.status_statement_id = 2" : "AND rss.status_statement_id = 0";
        return roomStatusStatementsArray;
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


}
