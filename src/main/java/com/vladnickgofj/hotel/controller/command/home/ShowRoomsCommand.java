package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDtoRequest;
import com.vladnickgofj.hotel.service.RoomStatusService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowRoomsCommand implements Command {

    private static Logger LOGGER = Logger.getLogger(ShowRoomsCommand.class);
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RoomStatusService roomStatusService = contextInjector.getRoomStatusService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        removeSessionAtributes(request);

        String hotelId = request.getParameter("hotelId");
        String hotelName = request.getParameter("hotelName");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String numberOfPage = request.getParameter("numberOfPage");
        String statusFree = request.getParameter("statusFree");
        String statusBooked = request.getParameter("statusBooked");
        String checkInStr = request.getParameter("checkIn");
        String checkOutStr = request.getParameter("checkOut");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String method = request.getMethod();
        LOGGER.info("method" + method);

        RoomStatusDtoRequest roomStatusDtoRequest = RoomStatusDtoRequest.newBuilder()
                .hotelId(hotelId)
                .hotelName(hotelName)
                .sorting(sorting)
                .ordering(ordering)
                .itemsOnPage(recordsOnPage)
                .numberOfPage(numberOfPage)
                .statusFree(statusFree)
                .statusBooked(statusBooked)
                .signInStr(checkInStr)
                .signOutStr(checkOutStr)
                .build();

        Integer totalPages = roomStatusService.getNumberOfPages(roomStatusDtoRequest);
        List<RoomStatusDto> roomStatusList = roomStatusService.findAll(roomStatusDtoRequest);

        LOGGER.info("sorting: " + roomStatusDtoRequest.getSorting());
        LOGGER.info("ordering: " + roomStatusDtoRequest.getOrdering());
        LOGGER.info("recordsOnPage: " + roomStatusDtoRequest.getPagenableElementsDto().getItemsOnPage());
        LOGGER.info("totalPages: " + totalPages);
        LOGGER.info("listOfRooms: " + roomStatusList);
        LOGGER.info("numberOfPage: " + roomStatusDtoRequest.getNumberOfPage());
        LOGGER.info("checkIn: " + roomStatusDtoRequest.getSignIn());
        LOGGER.info("checkOut: " + roomStatusDtoRequest.getSignOut());
        LOGGER.info("statusFree: " + roomStatusDtoRequest.getStatusFree());
        LOGGER.info("statusBooked: " + roomStatusDtoRequest.getStatusBooked());
        LOGGER.info("minCheckIn: " + roomStatusDtoRequest.getMinSignIn());
        LOGGER.info("maxCheckIn: " + roomStatusDtoRequest.getMaxSignIn());
        LOGGER.info("minCheckOut: " + roomStatusDtoRequest.getMinSignOut());
        LOGGER.info("maxCheckOut: " + roomStatusDtoRequest.getMaxSignOut());

        request.setAttribute("hotelId", hotelId);
        request.setAttribute("hotelName", hotelName);
        request.setAttribute("sorting", roomStatusDtoRequest.getSorting());
        request.setAttribute("ordering", roomStatusDtoRequest.getOrdering());
        request.setAttribute("recordsOnPage", roomStatusDtoRequest.getPagenableElementsDto().getItemsOnPage());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listOfRooms", roomStatusList);
        request.setAttribute("numberOfPage", roomStatusDtoRequest.getNumberOfPage());
        request.setAttribute("checkIn", roomStatusDtoRequest.getSignIn());
        request.setAttribute("checkOut", roomStatusDtoRequest.getSignOut());
        request.setAttribute("statusFree", roomStatusDtoRequest.getStatusFree());
        request.setAttribute("statusBooked", roomStatusDtoRequest.getStatusBooked());
        request.setAttribute("minCheckIn", roomStatusDtoRequest.getMinSignIn());
        request.setAttribute("maxCheckIn", roomStatusDtoRequest.getMaxSignIn());
        request.setAttribute("minCheckOut", roomStatusDtoRequest.getMinSignOut());
        request.setAttribute("maxCheckOut", roomStatusDtoRequest.getMaxSignOut());
        String url = "home?command=showRooms&hotelId="+hotelId+"&hotelName="+hotelName;
//        return "post".equals(method) ? url : page;
        return PagesConstant.SHOW_ROOMS;
    }

    private void removeSessionAtributes(HttpServletRequest request) {
        request.getSession().removeAttribute("statusFree");
        request.getSession().removeAttribute("statusBooked");
        request.getSession().removeAttribute("typeName");
        request.getSession().removeAttribute("numberOfBeds");
        request.getSession().removeAttribute("price");
        request.getSession().removeAttribute("hotelId");
        request.getSession().removeAttribute("hotelName");
        request.getSession().removeAttribute("roomId");
        request.getSession().removeAttribute("roomStatusId");
        request.getSession().removeAttribute("checkIn");
        request.getSession().removeAttribute("checkOut");
        request.getSession().removeAttribute("sorting");
        request.getSession().removeAttribute("ordering");
        request.getSession().removeAttribute("recordsOnPage");
        request.getSession().removeAttribute("days");
    }

}