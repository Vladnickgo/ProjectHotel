package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.entity.OrderStatus;
import com.vladnickgofj.hotel.dao.entity.StatusStatement;
import com.vladnickgofj.hotel.service.UsersOrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderCompletionConfirmationCommand implements Command {
    private static final Logger LOGGER=Logger.getLogger(OrderCompletionConfirmationCommand.class);
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final UsersOrderService usersOrderService = contextInjector.getUsersOrderService();
    private final List<UsersOrderDto> usersOrderDtoList = new ArrayList<>();
    private final List<RoomStatusDto> roomStatusList = new ArrayList<>();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        usersOrderDtoList.clear();
        roomStatusList.clear();

        String userId = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String method = request.getMethod();
        LOGGER.info("method" + method);

        String userOrderId = request.getParameter("userOrderId");
        String hotelId = request.getParameter("hotelId");
        String hotelName = request.getParameter("hotelName");
        String numberOfPersons = request.getParameter("numberOfPersons");
        String[] statusDtoIds = request.getParameterValues("statusDtoId");
        String[] statusDtoDateStarts = request.getParameterValues("statusDtoDateStart");
        String[] statusDtoDateEnds = request.getParameterValues("statusDtoDateEnd");

        String[] roomIds = request.getParameterValues("roomId");
        String[] roomTypeIds = request.getParameterValues("roomTypeId");
        String[] signIns = request.getParameterValues("signIn");
        String[] signOuts = request.getParameterValues("signOut");

        for (int i = 0; i < signOuts.length; i++) {
            Integer statusDtoId = Integer.valueOf(statusDtoIds[i]);
            LocalDate dateStart = LocalDate.parse(signIns[i]);
            LocalDate dateEnd = LocalDate.parse(signOuts[i]);
            LocalDate statusDtoDateStart = LocalDate.parse(statusDtoDateStarts[i]);
            LocalDate statusDtoDateEnd = LocalDate.parse(statusDtoDateEnds[i]);
            Integer roomId = Integer.valueOf(roomIds[i]);
            Integer roomTypeId = Integer.valueOf(roomTypeIds[i]);
            UsersOrderDto usersOrderDto = UsersOrderDto.newBuilder()
                    .id(Integer.valueOf(userOrderId))
                    .dateStart(dateStart)
                    .dateEnd(dateEnd)
                    .userDto(UserDto.newBuilder()
                            .id(Integer.valueOf(userId))
                            .firstName(firstName)
                            .lastName(lastName)
                            .email(email)
                            .build())
                    .hotelDto(HotelDto.newBuilder()
                            .id(Integer.valueOf(hotelId))
                            .name(hotelName)
                            .build())
                    .roomDtoResponse(RoomDtoResponse.newBuilder()
                            .id(roomId)
                            .roomType(RoomTypeDto.newBuilder()
                                    .typeId(roomTypeId)
                                    .build())
                            .build())
                    .numberOfPersons(Integer.valueOf(numberOfPersons))
                    .orderStatus(OrderStatus.COMPLETED)
                    .build();

            RoomStatusDto roomStatusDto = RoomStatusDto.newBuilder()
                    .statusId(statusDtoId)
                    .dateStart(statusDtoDateStart)
                    .dateEnd(statusDtoDateEnd)
                    .roomDtoResponse(RoomDtoResponse.newBuilder()
                            .id(roomId)
                            .roomType(RoomTypeDto.newBuilder()
                                    .typeId(roomTypeId)
                                    .build())
                            .hotel(HotelDto.newBuilder()
                                    .name(hotelName)
                                    .build())
                            .build())
                    .statusStatement(StatusStatement.newBuilder()
                            .statusStatementId(1)
                            .build())
                    .build();
            usersOrderDtoList.add(usersOrderDto);
            roomStatusList.add(roomStatusDto);
        }
        usersOrderService.completeUsersOrder(usersOrderDtoList, roomStatusList);

        return PagesConstant.ABOUT_PAGE;
    }
}
