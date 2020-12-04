package by.it_academy.controllers;

import by.it_academy.model.users.User;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView loadHomePage() {
        return methodLoadIndexPage();
    }

    @GetMapping("/index")
    public ModelAndView loadIndexPage() {
        return methodLoadIndexPage();
    }

    private ModelAndView methodLoadIndexPage() {
        try{
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("books", bookService.findAllBooks());
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/index")
    public ModelAndView returnToIndex(@AuthenticationPrincipal User user) {
        try {
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("books", bookService.findAllBooks());
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        } catch (Exception e){
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }
}
