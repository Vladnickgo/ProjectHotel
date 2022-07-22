package com.vladnickgofj.hotel.controller;

import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AbstractServlet.class);

    private static final String NOT_VALID_PATH = "Not valid path";

    private final Map<String, Command> commandNameToCommand;

    private final Command defaultCommand;

    AbstractServlet(String path, String defaultCommand) {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        switch (path) {
            case "home": {
                this.commandNameToCommand = injector.getHomeCommandNameToCommand();
                LOGGER.info("Path: " + path);
                break;
            }
            case "user": {
                this.commandNameToCommand = injector.getUserCommands();
                LOGGER.info("Path: " + path);
                break;
            }
            default: {
                LOGGER.error(NOT_VALID_PATH);
                throw new IllegalArgumentException(NOT_VALID_PATH);
            }
        }
        this.defaultCommand = commandNameToCommand.get(defaultCommand);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        LOGGER.info("Command name: " + commandName);
        Command command = commandNameToCommand.getOrDefault(commandName, defaultCommand);
        final String page = command.execute(req, resp);
        LOGGER.info("page: " + page);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
