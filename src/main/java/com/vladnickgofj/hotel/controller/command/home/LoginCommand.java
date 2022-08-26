package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        LOGGER.info("email: " + email);
        String password = request.getParameter("password");
        LOGGER.info("password: " + password);
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        try {
            UserDto user = userService.login(email, password);
            session.setAttribute("user", user);
        } catch (IllegalArgumentException exception) {
            String message = exception.getMessage();
            LOGGER.info(message);
            request.setAttribute("loginPageEmail", email);
            request.setAttribute("errorMessage", message);
            LOGGER.info("loginPageEmail: " + email);
            LOGGER.info("errorMessage: " + message);
            return String.format("home?command=loginPage&loginPageEmail=%S&errorMessage=%S", email, message);
        }
        request.setAttribute("url", "home?command=showHotels");
//        return PagesConstant.SHOW_HOTELS;
        return "home?command=showHotels";
    }


}
