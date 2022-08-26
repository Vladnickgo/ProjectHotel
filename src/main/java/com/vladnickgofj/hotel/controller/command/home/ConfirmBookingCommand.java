package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomType;
import com.vladnickgofj.hotel.service.BookingService;
import com.vladnickgofj.hotel.service.RoomStatusService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.Integer.valueOf;

public class ConfirmBookingCommand implements Command {

    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RoomStatusService roomStatusService = contextInjector.getRoomStatusService();
    private final BookingService bookingService = contextInjector.getBookingService();
    private static final Logger LOGGER = Logger.getLogger(ConfirmBookingCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String typeName = (String) request.getSession().getAttribute("typeName");
        Integer numberOfBeds = (Integer) request.getSession().getAttribute("numberOfBeds");
        Integer price = (Integer) request.getSession().getAttribute("price");
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        String hotelId = (String) request.getSession().getAttribute("hotelId");
        String hotelName = (String) request.getSession().getAttribute("hotelName");
        String roomId = (String) request.getSession().getAttribute("roomId");
        String roomStatusId = (String) request.getSession().getAttribute("roomStatusId");
        String dateStart = (String) request.getSession().getAttribute("dateStart");
        String dateEnd = (String) request.getSession().getAttribute("dateEnd");
        String checkIn = (String) request.getSession().getAttribute("checkIn");
        String checkOut = (String) request.getSession().getAttribute("checkOut");
        String method = request.getParameter("method");
        LOGGER.info("checkOut: " + checkOut);
        long numberOfNights = (long) request.getSession().getAttribute("numberOfNights");
        LOGGER.info("numberOfNights: " + numberOfNights);
        Integer userId = user.getId();
        RoomStatusDto roomStatusDto = (RoomStatusDto) request.getSession().getAttribute("roomStatus");
        LOGGER.info(roomStatusDto);
        LOGGER.info("roomStatusDto: " + roomStatusDto);

        LocalDate bookTime = LocalDate.now();

        BookingDto bookingDto = BookingDto.newBuilder()
                .checkIn(LocalDate.parse(checkIn))
                .checkOut(LocalDate.parse(checkOut))
                .bookTime(bookTime)
                .room(Room.newBuilder()
                        .id(valueOf(roomId))
                        .roomType(RoomType.newBuilder()
                                .typeName(typeName)
                                .build())
                        .numberOfBeds(numberOfBeds)
                        .hotel(Hotel.newBuilder()
                                .name(hotelName)
                                .build())
                        .build())
                .nights((int) numberOfNights)
                .userId(userId)
                .build();
        LOGGER.info("bookingDto: " + bookingDto);
        bookingService.createNewBooking(bookingDto, roomStatusDto);
        LOGGER.info("roomStatus and bookingStatus will be changed");
        request.getSession().setAttribute("numberOfBeds", numberOfBeds);
        request.getSession().setAttribute("price", price);
        request.getSession().setAttribute("typeName", typeName);
        request.getSession().setAttribute("bookTime", bookTime);
        request.getSession().setAttribute("hotelId", hotelId);
        request.getSession().setAttribute("hotelName", hotelName);
        request.getSession().setAttribute("roomId", roomId);
        request.getSession().setAttribute("roomStatusId", roomStatusId);
        request.getSession().setAttribute("dateStart", dateStart);
        request.getSession().setAttribute("dateEnd", dateEnd);
        request.getSession().setAttribute("checkIn", checkIn);
        request.getSession().setAttribute("checkOut", checkOut);
        request.getSession().setAttribute("days", numberOfNights);

        String url = "home?command=confirmBookingPageCommand";
//        String page=PagesConstant.BOOKING_PAYMENT_PAGE;
        return url;
    }
}
