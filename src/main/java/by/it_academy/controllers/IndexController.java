package by.it_academy.controllers;

import by.it_academy.model.users.User;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping({"/","/index"})
    public ModelAndView loadIndexPage(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        try{
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("books", bookService.findAllBooks(page, size));
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/index")
    public ModelAndView returnToIndex(@AuthenticationPrincipal User user,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        try {
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("books", bookService.findAllBooks(page, size));
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
