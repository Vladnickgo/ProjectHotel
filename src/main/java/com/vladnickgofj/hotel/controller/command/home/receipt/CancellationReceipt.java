package com.vladnickgofj.hotel.controller.command.home.receipt;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancellationReceipt implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    BookingService bookingService= contextInjector.getBookingService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        Integer bookingId = Integer.valueOf(request.getParameter("bookingId"));
        BookingDto booking = bookingService.findById(bookingId);
        request.setAttribute("booking",booking);
        return PagesConstant.CANCELLATION_RECEIPT;
    }
}
