package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;
import com.vladnickgofj.hotel.service.HotelService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShowHotelsCommand implements Command {

    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_HOTELS_ON_PAGE = 8;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numberOfPage = request.getParameter("numberOfPage");
        String recordsOnPage = request.getParameter("recordsOnPage");
        Integer numberOfPageInteger = parseStringToInt(numberOfPage, DEFAULT_PAGE_NUMBER);
        Integer recordsOnPageInteger = parseStringToInt(recordsOnPage, DEFAULT_HOTELS_ON_PAGE);

        PagenableElementsDto pagenableElementsDto = getPagenableElements(recordsOnPageInteger, numberOfPageInteger);

        ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
        HotelService hotelService = contextInjector.getHotelService();
        if (numberOfPage == null) {
            numberOfPage = "1";
        }

        List<HotelDto> allHotels = hotelService.findAll(pagenableElementsDto)
                .stream()
                .sorted(Comparator.comparing(HotelDto::getName))
                .collect(Collectors.toList());
        request.setAttribute("listOfHotels", allHotels);
        request.setAttribute("totalPages", hotelService.getNumberOfPages(pagenableElementsDto));
        request.setAttribute("recordsOnPage", recordsOnPage);
        request.setAttribute("numberOfPage", numberOfPage);
        return PagesConstant.SHOW_HOTELS;
    }

    private PagenableElementsDto getPagenableElements(Integer roomsOnPage, Integer numberOfPage) {
        return PagenableElementsDto.newBuilder()
                .numberOfPage(numberOfPage)
                .itemsOnPage(roomsOnPage)
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

}