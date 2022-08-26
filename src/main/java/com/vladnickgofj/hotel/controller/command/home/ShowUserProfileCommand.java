package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.BookingRequestDto;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.service.BookingService;
import com.vladnickgofj.hotel.service.util.BookingRequestDtoUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUserProfileCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final BookingService bookingService = contextInjector.getBookingService();
    private static final Logger LOGGER = Logger.getLogger(ShowUserProfileCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        BookingRequestDto bookingRequestDto = getBookingRequestDto(request);
        Integer pages = bookingService.getNumberOfPages(bookingRequestDto);
        List<BookingDto> bookingsByUserIdAndParameters = bookingService.findBookingsByUserIdAndParameters(bookingRequestDto);
        BookingRequestDtoUtil bookingRequestDtoUtil = new BookingRequestDtoUtil(bookingRequestDto);
        String statusPaid = bookingRequestDtoUtil.getStatusPaid();
        String statusNotPaid = bookingRequestDtoUtil.getStatusNotPaid();
        String statusCanceled = bookingRequestDtoUtil.getStatusCanceled();
        String sorting = bookingRequestDtoUtil.getSorting();
        String ordering = bookingRequestDtoUtil.getOrdering();
        Integer itemsOnPage = bookingRequestDtoUtil.getItemsOnPage();
        Integer numberOfPage = bookingRequestDtoUtil.getNumberOfPage();

        request.setAttribute("statusNotPaid", statusNotPaid);
        request.setAttribute("statusPaid", statusPaid);
        request.setAttribute("statusCanceled", statusCanceled);
        request.setAttribute("sorting", sorting);
        request.setAttribute("ordering", ordering);
        request.setAttribute("itemsOnPage", itemsOnPage);
        request.setAttribute("bookingsByUserIdAndParameters", bookingsByUserIdAndParameters);
        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("totalPages", pages);

        String url = "home?command=showUserProfile&statusNotPaid=" + statusNotPaid + "&statusPaid=" + statusPaid +
                "&statusCanceled=" + statusCanceled + "&sorting=" + sorting + "&ordering=" + ordering +
                "&itemsOnPage=" + itemsOnPage + "&numberOfPage=" + numberOfPage;
        request.setAttribute("url", url);
        return PagesConstant.USER_PROFILE;

    }

    private BookingRequestDto getBookingRequestDto(HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        String statusNotPaid = request.getParameter("statusNotPaid");
        String statusPaid = request.getParameter("statusPaid");
        String statusCanceled = request.getParameter("statusCanceled");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String itemsOnPage = request.getParameter("itemsOnPage");
        String numberOfPage = request.getParameter("numberOfPage");
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
        return bookingRequestDto;
    }
}
