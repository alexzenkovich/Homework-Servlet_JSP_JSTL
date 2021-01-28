package by.it_academy.controllers;

import by.it_academy.model.basket.BasketCell;
import by.it_academy.model.users.User;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ModelAndView processBasketPage(@AuthenticationPrincipal User user,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        try {
            List<BasketCell> basketCells = userService.findUserWithBasketCellsWithBooksById(user.getId());
            ModelAndView modelAndView = new ModelAndView();
            if (basketCells.size() == 0) {
                modelAndView.setViewName("index");
                modelAndView.addObject("books", bookService.findAllBooks(page, size));
                modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
                modelAndView.addObject("error", YOUR_BASKET_IS_EMPTY);
            } else {
                modelAndView.addObject("booksInBasket", basketCells);
                modelAndView.setViewName("basket");
            }
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/addToBasket")
    public ModelAndView addBookToBasket(@AuthenticationPrincipal User user,
                                        @RequestParam int daysForReading,
                                        @RequestParam long bookId,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        try {
            ModelAndView modelAndView = new ModelAndView("index");
            if (daysForReading == 0) {
                modelAndView.addObject("basketMessage", INVALID_FIELD_DATA);
            } else {
                userService.addBookToUser(user.getId(), bookId, daysForReading);
                modelAndView.addObject("basketMessage", BOOK_WAS_ADDED_TO_BASKET);
            }
            modelAndView.addObject("books", bookService.findAllBooks(page, size));
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/deleteFromBasket")
    public ModelAndView processDeleteBasket(@AuthenticationPrincipal User user,
                                            @RequestParam Long basketCellId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        try {
            userService.deleteBookByUserId(user.getId(), basketCellId);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("basketMessage", BOOK_WAS_DELETED_FROM_BASKET);
            modelAndView.addObject("books", bookService.findAllBooks(page, size));
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));
            modelAndView.setViewName("basket");
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }
}
