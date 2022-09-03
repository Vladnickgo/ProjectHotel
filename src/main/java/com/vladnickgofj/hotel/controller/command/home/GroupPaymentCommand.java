package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.BookingRequestDto;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.service.BookingService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GroupPaymentCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final BookingService bookingService = contextInjector.getBookingService();
    private static final Logger LOGGER = Logger.getLogger(GroupPaymentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDto user = (UserDto) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        String statusNotPaid = request.getParameter("statusNotPaid");
        String statusPaid = request.getParameter("statusPaid");
        String statusCanceled = request.getParameter("statusCanceled");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String itemsOnPage = request.getParameter("itemsOnPage");
        String numberOfPage = request.getParameter("numberOfPage");
        String[] bookingIds = request.getParameterValues("bookingId");
        String cardErrorMessage = request.getParameter("cardErrorMessage");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        HashSet<BookingDto> bookingDtoHashSet = new HashSet<>();
        try {
            for (String bookingId : bookingIds) {
                BookingDto byId = bookingService.findById(Integer.valueOf(bookingId));
                bookingDtoHashSet.add(byId);
            }
            ArrayList<BookingDto> bookingDtoList = new ArrayList<>(bookingDtoHashSet);
            request.setAttribute("bookingDtoList", bookingDtoList);
            request.setAttribute("cardErrorMessage", cardErrorMessage);
            LOGGER.info("Found " + bookingDtoList.size() + " items BookingDto");

            return PagesConstant.CONFIRM_GROUP_PAYMENT_PAGE;
        } catch (RuntimeException e) {
            LOGGER.info("No found any items BookingDto");

            BookingRequestDto bookingRequestDto = BookingRequestDto.newBuilder()
                    .userId(userId)
                    .statusNotPaid(statusNotPaid)
                    .statusPaid(statusPaid)
                    .statusCanceled(statusCanceled)
                    .sorting(sorting)
                    .ordering(ordering)
                    .itemsOnPage(itemsOnPage)
                    .numberOfPage(numberOfPage)
                    .build();
            Integer pages = bookingService.getNumberOfPages(bookingRequestDto);
            List<BookingDto> bookingsByUserIdAndParameters = bookingService.findBookingsByUserIdAndParameters(bookingRequestDto);
            request.setAttribute("bookingsByUserIdAndParameters", bookingsByUserIdAndParameters);
            request.setAttribute("statusNotPaid", "notPaid");
            request.setAttribute("sorting", "price");
            request.setAttribute("ordering", "ASC");
            request.setAttribute("recordsOnPage", 5);
            request.setAttribute("numberOfPage", bookingService.getNumberOfPage());
            request.setAttribute("totalPages", pages);
            request.setAttribute("error", "true");
            return PagesConstant.USER_PROFILE;
        }
    }
}
