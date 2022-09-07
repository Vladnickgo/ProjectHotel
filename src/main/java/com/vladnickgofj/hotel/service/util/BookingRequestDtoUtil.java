package com.vladnickgofj.hotel.service.util;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.BookingRequestDto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BookingRequestDtoUtil {
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 5;
    private static final String DEFAULT_SORTING_PARAMETER = "price";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";
    private final BookingRequestDto bookingRequestDto;

    public BookingRequestDtoUtil(BookingRequestDto bookingRequestDto) {
        this.bookingRequestDto = bookingRequestDto;
    }

    public String getSorting() {
        return initParameterValue(bookingRequestDto.getSorting(), DEFAULT_SORTING_PARAMETER);
    }

    public String getOrdering() {
        return initParameterValue(bookingRequestDto.getOrdering(), DEFAULT_ORDERING_PARAMETER);
    }

    public Integer getNumberOfPage() {
        return initParameterValue(bookingRequestDto.getNumberOfPage(), DEFAULT_PAGE_NUMBER);
    }

    public Integer getItemsOnPage() {
        return initParameterValue(bookingRequestDto.getItemsOnPage(), DEFAULT_ITEMS_ON_PAGE);
    }

    public Integer getUserId() {
        return bookingRequestDto.getUserId();
    }

    public String getStatusNotPaid() {
        return Objects.equals(bookingRequestDto.getSorting(), null) ? "notPaid" : bookingRequestDto.getStatusNotPaid();
    }

    public String getStatusPaid() {
        return bookingRequestDto.getStatusPaid();
    }

    public String getStatusCanceled() {
        return bookingRequestDto.getStatusCanceled();
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

    public Comparator<BookingDto> extractedComparator() {
        String sort = getSorting();
        return getOrdering().equals("ASC") ? initNaturalComparatorMap().get(sort) : initReverseComparatorMap().get(sort);
    }

    private Map<String, Comparator<BookingDto>> initReverseComparatorMap() {
        Map<String, Comparator<BookingDto>> reverseComparatorMap = new HashMap<>();
        reverseComparatorMap.put("price", Comparator.comparing(t -> t.getRoom().getPrice(), Comparator.reverseOrder()));
        reverseComparatorMap.put("number_of_beds", Comparator.comparing(t -> t.getRoom().getNumberOfBeds(), Comparator.reverseOrder()));
        reverseComparatorMap.put("type_name", Comparator.comparing(t -> t.getRoom().getRoomType().getTypeName(), Comparator.reverseOrder()));
        return reverseComparatorMap;
    }

    private Map<String, Comparator<BookingDto>> initNaturalComparatorMap() {
        Map<String, Comparator<BookingDto>> naturalComparatorMap = new HashMap<>();
        naturalComparatorMap.put("price", Comparator.comparing(t -> t.getRoom().getPrice(), Comparator.naturalOrder()));
        naturalComparatorMap.put("number_of_beds", Comparator.comparing(t -> t.getRoom().getNumberOfBeds(), Comparator.naturalOrder()));
        naturalComparatorMap.put("type_name", Comparator.comparing(t -> t.getRoom().getRoomType().getTypeName(), Comparator.naturalOrder()));
        return naturalComparatorMap;
    }

    public Integer[] getBookingStatusIds() {
        Integer[] bookingStatusArray = new Integer[3];
        bookingStatusArray[0] = Objects.equals(getStatusNotPaid(), "notPaid") ? 1 : 0;
        bookingStatusArray[1] = Objects.equals(getStatusPaid(), "paid") ? 2 : 0;
        bookingStatusArray[2] = Objects.equals(getStatusCanceled(), "canceled") ? 3 : 0;
        return bookingStatusArray;
    }

}
