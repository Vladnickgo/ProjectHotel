package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.service.HotelService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowHotelsCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(ShowHotelsCommand.class);
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final HotelService hotelService = contextInjector.getHotelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numberOfPage = request.getParameter("numberOfPage");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String command = request.getParameter("command");
        request.setAttribute("command", command);

        Integer pageNumber = hotelService.initNumberOfPage(numberOfPage);
        Integer itemsOnPage = hotelService.initItemsOnPage(recordsOnPage);

        LOGGER.info("Items on page: " + itemsOnPage);
        LOGGER.info("Page: " + pageNumber);

        List<HotelDto> allHotels = hotelService.findAll(itemsOnPage, pageNumber);
        Integer pages = hotelService.getNumberOfPages(itemsOnPage);
        LOGGER.info("Pages: " + pages);
        request.setAttribute("listOfHotels", allHotels);
        request.setAttribute("totalPages", pages);
        request.setAttribute("recordsOnPage", itemsOnPage);
        request.setAttribute("numberOfPage", pageNumber);
        request.setAttribute("command", command);
        String url = "home?command=showHotels&numberOfPage=" +
                pageNumber + "&recordsOnPage=" + itemsOnPage;
        request.setAttribute("url", url);
        return PagesConstant.SHOW_HOTELS;
    }


}