package by.it_academy;

import by.it_academy.model.basket.Basket;
import by.it_academy.model.basket.BasketCell;
import by.it_academy.model.books.Book;
import by.it_academy.model.users.User;
import by.it_academy.services.BasketCellService;
import by.it_academy.services.BasketService;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Runner {

    private static final ApplicationContext CONTEXT = new AnnotationConfigApplicationContext("by.it_academy");

    public static void main(String[] args) {

        BookService bookService = CONTEXT.getBean(BookService.class);
        UserService userService = CONTEXT.getBean(UserService.class);
        BasketService basketService = CONTEXT.getBean(BasketService.class);
        BasketCellService basketCellService = CONTEXT.getBean(BasketCellService.class);

//        System.out.println(userService.findUserByAuthenticate_LoginAndAuthenticate_Password("admin1", "1"));
        long bookId = 3L;
        long userId = 3L;
        int daysForReading = 2;

        userService.addBookToUser(userId, bookId, daysForReading);

        System.out.println(userService.countUserBasketBasketCellsById(userId));
    }
}
