package by.it_academy.controllers;

import by.it_academy.model.basket.BasketCell;
import by.it_academy.model.users.User;
import by.it_academy.services.BasketCellService;
import by.it_academy.services.BasketService;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView loadIndexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", bookService.findAllBooks());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/index")
    public ModelAndView returnToIndex(@SessionAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("books", bookService.findAllBooks());
        modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
        return modelAndView;
    }
}
