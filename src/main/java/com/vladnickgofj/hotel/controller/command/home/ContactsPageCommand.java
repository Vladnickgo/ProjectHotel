package com.vladnickgofj.hotel.controller.command.home;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ContactsPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        return PagesConstant.CONTACTS_PAGE;
    }
}
