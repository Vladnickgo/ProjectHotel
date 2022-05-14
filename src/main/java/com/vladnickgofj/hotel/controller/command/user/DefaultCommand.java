package com.vladnickgofj.hotel.controller.command.user;

import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagesConstant.USER_DEFAULT_PAGE;
    }
}
