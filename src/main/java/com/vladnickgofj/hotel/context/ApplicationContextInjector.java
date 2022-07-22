package com.vladnickgofj.hotel.context;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.command.home.*;
import com.vladnickgofj.hotel.controller.command.user.*;
import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.*;
import com.vladnickgofj.hotel.dao.entity.*;
import com.vladnickgofj.hotel.dao.impl.*;
import com.vladnickgofj.hotel.service.*;
import com.vladnickgofj.hotel.service.impl.*;
import com.vladnickgofj.hotel.service.mapper.*;
import com.vladnickgofj.hotel.validator.UserValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {

    private static final HikariConnectionPool HIKARI_CONNECTION_POOL = new HikariConnectionPool("bd-config");

    private static final UserDao USER_DAO = new UserDaoImpl(HIKARI_CONNECTION_POOL);

    private static final HotelDao HOTEL_DAO = new HotelDaoImpl(HIKARI_CONNECTION_POOL);

    private static final RoomDao ROOM_DAO = new RoomDaoImpl(HIKARI_CONNECTION_POOL);

    private static final RoomStatusDao ROOM_STATUS_DAO = new RoomStatusDaoImpl(HIKARI_CONNECTION_POOL);

    private static final RoomTypeDao ROOM_TYPE_DAO = new RoomTypeDaoImpl(HIKARI_CONNECTION_POOL);

    private static final Mapper<UserDto, User> USER_MAPPER = new UserMapper();

    public static final Mapper<HotelDto, Hotel> HOTEL_MAPPER = new HotelMapper();

    private static final Mapper<RoomStatusDto, RoomStatus> ROOM_STATUS_MAPPER = new RoomStatusMapper();

    private static final Mapper<RoomTypeDto, RoomType> ROOM_TYPE_MAPPER = new RoomTypeMapper();

    private static final Mapper<RoomDtoResponse, Room> ROOM_MAPPER = new RoomResponseMapper();

    private static final Mapper<BookingDto, Booking> BOOKING_MAPPER = new BookingMapper();

    private static final UserValidator USER_VALIDATOR = new UserValidator();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER, USER_VALIDATOR);

    private static final HotelService HOTEL_SERVICE = new HotelServiceImpl(HOTEL_DAO, HOTEL_MAPPER);

    private static final RoomService ROOM_SERVICE = new RoomServiceImpl(ROOM_DAO, ROOM_MAPPER);

    private static final RoomStatusService ROOM_STATUS_SERVICE = new RoomStatusServiceImpl(ROOM_STATUS_DAO, ROOM_STATUS_MAPPER);

    private static final RoomTypeService ROOM_TYPE_SERVICE = new RoomTypeServiceImpl(ROOM_TYPE_DAO, ROOM_TYPE_MAPPER);

    private static final ChangeRoomStatusService CHANGE_ROOM_STATUS_SERVICE = new ChangeRoomStatusServiceImpl(ROOM_STATUS_DAO, ROOM_STATUS_MAPPER, BOOKING_MAPPER);

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

    private static final Command SHOW_ROOMS_COMMAND = new ShowRoomsCommand();

    private static final Command CHANGE_ROOM_STATUS_COMMAND = new ChangeRoomStatusCommand();

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();

    private static final Map<String, Command> HOME_COMMAND_NAME_TO_COMMAND = initHomeCommand();

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
        homeCommandNameToCommand.put("showRooms", SHOW_ROOMS_COMMAND);
        homeCommandNameToCommand.put("changeRoomStatus", CHANGE_ROOM_STATUS_COMMAND);
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

    public RoomService getRoomService() {
        return ROOM_SERVICE;
    }

    public RoomStatusService getRoomStatusService() {
        return ROOM_STATUS_SERVICE;
    }

    public RoomTypeService getRoomTypeService() {
        return ROOM_TYPE_SERVICE;
    }

    public ChangeRoomStatusService getChangeRoomStatusService() {
        return CHANGE_ROOM_STATUS_SERVICE;
    }

    public Map<String, Command> getHomeCommandNameToCommand() {
        return HOME_COMMAND_NAME_TO_COMMAND;
    }
}



