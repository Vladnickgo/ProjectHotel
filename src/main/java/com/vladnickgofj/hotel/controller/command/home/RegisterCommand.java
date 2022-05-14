package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.dao.entity.Role;
import com.vladnickgofj.hotel.service.UserService;
import com.vladnickgofj.hotel.controller.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        // TODO: 04.04.2022 logic for registration
        //to move logic form doSighIn in HelloServlet
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmationPassword = request.getParameter("confirmationPassword");

        if (!Objects.equals(password, confirmationPassword) || Objects.equals(password, "")) {
            request.setAttribute("confirmationPassword", "Password not Equal");
            return PagesConstant.REGISTRATION_PAGE;
        }
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(password);
        System.out.println(confirmationPassword);
        UserDto userDto = new UserDto(1, firstName, lastName, email, password, confirmationPassword, Role.USER);
        System.out.println("User is created");
        System.out.println("userDto"+userDto);
        try {
            userService.save(userDto);
            System.out.println("User is saved");
        }catch (Exception e){
            System.out.println("User isn't saved");
        }
        return PagesConstant.LOGIN_PAGE;
    }

    private boolean emailValidation(HttpServletRequest req, String email) throws ServletException, IOException {
        String regExp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        if (!email.matches(regExp)) {
            req.setAttribute("message", "email is not correct");
            return true;
        }
        return false;
    }
}
