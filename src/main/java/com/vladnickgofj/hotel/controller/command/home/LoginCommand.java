package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            UserDto user = userService.login(email, password);
            session.setAttribute("user", user);
        } catch (IllegalArgumentException exception) {
            String message = exception.getMessage();
            LOGGER.info(message);
            request.setAttribute("loginPageEmail", email);
            request.setAttribute("errorMessage", message);
            return PagesConstant.LOGIN_PAGE;
        }

        return PagesConstant.HOME_PAGE;
    }


}
