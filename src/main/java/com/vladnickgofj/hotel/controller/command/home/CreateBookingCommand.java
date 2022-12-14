package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.service.RoomStatusService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.lang.Integer.valueOf;

public class CreateBookingCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(CreateBookingCommand.class);
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RoomStatusService roomStatusService = contextInjector.getRoomStatusService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        String roomStatusId = request.getParameter("roomStatusId");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String checkIn = request.getParameter("checkIn");
        String checkOut = request.getParameter("checkOut");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String hotelId = request.getParameter("hotelId");
        String hotelName = request.getParameter("hotelName");
        String statusFree = request.getParameter("statusFree");
        String statusBooked = request.getParameter("statusBooked");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String method = request.getMethod();
        LOGGER.info("method" + method);
        LOGGER.info("hotelId" + hotelId);

        long numberOfNights = ChronoUnit.DAYS.between(LocalDate.parse(checkIn), LocalDate.parse(checkOut));
        RoomStatusDto roomStatusById = roomStatusService.getRoomStatusById(valueOf(roomStatusId));
        String typeName = roomStatusById.getRoomDtoResponse().getRoomType().getTypeName();
        Integer numberOfBeds = roomStatusById.getRoomDtoResponse().getNumberOfBeds();
        Integer price = roomStatusById.getRoomDtoResponse().getPrice();
        LOGGER.info("roomStatusById: " + roomStatusById);
        request.getSession().setAttribute("roomStatus", roomStatusById);
        request.getSession().setAttribute("typeName", typeName);
        request.getSession().setAttribute("numberOfBeds", numberOfBeds);
        request.getSession().setAttribute("price", price);
        request.getSession().setAttribute("hotelId", hotelId);
        request.getSession().setAttribute("hotelName", hotelName);
        request.getSession().setAttribute("roomId", roomId);
        request.getSession().setAttribute("roomStatusId", roomStatusId);
        request.getSession().setAttribute("dateStart", dateStart);
        request.getSession().setAttribute("dateEnd", dateEnd);
        request.getSession().setAttribute("checkIn", checkIn);
        request.getSession().setAttribute("checkOut", checkOut);
        request.getSession().setAttribute("sorting", sorting);
        request.getSession().setAttribute("ordering", ordering);
        request.getSession().setAttribute("recordsOnPage", recordsOnPage);
        request.getSession().setAttribute("statusFree", statusFree);
        request.getSession().setAttribute("statusBooked", statusBooked);
        request.getSession().setAttribute("numberOfNights", numberOfNights);
        String parameters = "&roomId=" + roomId +
                "&roomStatusId=" + roomStatusId +
                "&dateStart=" + dateStart +
                "&dateEnd=" + dateEnd +
                "&checkIn=" + checkIn +
                "&checkOut=" + checkOut +
                "&sorting=" + sorting +
                "&ordering=" + ordering +
                "&hotelId=" + hotelId +
                "&hotelName=" + hotelName +
                "&statusFree=" + statusFree +
                "&statusBooked=" + statusBooked +
                "&recordsOnPage=" + recordsOnPage +
                "&numberOfNights=" + numberOfNights;
        request.setAttribute("parameters", parameters);
        return PagesConstant.CREATE_BOOKING_PAGE;
    }
}
