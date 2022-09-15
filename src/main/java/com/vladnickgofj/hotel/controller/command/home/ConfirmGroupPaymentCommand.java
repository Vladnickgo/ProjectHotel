package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.service.BookingService;
import com.vladnickgofj.hotel.service.PaymentService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfirmGroupPaymentCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ConfirmGroupPaymentCommand.class);
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final BookingService bookingService = contextInjector.getBookingService();
    private final PaymentService paymentService = contextInjector.getPaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] bookingIds = request.getParameterValues("bookingId");
        String cardNumber = request.getParameter("cardNumber");
        String cvvCode = request.getParameter("cvvCode");
        try {
            paymentService.checkCardData(cardNumber, cvvCode);
            LOGGER.info("Checking card is successful");
        } catch (IllegalArgumentException e) {
            StringBuilder stringBuilderUrl = new StringBuilder("home?command=groupPayment&cardErrorMessage=error");
            for (String bookingId : bookingIds) {
                stringBuilderUrl.append("&bookingId=").append(bookingId);
            }
            String url = stringBuilderUrl.toString();
            request.setAttribute("cardErrorMessage", "error");
            return url;
        }
        List<BookingDto> bookingDtoList = new ArrayList<>();
        StringBuilder stringBuilderUrl = new StringBuilder("home?command=successGroupPaymentPage");

        for (String bookingId : bookingIds) {
            BookingDto byId = bookingService.findById(Integer.valueOf(bookingId));
            try {
                paymentService.addPayment(byId);
            } catch (IllegalArgumentException e) {
                stringBuilderUrl.append("&paymentErrorMessage=error");
                return stringBuilderUrl.toString();
            }
            bookingDtoList.add(byId);
            stringBuilderUrl.append("&bookingIds=").append(bookingId);
        }
        request.setAttribute("bookingDtoList", bookingDtoList);
        return stringBuilderUrl.toString();
    }
}
