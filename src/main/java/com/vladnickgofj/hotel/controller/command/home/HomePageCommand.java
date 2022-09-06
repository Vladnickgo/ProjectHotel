package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class HomePageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(HomePageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        Locale locale = request.getLocale();
        Locale ua=new Locale("ru","UA");
        String language = (String) request.getSession().getAttribute("language");
        String country = locale.getCountry();
        System.out.println(locale);
        System.out.println(language);
        System.out.println(country);
        Locale us = Locale.CANADA;
        String date = DateFormat.getDateInstance(
                DateFormat.SHORT,
                Objects.equals(language, "ua") ?ua:us
                ).format(new Date());
        System.out.println("date: " + date);

        return PagesConstant.HOME_PAGE;
    }
}
