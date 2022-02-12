import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.entity.Role;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.dao.impl.UserDaoImpl;

public class Main {

    public static final Integer NUMBER = null;
    private static final int DEFAULT_USER_ROLE_ID = 2;

    public static void main(String[] args) {
//        UserRepository userRepository = new UserRepository();
//        userRepository.save(new User(1,"Ivan","Petrov","123@asd.com","1111",Role.USER));
//
//        userRepository.findById(5);
//        User user=new User(4,"John","Doe","name@email.com","111",Role.ADMIN);
//        userRepository.update(user);
//        userRepository.delete(4);


        /*----------------------------------------------*/

        HikariConnectionPool hikariConnectionPool = new HikariConnectionPool("bd-config");

        UserDaoImpl userDao = new UserDaoImpl(hikariConnectionPool);

//        Optional<User> byId = userDao.findById(2);
//        userDao.findAll();
//        System.out.println(byId);
//        userDao.findByEmail("ayj@asd.asd");
//        userDao.findAll();
//        userDao.save();
        userDao.update(new User(1, "St", "Hauss", "stepan@another.asd", "1234", Role.ADMIN));

//        userDao.save(new User(7, "Another", "Another", "another@another.asd", "1234", Role.USER));
//        userDao.findAll();
//
//        equalsObj(2, 5);
//        Optional<Integer> user = Optional.ofNullable(NUMBER);
//
//        if (user.isPresent()) {
//            equalsObj(user.get(), 5);
//        }
//
//        user.orElse("Other");


    }

//    private static boolean equalsObj(Integer number,Integer number2) {
//       return number.equals(number2);
//    }
}
