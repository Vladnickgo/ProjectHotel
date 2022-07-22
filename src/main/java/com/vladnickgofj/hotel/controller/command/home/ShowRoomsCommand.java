package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.entity.StatusStatement;
import com.vladnickgofj.hotel.service.RoomStatusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class ShowRoomsCommand implements Command {

    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ROOMS_ON_PAGE = 5;
    private static final String DEFAULT_SORTING_PARAMETER = "price";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";

    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RoomStatusService roomStatusService = contextInjector.getRoomStatusService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String hotelId = request.getParameter("hotelId");
        String name = request.getParameter("hotelName");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String numberOfPage = request.getParameter("numberOfPage");
        String statusFree = request.getParameter("statusFree");
        String statusBooked = request.getParameter("statusBooked");
        statusFree = request.getParameter("signIn") == null ? "free" : statusFree;
        LocalDate signIn = LocalDate.parse(request.getParameter("signIn") == null ? String.valueOf(LocalDate.now()) : request.getParameter("signIn"));
        LocalDate minSignIn = LocalDate.now();
        LocalDate maxSignIn = LocalDate.now().plusMonths(1);
        LocalDate signOut = LocalDate.parse(request.getParameter("signOut") == null ? String.valueOf(LocalDate.now().plusDays(1)) : request.getParameter("signOut"));
        LocalDate minSignOut = signIn.plusDays(1);
        LocalDate maxSignOut = LocalDate.now().plusMonths(1).plusDays(1);

        Integer id = Integer.valueOf(hotelId);
        Integer numberOfPageInteger = initParameterValue(numberOfPage, DEFAULT_PAGE_NUMBER);
        Integer recordsOnPageInteger = initParameterValue(recordsOnPage, DEFAULT_ROOMS_ON_PAGE);
        Integer statusStatementId = 1;
        RoomStatusDto roomStatusDto = RoomStatusDto.newBuilder()
                .dateStart(signIn)
                .dateEnd(signOut)
                .statusStatement(StatusStatement.newBuilder()
                        .statusStatementId(statusStatementId)
                        .build())
                .build();
        PagenableElementsDto pagenableElementsDto = getPagenableElementsDto(recordsOnPageInteger, numberOfPageInteger);
        String roomStatusQuerySubstitute = getQuerySubstitute(statusFree, statusBooked);

        Integer totalPages = roomStatusService.getNumberOfPages(pagenableElementsDto, id, roomStatusQuerySubstitute, roomStatusDto);
        sorting = initParameterValue(sorting, DEFAULT_SORTING_PARAMETER);
        ordering = initParameterValue(ordering, DEFAULT_ORDERING_PARAMETER);

        Comparator<RoomStatusDto> extractedComparator = extractedComparator(sorting, ordering);
        List<RoomStatusDto> roomStatusList = roomStatusService.findAll(id, roomStatusDto, roomStatusQuerySubstitute, pagenableElementsDto, sorting, ordering, extractedComparator);

        request.setAttribute("hotelId", id);
        request.setAttribute("hotelName", name);
        request.setAttribute("sorting", sorting);
        request.setAttribute("ordering", ordering);
        request.setAttribute("recordsOnPage", recordsOnPageInteger);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listOfRooms", roomStatusList);
        request.setAttribute("numberOfPage", numberOfPageInteger);
        request.setAttribute("signIn", signIn);
        request.setAttribute("signOut", signOut);
        request.setAttribute("statusFree", statusFree);
        request.setAttribute("statusBooked", statusBooked);
        request.setAttribute("minSignIn", minSignIn);
        request.setAttribute("maxSignIn", maxSignIn);
        request.setAttribute("minSignOut", minSignOut);
        request.setAttribute("maxSignOut", maxSignOut);

        System.out.println("------------------------------------");
        roomStatusList.forEach(System.out::println);
        System.out.println("------------------------------------");

        return PagesConstant.SHOW_ROOMS;
    }

    private PagenableElementsDto getPagenableElementsDto(Integer roomsOnPage, Integer numberOfPage) {
        return PagenableElementsDto.newBuilder()
                .numberOfPage(numberOfPage)
                .itemsOnPage(roomsOnPage)
                .build();
    }

    private Integer initParameterValue(String parameter, Integer defaultValue) {
        try {
            return Objects.equals(null, parameter) ? defaultValue : Integer.valueOf(parameter);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String initParameterValue(String parameter, String defaultValue) {
        return Objects.equals(null, parameter) ? defaultValue : parameter;
    }

    private String getQuerySubstitute(String statusFree, String statusBooked) {
        return Objects.equals(statusFree, "free") ?
                ("booked".equals(statusBooked) ?
                        "AND (rss.status_statement_id = 1 OR rss.status_statement_id = 2)" : "AND rss.status_statement_id = 1") :
                "booked".equals(statusBooked) ?
                        "AND rss.status_statement_id = 2" : "AND rss.status_statement_id = 0";
    }

    private Comparator<RoomStatusDto> extractedComparator(String sorting, String ordering) {
        return ordering.equals("ASC") ? initNaturalComparatorMap().get(sorting) : initReverseComparatorMap().get(sorting);
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