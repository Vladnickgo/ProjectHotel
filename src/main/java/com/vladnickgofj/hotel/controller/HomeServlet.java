package com.vladnickgofj.hotel.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/home")
public class HomeServlet extends AbstractServlet {
    public HomeServlet() {
        super("home", "defaultCommand");
    }
}