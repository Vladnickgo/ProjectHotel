package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.RoomDtoResponse;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.entity.StatusStatement;
import com.vladnickgofj.hotel.service.ChangeRoomStatusService;
import com.vladnickgofj.hotel.service.RoomStatusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class ChangeRoomStatusCommand implements Command {

    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RoomStatusService roomStatusService = contextInjector.getRoomStatusService();
    private final ChangeRoomStatusService changeRoomStatusService = contextInjector.getChangeRoomStatusService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hotelId = request.getParameter("hotelId");
        String hotelName = request.getParameter("hotelName");
        String roomId = request.getParameter("roomId");
        String roomStatusId = request.getParameter("roomStatusId");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String userId = request.getParameter("userId");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String statusFree = request.getParameter("statusFree");
        String statusBooked = request.getParameter("statusBooked");
        RoomStatusDto roomStatusDto = RoomStatusDto.newBuilder()
                .statusId(Integer.valueOf(roomStatusId))
                .dateStart(LocalDate.parse(dateStart))
                .dateEnd(LocalDate.parse(dateEnd))
                .roomDtoResponse(RoomDtoResponse.newBuilder()
                        .id(Integer.valueOf(roomId))
                        .build())
                .statusStatement(StatusStatement.newBuilder()
                        .statusStatementId(2)
                        .build())
                .build();
        BookingDto bookingDto = BookingDto.newBuilder()
                .check_in(LocalDate.parse(dateStart))
                .check_out(LocalDate.parse(dateEnd))
                .roomId(Integer.valueOf(roomId))
                .bookTime(LocalDate.now())
                .bookingStatusId(1)
                .userId(Integer.valueOf(userId))
                .build();
        System.out.println(roomStatusDto);
        System.out.println(bookingDto);
        request.setAttribute("signIn", dateStart);
        request.setAttribute("signOut", dateEnd);
        request.setAttribute("sorting", sorting);
        request.setAttribute("ordering", ordering);
        request.setAttribute("recordsOnPage", recordsOnPage);
        request.setAttribute("hotelId", hotelId);
        request.setAttribute("hotelName", hotelName);
        request.setAttribute("statusFree", statusFree);
        request.setAttribute("statusBooked", statusBooked);

        changeRoomStatusService.changeRoomStatusDto(roomStatusDto, bookingDto);
        return PagesConstant.SHOW_ROOMS;
    }
}
