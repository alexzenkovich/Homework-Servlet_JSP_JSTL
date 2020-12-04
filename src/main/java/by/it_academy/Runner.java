package by.it_academy;

import by.it_academy.services.BasketCellService;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Runner {

    private static final ApplicationContext CONTEXT = new AnnotationConfigApplicationContext("by.it_academy");

    public static void main(String[] args) {

        BookService bookService = CONTEXT.getBean(BookService.class);
        UserService userService = CONTEXT.getBean(UserService.class);

//        System.out.println(userService.findUserByAuthenticate_LoginAndAuthenticate_Password("admin1", "1"));
        long bookId = 3L;
        long userId = 3L;
        int daysForReading = 2;

        userService.addBookToUser(userId, bookId, daysForReading);

        System.out.println(userService.countUserBasketBasketCellsById(userId));

    }
}
