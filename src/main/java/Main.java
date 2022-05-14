


import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.entity.Role;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.dao.impl.UserDaoImpl;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
//import java.util.logging.Level;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException, SQLException {
        UserDaoImpl userDao = new UserDaoImpl(new HikariConnectionPool("bd-config"));
        List<User> all = userDao.findAll();

        all.stream()
                .filter(t -> t.getRole() == Role.ADMIN)
                .map(t -> t.getLastName() + " " + t.getRole())
                .forEach(t -> System.out.println(t));

        Optional<User> byEmail = userDao.findByEmail("ivan@mail.com");
        System.out.println(byEmail);
    }
}
