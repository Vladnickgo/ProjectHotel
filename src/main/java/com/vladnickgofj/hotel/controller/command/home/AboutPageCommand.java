package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AboutPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AboutPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        request.setAttribute("url", "home?command=aboutPage");
        return PagesConstant.ABOUT_PAGE;
    }

}
