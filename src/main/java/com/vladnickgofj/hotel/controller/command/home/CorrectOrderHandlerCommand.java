package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import com.vladnickgofj.hotel.service.RoomStatusService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorrectOrderHandlerCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(CorrectOrderHandlerCommand.class);
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RoomStatusService roomStatusService = contextInjector.getRoomStatusService();
    private final List<RoomStatusDto> roomStatusDtoList = new ArrayList<>();

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
        String userOrderId = request.getParameter("userOrderId");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String numberOfPersons = request.getParameter("numberOfPersons");
        roomStatusDtoList.clear();
        request.setAttribute("userId", userId);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("roomType", roomType);
        request.setAttribute("hotelId", hotelId);
        request.setAttribute("hotelName", hotelName);
        request.setAttribute("numberOfPersons", numberOfPersons);
        request.setAttribute("userOrderId", userOrderId);
        try {
            String[] roomStatusIds = request.getParameterValues("roomStatusId");
            if(roomStatusIds!=null) {
                request.getSession().setAttribute("roomStatusIdsArray",roomStatusIds);
            }
            String[] roomStatusIdsArray = (String[]) request.getSession().getAttribute("roomStatusIdsArray");
            for (String roomStatusId :  roomStatusIdsArray) {
                RoomStatusDto roomStatusById = roomStatusService.getRoomStatusById(Integer.valueOf(roomStatusId));
                roomStatusDtoList.add(roomStatusById);
            }
            request.setAttribute("roomStatusDtoList", roomStatusDtoList);
            return PagesConstant.ORDER_HANDLER_CORRECTION;
        } catch (RuntimeException e) {
            UsersOrderDto orderDto = UsersOrderDto.newBuilder()
                    .hotelDto(HotelDto.newBuilder()
                            .id(Integer.valueOf(hotelId))
                            .build())
                    .dateStart(LocalDate.parse(dateStart))
                    .dateEnd(LocalDate.parse(dateEnd))
                    .build();
            request.setAttribute("error", "true");
            List<RoomStatusDto> roomStatusDtoList = roomStatusService.findAllFreeByParameters(orderDto);
            request.setAttribute("roomStatusDtoList", roomStatusDtoList);
            return PagesConstant.ORDER_HANDLER_PAGE;
        }
    }
}
