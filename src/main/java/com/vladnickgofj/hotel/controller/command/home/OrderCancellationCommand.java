package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.service.UsersOrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderCancellationCommand implements Command {
    private static final Logger LOGGER=Logger.getLogger(OrderCancellationCommand.class);
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final UsersOrderService usersOrderService = contextInjector.getUsersOrderService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userOrderId = request.getParameter("userOrderId");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String method = request.getMethod();
        LOGGER.info("method" + method);
        usersOrderService.updateUsersOrderById(Integer.valueOf(userOrderId));
        return PagesConstant.ADMIN_PROFILE;
    }
}
