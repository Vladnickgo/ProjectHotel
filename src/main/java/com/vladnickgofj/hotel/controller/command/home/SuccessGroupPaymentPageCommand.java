package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.service.BookingService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SuccessGroupPaymentPageCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private static final Logger LOGGER = Logger.getLogger(SuccessGroupPaymentPageCommand.class);
    private final BookingService bookingService = contextInjector.getBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        LOGGER.info(command);
        String[] bookingIds = request.getParameterValues("bookingIds");
        List<BookingDto> bookingDtoList = new ArrayList<>();
        for (String bookingId : bookingIds) {
            BookingDto byId = bookingService.findById(Integer.valueOf(bookingId));
            bookingDtoList.add(byId);
        }
        request.getSession().setAttribute("bookingDtoList", bookingDtoList);
        request.setAttribute("bookingIds", bookingIds);

        return PagesConstant.SUCCESS_PAYMENT_PAGE;
    }
}
