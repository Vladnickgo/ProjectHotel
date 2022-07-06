package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.PaginateRoomDto;
import com.vladnickgofj.hotel.controller.dto.RoomDtoResponse;
import com.vladnickgofj.hotel.service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowRoomsCommand implements Command {

    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ROOMS_ON_PAGE = 5;
    ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    RoomService roomService = contextInjector.getRoomService();
    PaginateRoomDto paginateRoomDto;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = Integer.valueOf(request.getParameter("hotelId"));
        String name = request.getParameter("hotelName");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String roomsOnPage = request.getParameter("roomsOnPage");
        String actualPageNumber = request.getParameter("actualPageNumber");
        String changePage = request.getParameter("changePage");

        Integer pageNumber = parseStringToInt(actualPageNumber, DEFAULT_PAGE_NUMBER);
        paginateRoomDto = getBuild(roomsOnPage, pageNumber);
        Integer numberOfPages = roomService.getNumberOfPages(paginateRoomDto, id);
        pageNumber = getActualPageNumber(changePage, pageNumber, numberOfPages);
        paginateRoomDto = getBuild(roomsOnPage, pageNumber);
        sorting = initialSorting(sorting);
        ordering = initialOrdering(ordering);

        Comparator<RoomDtoResponse> extractedComparator = extracted(sorting, ordering);
        List<RoomDtoResponse> listOfRooms = roomService.findAll(id, sorting, ordering, extractedComparator, paginateRoomDto);
        request.setAttribute("hotelId", id);
        request.setAttribute("hotelName", name);
        request.setAttribute("sorting", sorting);
        request.setAttribute("ordering", ordering);
        request.setAttribute("roomsOnPage", roomsOnPage);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("listOfRooms", listOfRooms);
        request.setAttribute("actualPageNumber", pageNumber);

        return PagesConstant.SHOW_ROOMS;
    }

    private Map<String, Integer> extractedIncrement() {
        Map<String, Integer> map = new HashMap<>();
        map.put(">", 1);
        map.put("<", -1);
        map.put(null, 0);
        return map;
    }

    private Integer getActualPageNumber(String changePage, Integer pageNumber, Integer numberOfPages) {
        pageNumber += extractedIncrement().get(changePage);
        return pageNumber < 1 ? 1 : pageNumber > numberOfPages ? numberOfPages : pageNumber;
    }

    private PaginateRoomDto getBuild(String roomsOnPage, Integer numberOfPage) {
        return PaginateRoomDto.newBuilder()
                .numberOfPage(numberOfPage)
                .roomsOnPage(parseStringToInt(roomsOnPage, DEFAULT_ROOMS_ON_PAGE))
                .build();
    }

    private Integer parseStringToInt(String page, Integer defaultValue) {
        Integer intValue;
        try {
            intValue = Integer.valueOf(page);
        } catch (NumberFormatException e) {
            intValue = defaultValue;
        }
        return intValue;
    }

    private String initialOrdering(String ordering) {
        return ordering == null ? "ASC" : ordering;
    }

    private String initialSorting(String sorting) {
        return sorting == null ? "price" : sorting;
    }

    private Comparator<RoomDtoResponse> extracted(String sorting, String ordering) {
        return ordering.equals("ASC") ? initNaturalComparatorMap().get(sorting) : initReverseComparatorMap().get(sorting);
    }

    private Map<String, Comparator<RoomDtoResponse>> initReverseComparatorMap() {
        Map<String, Comparator<RoomDtoResponse>> reverseComparatorMap = new HashMap<>();
        reverseComparatorMap.put("price", Comparator.comparing(RoomDtoResponse::getPrice, Comparator.reverseOrder()));
        reverseComparatorMap.put("number_of_beds", Comparator.comparing(RoomDtoResponse::getNumberOfBeds, Comparator.reverseOrder()));
        reverseComparatorMap.put("type_name", Comparator.comparing(roomDtoResponse -> roomDtoResponse.getRoomType().getTypeName(), Comparator.reverseOrder()));
        reverseComparatorMap.put("status_name", Comparator.comparing(roomDtoResponse -> roomDtoResponse.getRoomStatus().getStatusName(), Comparator.reverseOrder()));
        return reverseComparatorMap;
    }

    private Map<String, Comparator<RoomDtoResponse>> initNaturalComparatorMap() {
        Map<String, Comparator<RoomDtoResponse>> naturalComparatorMap = new HashMap<>();
        naturalComparatorMap.put("price", Comparator.comparing(RoomDtoResponse::getPrice, Comparator.naturalOrder()));
        naturalComparatorMap.put("number_of_beds", Comparator.comparing(RoomDtoResponse::getNumberOfBeds, Comparator.naturalOrder()));
        naturalComparatorMap.put("type_name", Comparator.comparing(roomDtoResponse -> roomDtoResponse.getRoomType().getTypeName(), Comparator.naturalOrder()));
        naturalComparatorMap.put("status_name", Comparator.comparing(roomDtoResponse -> roomDtoResponse.getRoomStatus().getStatusName(), Comparator.naturalOrder()));
        return naturalComparatorMap;
    }

}