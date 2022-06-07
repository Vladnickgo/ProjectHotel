package com.vladnickgofj.hotel.context;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.command.home.*;
import com.vladnickgofj.hotel.controller.command.user.*;
import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.dao.HotelDao;
import com.vladnickgofj.hotel.dao.UserDao;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.dao.impl.HotelDaoImpl;
import com.vladnickgofj.hotel.dao.impl.UserDaoImpl;
import com.vladnickgofj.hotel.service.HotelService;
import com.vladnickgofj.hotel.service.UserService;
import com.vladnickgofj.hotel.service.impl.HotelServiceImpl;
import com.vladnickgofj.hotel.service.impl.UserServiceImpl;
import com.vladnickgofj.hotel.service.mapper.HotelMapper;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import com.vladnickgofj.hotel.service.mapper.UserMapper;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.validator.UserValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {

    private static final HikariConnectionPool HIKARI_CONNECTION_POOL = new HikariConnectionPool("bd-config");

    private static final UserDao USER_DAO = new UserDaoImpl(HIKARI_CONNECTION_POOL);

    private static final HotelDao HOTEL_DAO = new HotelDaoImpl(HIKARI_CONNECTION_POOL);

    private static final Mapper<UserDto, User> USER_MAPPER = new UserMapper();

    public static final Mapper<HotelDto, Hotel> HOTEL_MAPPER = new HotelMapper();

    private static final UserValidator USER_VALIDATOR = new UserValidator();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER, USER_VALIDATOR);

    private static final HotelService HOTEL_SERVICE = new HotelServiceImpl(HOTEL_DAO, HOTEL_MAPPER);

    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);

    private static final Command LOGOUT_COMMAND = new LogoutCommand();

    private static final Command SHOW_PROFILE_COMMAND = new ShowProfileCommand();

    private static final Command DEFAULT_COMMAND = new DefaultCommand();

    private static final Command HOME_COMMAND = new HomePageCommand();

    private static final Command ABOUT_COMMAND = new AboutPageCommand();

    private static final Command CONTACTS_COMMAND = new ContactsPageCommand();

    private static final Command LOGIN_PAGE_COMMAND = new LoginPageCommand();

    private static final Command REGISTER_PAGE_COMMAND = new RegisterPageCommand();

    private static final Command SHOW_HOTELS_COMMAND = new ShowHotelsCommand();

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();

    private static final Map<String, Command> HOME_COMMAND_NAME_TO_COMMAND = initHomeCommand();

    //private static final RoomService ROOM_SERVICE=new RoomServiceImpl();

    private static ApplicationContextInjector applicationContextInjector;

    private ApplicationContextInjector() {

    }

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        userCommandNameToCommand.put("defaultCommand", DEFAULT_COMMAND);
        return Collections.unmodifiableMap(userCommandNameToCommand);
    }

    private static Map<String, Command> initHomeCommand() {
        Map<String, Command> homeCommandNameToCommand = new HashMap<>();
        homeCommandNameToCommand.put("homePage", HOME_COMMAND);
        homeCommandNameToCommand.put("aboutPage", ABOUT_COMMAND);
        homeCommandNameToCommand.put("contactsPage", CONTACTS_COMMAND);
        homeCommandNameToCommand.put("loginPage", LOGIN_PAGE_COMMAND);
        homeCommandNameToCommand.put("registerPage", REGISTER_PAGE_COMMAND);
        homeCommandNameToCommand.put("register", REGISTER_COMMAND);
        homeCommandNameToCommand.put("login", LOGIN_COMMAND);
        homeCommandNameToCommand.put("show-profile", SHOW_PROFILE_COMMAND);
        homeCommandNameToCommand.put("showHotels", SHOW_HOTELS_COMMAND);
        homeCommandNameToCommand.put("defaultCommand", DEFAULT_COMMAND);

        return Collections.unmodifiableMap(homeCommandNameToCommand);
    }

    public static ApplicationContextInjector getInstance() {
        if (applicationContextInjector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (applicationContextInjector == null) {
                    applicationContextInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationContextInjector;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public HotelService getHotelService() {
        return HOTEL_SERVICE;
    }

    public Map<String, Command> getHomeCommandNameToCommand() {
        return HOME_COMMAND_NAME_TO_COMMAND;
    }

}



