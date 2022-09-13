package com.vladnickgofj.hotel.controller.command.home.receipt;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.PaymentDto;
import com.vladnickgofj.hotel.service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentReceipt implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final PaymentService paymentService= contextInjector.getPaymentService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        Integer bookingId = Integer.valueOf(request.getParameter("bookingId"));
        PaymentDto paymentByBookingId = paymentService.findPaymentByBookingId(bookingId);
        request.setAttribute("paymentInfo",paymentByBookingId);
        return PagesConstant.PAYMENT_RECEIPT;
    }
}
