package by.itacademy.controllers;

import by.itacademy.model.basket.Basket;
import by.itacademy.model.users.Authenticate;
import by.itacademy.model.users.Role;
import by.itacademy.model.users.User;
import by.itacademy.services.BookService;
import by.itacademy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.itacademy.constants.ErrorConstants.*;

@Controller
public class RegistrationController{

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @GetMapping("/registration")
    protected ModelAndView loadRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("templates/registration");

        User user = new User();
        user.setRole(Role.USER);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView processRegistration(@ModelAttribute User user) {
        try {

            ChangeController.checkUserForm(user);

            if (userService.isUserLoginExist(user.getAuthenticate().getLogin())) {
                throw new RuntimeException(INVALID_USER_REGISTRATION_DATA);
            }

            user = userService.create(user);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("index");

            if (user.getRole() == Role.ADMINISTRATOR) {
                modelAndView.addObject("users", userService.findAllUsers());
            }

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("registration");

            return modelAndView;
        }
    }
}
