package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.service.BookingService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class PaymentCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(PaymentCommand.class);
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final BookingService bookingService = contextInjector.getBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("numberOfBeds");
        request.getSession().removeAttribute("price");
        request.getSession().removeAttribute("typeName");
        request.getSession().removeAttribute("bookTime");
        request.getSession().removeAttribute("hotelId");
        request.getSession().removeAttribute("hotelName");
        request.getSession().removeAttribute("roomStatusId");
        request.getSession().removeAttribute("dateStart");
        request.getSession().removeAttribute("dateEnd");
        request.getSession().removeAttribute("days");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String method = request.getMethod();
        LOGGER.info("method" + method);

        String roomId = (String) request.getSession().getAttribute("roomId");
        String checkIn = (String) request.getSession().getAttribute("checkIn");
        String checkOut = (String) request.getSession().getAttribute("checkOut");

        String roomId1 = request.getParameter("roomId");
        String checkIn1 = request.getParameter("checkIn");
        String checkOut1 = request.getParameter("checkOut");
        String cardErrorMessage = request.getParameter("cardErrorMessage");

        System.out.println("roomId1: " + roomId1);
        System.out.println("checkIn1: " + checkIn1);
        System.out.println("checkOut1: " + checkOut1);
        System.out.println("roomId: " + roomId);
        System.out.println("checkIn: " + checkIn);
        System.out.println("checkOut: " + checkOut);

        BookingDto bookingDto = BookingDto.newBuilder()
                .room(Room.newBuilder()
                        .id(Integer.valueOf(roomId))
                        .build())
                .checkIn(LocalDate.parse(checkIn))
                .checkOut(LocalDate.parse(checkOut))
                .build();
        BookingDto bookingByParameters = bookingService.getBookingByParameters(bookingDto);
        request.setAttribute("bookingByParameters", bookingByParameters);
        request.setAttribute("cardErrorMessage", cardErrorMessage);
        LOGGER.info("bookingByParameters" + bookingByParameters);
        String page = PagesConstant.PAYMENT_PAGE;
        String url = "home?command=payment";
        return "post".equals(method) ? url : page;
    }
}
