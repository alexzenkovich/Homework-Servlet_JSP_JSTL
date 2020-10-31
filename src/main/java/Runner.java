import connection.DBConnection;
import connection.JDBCConnection;
import constants.Constants;
import exception.ApplicationBaseException;
import model.basket.Basket;
import model.users.Authenticate;
import model.users.Role;
import model.users.User;
import org.h2.tools.RunScript;
import org.h2.tools.Server;
import persistance.dao.daoImpl.BookDaoImpl;
import persistance.dao.daoImpl.UserDaoImpl;

import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

public class Runner {

    public static void main(String[] args) {


        UserDaoImpl userDao = new UserDaoImpl();
        BookDaoImpl bookDao = new BookDaoImpl();

        System.out.println();
        System.out.println("# getAll Users ");
//        userDao.getAll().forEach(System.out::println);
        bookDao.getAll().forEach(System.out::println);

        System.out.println();

        System.out.println(userDao.getUserByLoginAndPassword("admin1", "1"));


//        System.out.println("# create User and getById without role");
//        User user = new User("Jimmy", "White", "jimmy_white@it-academy.com", 25);
//        user.setAuthenticate(new Authenticate("jim", "jim"));
//        user.setRole(Role.USER);
//        user.setBasket(new Basket());
//        userDao.create(user);


//        System.out.println();
//        System.out.println("# getUserByIdWithRole ");
//        System.out.println(userDao.getUserByIdWithRole(user.getId()));


//        System.out.println();
//        System.out.println("# delete User and getAll");
//        userDao.delete(10L);
//        userDao.getAll().forEach(System.out::println);


//        System.out.println();
//        System.out.println("# book dao sample");
//        BookDaoJdbcImpl bookDaoJdbc = new BookDaoJdbcImpl();
//        bookDaoJdbc.getAll().forEach(System.out::println);
//        System.out.println(bookDaoJdbc.getById(2L));

//        SERVER.stop();
    }
}
