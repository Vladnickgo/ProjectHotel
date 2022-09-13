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
        String sorting = bookingRequestDto.getSorting();
        return initSorting().getOrDefault(sorting, DEFAULT_SORTING_PARAMETER);
    }

    public String getOrdering() {
        String ordering = bookingRequestDto.getOrdering();
        return initOrdering().getOrDefault(ordering, DEFAULT_ORDERING_PARAMETER);
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
        return Objects.equals(bookingRequestDto.getSorting(), null) &&
                Objects.equals(bookingRequestDto.getStatusPaid(), null) &&
                Objects.equals(bookingRequestDto.getStatusCanceled(), null)
                ? "notPaid" : bookingRequestDto.getStatusNotPaid();
    }

    public String getStatusPaid() {
        return bookingRequestDto.getStatusPaid();
    }

    public String getStatusCanceled() {
        return bookingRequestDto.getStatusCanceled();
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
        Integer[] bookingStatusArray = new Integer[4];
        bookingStatusArray[0] = Objects.equals(getStatusNotPaid(), "notPaid") ? 1 : 0;
        bookingStatusArray[1] = Objects.equals(getStatusPaid(), "paid") ? 2 : 0;
        bookingStatusArray[2] = Objects.equals(getStatusCanceled(), "canceled") ? 3 : 0;
        bookingStatusArray[3] = Objects.equals(getStatusCanceled(), "canceled") ? 4 : 0;
        return bookingStatusArray;
    }

    private static Map<String, String> initSorting() {
        Map<String, String> sortingMap = new HashMap<>();
        sortingMap.put("price", "price");
        sortingMap.put("number_of_beds", "number_of_beds");
        sortingMap.put("type_name", "type_name");
        sortingMap.put("nights", "nights");
        sortingMap.put("book_time", "book_time");
        return sortingMap;
    }

    private static Map<String, String> initOrdering() {
        Map<String, String> orderingMap = new HashMap<>();
        orderingMap.put("ASC", "ASC");
        orderingMap.put("DESC", "DESC");
        return orderingMap;
    }

}
