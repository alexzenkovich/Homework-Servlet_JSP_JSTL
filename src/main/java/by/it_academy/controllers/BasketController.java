package by.it_academy.controllers;

import by.it_academy.exception.ApplicationBaseException;
import by.it_academy.model.basket.BasketCell;
import by.it_academy.model.users.User;
import by.it_academy.services.BasketCellService;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static by.it_academy.constants.Constants.*;
import static by.it_academy.constants.ErrorConstants.*;

@Controller
public class BasketController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping("/basket")
    public ModelAndView processBasketPage(@SessionAttribute("user") User user) {
        try {

            List<BasketCell> basketCells = userService.findUserWithBasketCellsWithBooksById(user.getId());
            if (basketCells.size() == 0) {
                throw new RuntimeException(YOUR_BASKET_IS_EMPTY);
            }
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("booksInBasket", basketCells);
            modelAndView.setViewName("basket");
            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.addObject("books", bookService.findAllBooks());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            modelAndView.setViewName("index");
            return modelAndView;
        }
    }

    @PostMapping("/addToBasket")
    public ModelAndView addBookToBasket(@SessionAttribute("user") User user,
                                        @RequestParam int daysForReading,
                                        @RequestParam long bookId) {
        try {
            if (daysForReading == 0) {
                throw new ApplicationBaseException(INVALID_FIELD_DATA);
            }
            userService.addBookToUser(user.getId(), bookId, daysForReading);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("books", bookService.findAllBooks());
            modelAndView.addObject("basketMessage", BOOK_WAS_ADDED_TO_BASKET);
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));
            modelAndView.setViewName("index");

            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.addObject("books", bookService.findAllBooks());
            modelAndView.setViewName("index");
            return modelAndView;
        }
    }

    @PostMapping("/deleteFromBasket")
    public ModelAndView processDeleteBasket(@SessionAttribute("user") User user,
                                            @RequestParam Long basketCellId) {
        try {
            userService.deleteBookByUserId(user.getId(), basketCellId);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("booksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));
            modelAndView.setViewName("basket");
            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.addObject("books", bookService.findAllBooks());
            modelAndView.setViewName("basket");
            return modelAndView;
        }
    }
}
