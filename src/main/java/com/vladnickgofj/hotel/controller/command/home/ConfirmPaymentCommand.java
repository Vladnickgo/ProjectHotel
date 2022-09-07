package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.service.BookingService;
import com.vladnickgofj.hotel.service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmPaymentCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final BookingService bookingService = contextInjector.getBookingService();
    private final PaymentService paymentService = contextInjector.getPaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardNumber = request.getParameter("cardNumber");
        String cvvCode = request.getParameter("cvvCode");
        String bookingId = request.getParameter("bookingByParameters.id");
        BookingDto bookingServiceById = bookingService.findById(Integer.valueOf(bookingId));

        try {
            paymentService.checkCardData(cardNumber, cvvCode);
        } catch (IllegalArgumentException e) {
            return "home?command=payment&cardErrorMessage=error";
        }
        request.getSession().removeAttribute("checkIn");
        request.getSession().removeAttribute("checkOut");
        request.getSession().removeAttribute("roomId");
        paymentService.addPayment(bookingServiceById);
        request.getSession().setAttribute("bookingServiceById", bookingServiceById);

        return "home?command=successPaymentPage";
    }
}
