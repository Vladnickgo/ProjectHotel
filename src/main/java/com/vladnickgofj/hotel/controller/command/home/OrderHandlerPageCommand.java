package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.service.RoomStatusService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class OrderHandlerPageCommand implements Command {
    private static final Logger LOGGER=Logger.getLogger(OrderHandlerPageCommand.class);
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RoomStatusService roomStatusService = contextInjector.getRoomStatusService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String roomType = request.getParameter("roomType");
        String hotelId = request.getParameter("hotelId");
        String hotelName = request.getParameter("hotelName");
        String numberOfPersons = request.getParameter("numberOfPersons");
        String userOrderId = request.getParameter("userOrderId");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String method = request.getMethod();
        LOGGER.info("method" + method);
        UsersOrderDto usersOrderDto = UsersOrderDto.newBuilder()
                .userDto(UserDto.newBuilder()
                        .id(Integer.valueOf(userId))
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .build())
                .hotelDto(HotelDto.newBuilder()
                        .id(Integer.valueOf(hotelId))
                        .build())
                .dateStart(LocalDate.parse(dateStart))
                .dateEnd(LocalDate.parse(dateEnd))
                .roomDtoResponse(RoomDtoResponse.newBuilder()
                        .roomType(RoomTypeDto.newBuilder()
                                .typeName(roomType)
                                .build())
                        .build())
                .build();
        List<RoomStatusDto> roomStatusDtoList = roomStatusService.findAllFreeByParameters(usersOrderDto);
        request.setAttribute("roomStatusDtoList", roomStatusDtoList);
        request.setAttribute("hotelId", hotelId);
        request.setAttribute("hotelName", hotelName);
        request.setAttribute("userId", userId);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("numberOfPersons", numberOfPersons);
        request.setAttribute("roomType", roomType);
        request.setAttribute("userOrderId", userOrderId);

        return PagesConstant.ORDER_HANDLER_PAGE;
    }
}
