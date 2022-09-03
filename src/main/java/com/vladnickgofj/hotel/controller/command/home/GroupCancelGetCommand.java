package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GroupCancelGetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookingDto> bookingDtoList = (List<BookingDto>) request.getSession().getAttribute("bookingDtoList");
        request.setAttribute("bookingDtoList", bookingDtoList);
        return PagesConstant.CANCEL_BOOKING_RESULT_PAGE;
    }
}