package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.LocalDateDto;
import com.vladnickgofj.hotel.controller.dto.RoomTypeDto;
import com.vladnickgofj.hotel.service.HotelService;
import com.vladnickgofj.hotel.service.RoomTypeService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersOrderPageCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final HotelService hotelService = contextInjector.getHotelService();
    private final RoomTypeService roomTypeService = contextInjector.getRoomTypeService();
    public static final Logger LOGGER = Logger.getLogger(UsersOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String signIn = request.getParameter("signIn");
        String signOut = request.getParameter("signOut");
        String roomTypeId = request.getParameter("roomTypeId");
        String hotelDto = request.getParameter("hotelDto");
        String numberOfPersons = request.getParameter("numberOfPersons");
        LocalDateDto localDateDto = LocalDateDto.newBuilder()
                .signIn(signIn)
                .signOut(signOut)
                .build();
        List<HotelDto> allHotels = hotelService.findAll();
        List<RoomTypeDto> allRoomTypes = roomTypeService.findAll();
        request.setAttribute("allHotels", allHotels);
        request.setAttribute("allRoomTypes", allRoomTypes);
        request.setAttribute("dateStart", localDateDto.getSignIn());
        request.setAttribute("minDateStart", localDateDto.getMinSignIn());
        request.setAttribute("maxDateStart", localDateDto.getMaxSignIn());
        request.setAttribute("dateEnd", localDateDto.getSignOut());
        request.setAttribute("maxDateEnd", localDateDto.getMaxSignOut());
        request.setAttribute("roomTypeId", roomTypeId);
        request.setAttribute("hotelDto", hotelDto);
        request.setAttribute("numberOfPersons", numberOfPersons);
        request.setAttribute("url", "home?command=usersOrderPage");
        return PagesConstant.USERS_ORDER_PAGE;
    }
}
