package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.service.BookingService;
import com.vladnickgofj.hotel.service.RoomStatusService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConfirmBookingGroupCancelCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final BookingService bookingService = contextInjector.getBookingService();
    private final RoomStatusService roomStatusService = contextInjector.getRoomStatusService();
    private static final Logger LOGGER = Logger.getLogger(ConfirmBookingGroupCancelCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] bookingIds = request.getParameterValues("bookingId");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        StringBuilder stringBuilderUrl = new StringBuilder("home?command=confirmBookingGroupCancel");
        List<BookingDto> bookingDtoList = new ArrayList<>();
        for (String bookingId : bookingIds) {
            BookingDto byId = bookingService.findById(Integer.valueOf(bookingId));
            LocalDate dateEnd = roomStatusService.findDateEndForFreeStatusByRoomIdAndDateStart(byId);
            bookingService.cancelBookingById(byId, dateEnd);
            bookingDtoList.add(byId);
            stringBuilderUrl.append("&bookingId=").append(bookingId);
        }
        LOGGER.info("Order cancellation completed");
        request.getSession().setAttribute("bookingIds", bookingIds);
        request.getSession().setAttribute("bookingDtoList", bookingDtoList);
        String url = stringBuilderUrl.toString();
        LOGGER.info("url: " + url);
//        request.getSession().setAttribute("url", url);
//        return PagesConstant.CANCEL_BOOKING_RESULT_PAGE;
        return "home?command=groupCancelGetCommand";
    }
}
