import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.command.Command;
import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PaginateHotelDto;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.HotelDao;
import com.vladnickgofj.hotel.dao.RoomDao;
import com.vladnickgofj.hotel.dao.UserDao;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Payment;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.dao.impl.*;
import com.vladnickgofj.hotel.service.HotelService;
import com.vladnickgofj.hotel.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        HikariConnectionPool connectionPool = new HikariConnectionPool("bd-config");
//        UserDaoImpl userDao = new UserDaoImpl(connectionPool);
//        RoomDaoImpl roomDao = new RoomDaoImpl(connectionPool);
        HotelDaoImpl hotelDao = new HotelDaoImpl(connectionPool);
//        BookingStatusDaoImpl bookingStatusDao = new BookingStatusDaoImpl(connectionPool);
//        RoomTypeDaoImpl roomTypeDao = new RoomTypeDaoImpl(connectionPool);
//        BookingDaoImpl bookingDao = new BookingDaoImpl(connectionPool);
//        PaymentDaoImpl paymentDao = new PaymentDaoImpl(connectionPool);
//        RoomStatusDaoImpl roomStatusDao = new RoomStatusDaoImpl(connectionPool);
//
//        System.out.println("userDao---------------------------");
//        userDao.findAll().forEach(System.out::println);
//        System.out.println("roomDao---------------------------");
//        roomDao.findAll().forEach(System.out::println);
//        System.out.println("hotelDao---------------------------");
//        hotelDao.findAll().forEach(System.out::println);
//        System.out.println("bookingStatusDao---------------------------");
//        bookingStatusDao.findAll().forEach(System.out::println);
//        System.out.println("roomTypeDao---------------------------");
//        roomTypeDao.findAll().forEach(System.out::println);
//        System.out.println("bookingDao---------------------------");
//        bookingDao.findAll().forEach(System.out::println);
//        System.out.println("paymentDao---------------------------");
//        paymentDao.findAll().forEach(System.out::println);
//        System.out.println("roomStatusDao---------------------------");
//        roomStatusDao.findAll().forEach(System.out::println);
//
//
//        System.out.println("userDaoById---------------------------");
//        userDao.findById(3).ifPresent(System.out::println);
//        System.out.println("roomDaoById---------------------------");
//        roomDao.findById(3).ifPresent(System.out::println);
//        System.out.println("hotelDaoById---------------------------");
//        hotelDao.findById(2).ifPresent(System.out::println);
//        System.out.println("bookingStatusDaoById---------------------------");
//        bookingStatusDao.findById(2).ifPresent(System.out::println);
//        System.out.println("roomTypeDaoById---------------------------");
//        roomTypeDao.findById(1).ifPresent(System.out::println);
//        System.out.println("bookingDaoById---------------------------");
//        bookingDao.findById(3).ifPresent(System.out::println);
//        System.out.println("paymentDaoById---------------------------");
//        paymentDao.findById(5).ifPresent(System.out::println);
//        System.out.println("roomStatusDaoById---------------------------");
//        roomStatusDao.findById(3).ifPresent(System.out::println);


        ApplicationContextInjector contextInjector=ApplicationContextInjector.getInstance();
        HotelService hotelService = contextInjector.getHotelService();
        List<HotelDto> all1 = hotelService.findAll(PaginateHotelDto.newBuilder()
                .numberOfPage(3)
                .hotelsOnPage(10)
                .build());
        all1.stream().map(HotelDto::getName).forEach(System.out::println);


    }
}
