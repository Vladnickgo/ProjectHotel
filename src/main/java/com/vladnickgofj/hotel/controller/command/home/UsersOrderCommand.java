package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.entity.OrderStatus;
import com.vladnickgofj.hotel.service.HotelService;
import com.vladnickgofj.hotel.service.RoomTypeService;
import com.vladnickgofj.hotel.service.UsersOrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.vladnickgofj.hotel.ApplicationConstant.MAX_NUMBER_OF_PERSONS_FOR_BOOKINGS;

public class UsersOrderCommand implements Command {

    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final UsersOrderService usersOrderService = contextInjector.getUsersOrderService();
    private final HotelService hotelService = contextInjector.getHotelService();
    private final RoomTypeService roomTypeService = contextInjector.getRoomTypeService();
    public static final Logger LOGGER = Logger.getLogger(UsersOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsersOrderDto usersOrderDto = getUsersOrderDto(request);
        try {
            usersOrderService.save(usersOrderDto);
        } catch (IllegalArgumentException e) {
            LOGGER.info(e.getMessage());
            List<HotelDto> allHotels = hotelService.findAll();
            List<RoomTypeDto> allRoomTypes = roomTypeService.findAll();
            request.setAttribute("allHotels", allHotels);
            request.setAttribute("allRoomTypes", allRoomTypes);
            request.setAttribute("hotelId", getUsersOrderDto(request).getHotelDto().getId());
            request.setAttribute("dateStart", getUsersOrderDto(request).getDateStart());
            request.setAttribute("dateEnd", getUsersOrderDto(request).getDateEnd());
            request.setAttribute("numberOfPersons", getUsersOrderDto(request).getNumberOfPersons());
            request.setAttribute("roomTypeId", getUsersOrderDto(request).getRoomDtoResponse().getRoomType().getTypeId());
            request.setAttribute("error",e.getMessage());
            request.setAttribute("maxPersons",MAX_NUMBER_OF_PERSONS_FOR_BOOKINGS);
            return PagesConstant.USERS_ORDER_PAGE;

        }
//        return PagesConstant.USER_PROFILE;
        request.setAttribute("statusNotDone","notDone");
        return PagesConstant.MY_ORDERS_PAGE;
    }

    private UsersOrderDto getUsersOrderDto(HttpServletRequest request) {
        String command = request.getParameter("command");
        request.setAttribute("command", command);

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
