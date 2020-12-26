package by.it_academy.controllers;

import by.it_academy.exception.ApplicationBaseException;
import by.it_academy.model.basket.Basket;
import by.it_academy.model.users.Authenticate;
import by.it_academy.model.users.Role;
import by.it_academy.model.users.User;
import by.it_academy.services.BookService;
import by.it_academy.services.UserSecurityService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import static by.it_academy.constants.ErrorConstants.*;

@Controller
public class RegistrationController{

    @Autowired
    private UserService userService;
    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private BookService bookService;

    @GetMapping("/registration")
    public ModelAndView loadRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("templates/registration");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("authenticate", new Authenticate());
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView processRegistration(@Validated(value = User.class)
                                            @ModelAttribute("user") User user,
                                            @ModelAttribute("authenticate") Authenticate authenticate) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            if (user.getName() == null || user.getName().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_NAME);
            }
            if (user.getSurname() == null || user.getSurname().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_SURNAME);
            }
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_EMAIL);
            }
            if (user.getAge() == 0 || String.valueOf(user.getAge()).isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_AGE);
            }
            if (authenticate.getLogin() == null || authenticate.getLogin().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_LOGIN);
            }
            if (authenticate.getPassword() == null || authenticate.getPassword().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_PASSWORD);
            }
            if (userService.existsUserByAuthenticateLogin(authenticate.getLogin())) {
                throw new ApplicationBaseException(INVALID_USER_REGISTRATION_DATA);
            }

            User userFromDB = userService.findUserByAuthenticateLogin(authenticate.getLogin());
            if(userFromDB != null) {
                modelAndView.addObject("error", USER_ALREADY_EXISTS);
                modelAndView.setViewName("templates/registration");
                return modelAndView;
            }

            user.setAuthenticate(authenticate);
            user.setBasket(new Basket());
            user.setRoles(Collections.singleton(Role.USER));
            userService.create(user);
            modelAndView.addObject("books", bookService.findAllBooks());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            modelAndView.setViewName("index");

            return modelAndView;

        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }
}
