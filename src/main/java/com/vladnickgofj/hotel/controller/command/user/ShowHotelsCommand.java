package com.vladnickgofj.hotel.controller.command.user;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PaginateHotelDto;
import com.vladnickgofj.hotel.service.HotelService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ShowHotelsCommand implements Command {

    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_HOTELS_ON_PAGE = 8;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numberOfPage = request.getParameter("page");
        String recordsOnPage = request.getParameter("recordsOnPage");
        PaginateHotelDto paginateHotelDto = getPaginateHotelDto(numberOfPage, recordsOnPage);
        ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
        HotelService hotelService = contextInjector.getHotelService();
        List<HotelDto> all = hotelService.findAll(paginateHotelDto);
        request.setAttribute("list", all.stream().map(HotelDto::getName).collect(Collectors.toList()));
        request.setAttribute("pages", hotelService.getPages(paginateHotelDto));
        request.setAttribute("recordsOnPage", recordsOnPage);
        request.setAttribute("numberOfPage",numberOfPage);
        return PagesConstant.SHOW_HOTELS;
    }

    private PaginateHotelDto getPaginateHotelDto(String numberOfPage, String recordsOnPage) {
        return PaginateHotelDto.newBuilder()
                .numberOfPage(parseStringToInt(numberOfPage, DEFAULT_PAGE_NUMBER))
                .hotelsOnPage(parseStringToInt(recordsOnPage, DEFAULT_HOTELS_ON_PAGE))
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