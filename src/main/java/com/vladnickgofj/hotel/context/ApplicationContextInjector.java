package com.vladnickgofj.hotel.context;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.command.home.MyOrdersPageCommand;
import com.vladnickgofj.hotel.controller.command.home.*;
import com.vladnickgofj.hotel.controller.command.home.header.AboutPageCommand;
import com.vladnickgofj.hotel.controller.command.home.header.ContactsPageCommand;
import com.vladnickgofj.hotel.controller.command.home.header.HomePageCommand;
import com.vladnickgofj.hotel.controller.command.home.receipt.CancellationReceipt;
import com.vladnickgofj.hotel.controller.command.home.receipt.PaymentReceipt;
import com.vladnickgofj.hotel.controller.command.user.DefaultCommand;
import com.vladnickgofj.hotel.controller.command.user.LogoutCommand;
import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.*;
import com.vladnickgofj.hotel.dao.entity.*;
import com.vladnickgofj.hotel.dao.impl.*;
import com.vladnickgofj.hotel.service.*;
import com.vladnickgofj.hotel.service.impl.*;
import com.vladnickgofj.hotel.service.mapper.*;
import com.vladnickgofj.hotel.service.util.PasswordEncryptionService;
import com.vladnickgofj.hotel.validator.BookingValidator;
import com.vladnickgofj.hotel.validator.UserValidator;
import com.vladnickgofj.hotel.validator.UsersOrderValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {

    private static final HikariConnectionPool HIKARI_CONNECTION_POOL = new HikariConnectionPool("bd-config");
//    private static final HikariConnectionPool HIKARI_CONNECTION_POOL = new HikariConnectionPool("db-config-test");

    private static final UserDao USER_DAO = new UserDaoImpl(HIKARI_CONNECTION_POOL);

    private static final HotelDao HOTEL_DAO = new HotelDaoImpl(HIKARI_CONNECTION_POOL);

    private static final RoomDao ROOM_DAO = new RoomDaoImpl(HIKARI_CONNECTION_POOL);

    private static final RoomStatusDao ROOM_STATUS_DAO = new RoomStatusDaoImpl(HIKARI_CONNECTION_POOL);

    private static final RoomTypeDao ROOM_TYPE_DAO = new RoomTypeDaoImpl(HIKARI_CONNECTION_POOL);

    private static final PaymentDao PAYMENT_DAO = new PaymentDaoImpl(HIKARI_CONNECTION_POOL);

    private static final BookingDao BOOKING_DAO = new BookingDaoImpl(HIKARI_CONNECTION_POOL);

    private static final UsersOrderDao USER_ORDER_DAO = new UsersOrderDaoImpl(HIKARI_CONNECTION_POOL);

    private static final Mapper<UserDto, User> USER_MAPPER = new UserMapper();

    public static final Mapper<HotelDto, Hotel> HOTEL_MAPPER = new HotelMapper();

    private static final Mapper<RoomStatusDto, RoomStatus> ROOM_STATUS_MAPPER = new RoomStatusMapper();

    private static final Mapper<com.vladnickgofj.hotel.controller.dto.RoomTypeDto, RoomType> ROOM_TYPE_MAPPER = new RoomTypeMapper();

    private static final Mapper<RoomDtoResponse, Room> ROOM_MAPPER = new RoomResponseMapper();

    private static final Mapper<BookingDto, Booking> BOOKING_MAPPER = new BookingMapper();

    private static final Mapper<PaymentDto, Payment> PAYMENT_MAPPER = new PaymentMapper();

    private static final Mapper<UsersOrderDto, UsersOrder> USER_ORDER_MAPPER = new UsersOrderMapper();

    private static final UserValidator USER_VALIDATOR = new UserValidator();

    private static final BookingValidator BOOKING_VALIDATOR = new BookingValidator();

    private static final UsersOrderValidator USER_ORDER_VALIDATOR = new UsersOrderValidator();

    private static final PasswordEncryptionService PASSWORD_ENCRYPTION_SERVICE = new PasswordEncryptionService();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER, USER_VALIDATOR, PASSWORD_ENCRYPTION_SERVICE);

    private static final HotelService HOTEL_SERVICE = new HotelServiceImpl(HOTEL_DAO, HOTEL_MAPPER);

    private static final RoomService ROOM_SERVICE = new RoomServiceImpl(ROOM_DAO, ROOM_MAPPER);

    private static final RoomStatusService ROOM_STATUS_SERVICE = new RoomStatusServiceImpl(ROOM_STATUS_DAO, ROOM_STATUS_MAPPER, BOOKING_VALIDATOR);

    private static final RoomTypeService ROOM_TYPE_SERVICE = new RoomTypeServiceImpl(ROOM_TYPE_DAO, ROOM_TYPE_MAPPER);

    private static final PaymentService PAYMENT_SERVICE = new PaymentServiceImpl(PAYMENT_DAO, BOOKING_DAO, PAYMENT_MAPPER, BOOKING_VALIDATOR);

    private static final BookingService BOOKING_SERVICE = new BookingServiceImpl(BOOKING_DAO, BOOKING_MAPPER, ROOM_STATUS_MAPPER, BOOKING_VALIDATOR);

    private static final UsersOrderService USERS_ORDER_SERVICE = new UsersOrderServiceImpl(USER_ORDER_DAO, USER_ORDER_MAPPER, ROOM_STATUS_MAPPER, USER_ORDER_VALIDATOR);

    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);

    private static final Command LOGOUT_COMMAND = new LogoutCommand();

    private static final Command SHOW_USER_PROFILE_COMMAND = new ShowUserProfileCommand();

    private static final Command SHOW_ADMIN_PROFILE_COMMAND = new ShowAdminProfileCommand();

    private static final Command DEFAULT_COMMAND = new DefaultCommand();

    private static final Command PAYMENT_RECEIPT = new PaymentReceipt();

    private static final Command HOME_COMMAND = new HomePageCommand();

    private static final Command ABOUT_COMMAND = new AboutPageCommand();

    private static final Command CONTACTS_COMMAND = new ContactsPageCommand();

    private static final Command LOGIN_PAGE_COMMAND = new LoginPageCommand();

    private static final Command REGISTER_PAGE_COMMAND = new RegisterPageCommand();

    private static final Command SHOW_HOTELS_COMMAND = new ShowHotelsCommand();

    private static final Command SHOW_ROOMS_COMMAND = new ShowRoomsCommand();

    private static final Command CONFIRM_BOOKING_COMMAND = new ConfirmBookingCommand();

    private static final Command PAYMENT_BOOKING_COMMAND = new PaymentCommand();

    private static final Command CONFIRM_PAYMENT_COMMAND = new ConfirmPaymentCommand();

    private static final Command USERS_ORDER_COMMAND = new UsersOrderCommand();

    private static final Command USERS_ORDER_PAGE_COMMAND = new UsersOrderPageCommand();

    private static final Command CORRECT_ORDER_HANDLER_COMMAND = new CorrectOrderHandlerCommand();

    private static final Command ORDER_HANDLER_PAGE_COMMAND = new OrderHandlerPageCommand();

    private static final Command ORDER_COMPLETION_CONFIRMATION_COMMAND = new OrderCompletionConfirmationCommand();

    private static final Command ORDER_CANCELLATION_COMMAND = new OrderCancellationCommand();

    private static final Command GROUP_PAYMENT_COMMAND = new GroupPaymentCommand();

    private static final Command CONFIRM_GROUP_PAYMENT_COMMAND = new ConfirmGroupPaymentCommand();

    private static final Command GROUP_CANCEL_BOOKING_COMMAND = new GroupCancelBookingCommand();

    private static final Command CONFIRM_BOOKING_GROUP_CANCEL_COMMAND = new ConfirmBookingGroupCancelCommand();

    private static final Command CREATE_BOOKING_COMMAND = new CreateBookingCommand();

    private static final Command CONFIRM_BOOKING_PAGE_COMMAND = new ConfirmBookingPageCommand();

    private static final Command SUCCESS_GROUP_PAYMENT_PAGE_COMMAND = new SuccessGroupPaymentPageCommand();

    private static final Command SHOW_HOTELS_PAGE_COMMAND = new ShowHotelsPageCommand();

    private static final Command SUCCESS_PAYMENT_PAGE_COMMAND = new SuccessPaymentPageCommand();

    private static final Command GROUP_CANCEL_GET_COMMAND = new GroupCancelGetCommand();

    private static final Command SUCCESS_REGISTER_COMMAND = new SuccessRegisterCommand();

    private static final Command UNSUCCESSFUL_REGISTER_COMMAND = new UnsuccessfulRegisterCommand();

    private static final Command CANCELLATION_RECEIPT = new CancellationReceipt();

    private static final Command MY_ORDERS_PAGE_COMMAND = new MyOrdersPageCommand();

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
        homeCommandNameToCommand.put("successRegister", SUCCESS_REGISTER_COMMAND);
        homeCommandNameToCommand.put("unsuccessfulRegister", UNSUCCESSFUL_REGISTER_COMMAND);
        homeCommandNameToCommand.put("login", LOGIN_COMMAND);
        homeCommandNameToCommand.put("showUserProfile", SHOW_USER_PROFILE_COMMAND);
        homeCommandNameToCommand.put("showAdminProfile", SHOW_ADMIN_PROFILE_COMMAND);
        homeCommandNameToCommand.put("showHotels", SHOW_HOTELS_COMMAND);
        homeCommandNameToCommand.put("showRooms", SHOW_ROOMS_COMMAND);
        homeCommandNameToCommand.put("confirmBookingCommand", CONFIRM_BOOKING_COMMAND);
        homeCommandNameToCommand.put("payment", PAYMENT_BOOKING_COMMAND);
        homeCommandNameToCommand.put("groupPayment", GROUP_PAYMENT_COMMAND);
        homeCommandNameToCommand.put("confirmPayment", CONFIRM_PAYMENT_COMMAND);
        homeCommandNameToCommand.put("confirmGroupPayment", CONFIRM_GROUP_PAYMENT_COMMAND);
        homeCommandNameToCommand.put("usersOrder", USERS_ORDER_COMMAND);
        homeCommandNameToCommand.put("usersOrderPage", USERS_ORDER_PAGE_COMMAND);
        homeCommandNameToCommand.put("correctOrderHandler", CORRECT_ORDER_HANDLER_COMMAND);
        homeCommandNameToCommand.put("orderHandlerPage", ORDER_HANDLER_PAGE_COMMAND);
        homeCommandNameToCommand.put("OrderCompletionConfirmationCommand", ORDER_COMPLETION_CONFIRMATION_COMMAND);
        homeCommandNameToCommand.put("orderCancellation", ORDER_CANCELLATION_COMMAND);
        homeCommandNameToCommand.put("groupCancelBooking", GROUP_CANCEL_BOOKING_COMMAND);
        homeCommandNameToCommand.put("confirmBookingGroupCancel", CONFIRM_BOOKING_GROUP_CANCEL_COMMAND);
        homeCommandNameToCommand.put("createBookingCommand", CREATE_BOOKING_COMMAND);
        homeCommandNameToCommand.put("confirmBookingPageCommand", CONFIRM_BOOKING_PAGE_COMMAND);
        homeCommandNameToCommand.put("showHotelsPageCommand", SHOW_HOTELS_PAGE_COMMAND);
        homeCommandNameToCommand.put("successPaymentPage", SUCCESS_PAYMENT_PAGE_COMMAND);
        homeCommandNameToCommand.put("successGroupPaymentPage", SUCCESS_GROUP_PAYMENT_PAGE_COMMAND);
        homeCommandNameToCommand.put("groupCancelGetCommand", GROUP_CANCEL_GET_COMMAND);
        homeCommandNameToCommand.put("defaultCommand", DEFAULT_COMMAND);
        homeCommandNameToCommand.put("paymentReceipt", PAYMENT_RECEIPT);
        homeCommandNameToCommand.put("cancellationReceipt", CANCELLATION_RECEIPT);
        homeCommandNameToCommand.put("myOrdersPage", MY_ORDERS_PAGE_COMMAND);

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

    public PaymentService getPaymentService() {
        return PAYMENT_SERVICE;
    }

    public BookingService getBookingService() {
        return BOOKING_SERVICE;
    }

    public UsersOrderService getUsersOrderService() {
        return USERS_ORDER_SERVICE;
    }

    public Map<String, Command> getHomeCommandNameToCommand() {
        return HOME_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

}