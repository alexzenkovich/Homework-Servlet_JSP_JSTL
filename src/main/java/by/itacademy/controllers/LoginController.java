package by.itacademy.controllers;

import by.itacademy.model.users.Role;
import by.itacademy.model.users.User;
import by.itacademy.services.BookService;
import by.itacademy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static by.itacademy.constants.ErrorConstants.*;

@Controller
public class LoginController{

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @GetMapping("/login")
    protected ModelAndView loadLoginPage() {
        return new ModelAndView("templates/login");
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam String login, @RequestParam String password) {
        try {
            if (login == null || login.isEmpty()) {
                throw new RuntimeException(INVALID_USER_LOGIN);
            }
            if (password == null || password.isEmpty()) {
                throw new RuntimeException(INVALID_USER_PASSWORD);
            }
            if (!userService.isUserLoginExist(login)) {
                throw new RuntimeException(USER_NOT_EXISTS);
            }

            User user = userService.findUserByAuthenticate_LoginAndAuthenticate_Password(login, password);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.addObject("books", bookService.findAllBooks());

            if (user.getRole() == Role.ADMINISTRATOR) {
                List<User> users = userService.findAllUsers();
                modelAndView.addObject("users", users);
            }

            modelAndView.setViewName("index");

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @GetMapping("/logout")
    protected ModelAndView loadLogoutPage() {
        return new ModelAndView("templates/logout");
    }

    @PostMapping("/logout")
    public ModelAndView processLogout(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", null);
        modelAndView.addObject("books", bookService.findAllBooks());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
