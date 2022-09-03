package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.entity.OrderStatus;
import com.vladnickgofj.hotel.service.UsersOrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class UsersOrderCommand implements Command {

    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final UsersOrderService usersOrderService = contextInjector.getUsersOrderService();
    public static final Logger LOGGER = Logger.getLogger(UsersOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsersOrderDto usersOrderDto = getUsersOrderDto(request);
        usersOrderService.save(usersOrderDto);
        return PagesConstant.USER_PROFILE;
    }

    private UsersOrderDto getUsersOrderDto(HttpServletRequest request) {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String method = request.getMethod();
        LOGGER.info("method" + method);
        String hotelId = request.getParameter("hotelId");
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String numberOfPersons = request.getParameter("numberOfPersons");
        String roomTypeId = request.getParameter("roomTypeId");
        LocalDate now = LocalDate.now();
        return UsersOrderDto.newBuilder()
                .userDto(UserDto.newBuilder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .build())
                .hotelDto(HotelDto.newBuilder()
                        .id(Integer.valueOf(hotelId))
                        .build())
                .dateStart(LocalDate.parse(dateStart))
                .dateEnd(LocalDate.parse(dateEnd))
                .orderDate(now)
                .numberOfPersons(Integer.valueOf(numberOfPersons))
                .roomDtoResponse(RoomDtoResponse.newBuilder()
                        .roomType(RoomTypeDto.newBuilder()
                                .typeId(Integer.valueOf(roomTypeId))
                                .build())
                        .build())
                .orderStatus(OrderStatus.PROCESSED)
                .build();
    }
}
