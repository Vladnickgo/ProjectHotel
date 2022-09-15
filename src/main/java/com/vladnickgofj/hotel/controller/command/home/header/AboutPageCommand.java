package com.vladnickgofj.hotel.controller.command.home.header;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AboutPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        return PagesConstant.ABOUT_PAGE;
    }

}