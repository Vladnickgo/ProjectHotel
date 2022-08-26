package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmBookingPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ConfirmBookingGroupCancelCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String url = "home?command=confirmBookingPageCommand";
        request.setAttribute("url", url);
        return PagesConstant.BOOKING_PAYMENT_PAGE;
    }
}