package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomType;
import com.vladnickgofj.hotel.service.BookingService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.Integer.valueOf;

public class ConfirmBookingCommand implements Command {

    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final BookingService bookingService = contextInjector.getBookingService();
    private static final Logger LOGGER = Logger.getLogger(ConfirmBookingCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String typeName = (String) request.getSession().getAttribute("typeName");
        Integer numberOfBeds = (Integer) request.getSession().getAttribute("numberOfBeds");
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        String hotelName = (String) request.getSession().getAttribute("hotelName");
        String roomId = (String) request.getSession().getAttribute("roomId");
        String checkIn = (String) request.getSession().getAttribute("checkIn");
        String checkOut = (String) request.getSession().getAttribute("checkOut");
        LOGGER.info("checkOut: " + checkOut);
        long numberOfNights = (long) request.getSession().getAttribute("numberOfNights");
        LOGGER.info("numberOfNights: " + numberOfNights);
        Integer userId = user.getId();
        RoomStatusDto roomStatusDto = (RoomStatusDto) request.getSession().getAttribute("roomStatus");
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
        request.getSession().setAttribute("days", numberOfNights);

        return "home?command=confirmBookingPageCommand";
    }
}
