package by.it_academy.controllers;

import by.it_academy.exception.ApplicationBaseException;
import by.it_academy.model.users.Authenticate;
import by.it_academy.model.users.User;
import by.it_academy.services.BasketCellService;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static by.it_academy.constants.ErrorConstants.*;

@Controller
public class RegistrationController{

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BasketCellService basketCellService;

    @GetMapping("/registration")
    public ModelAndView loadRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("templates/registration");

        modelAndView.addObject("user", new User());
        modelAndView.addObject("authenticate", new Authenticate());
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView processRegistration(HttpSession httpSession,
                                            @Validated(value = User.class)
                                            @ModelAttribute User user,
                                            @ModelAttribute Authenticate authenticate) {
        try {
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
            if (user.getAuthenticate().getLogin() == null || user.getAuthenticate().getLogin().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_LOGIN);
            }
            if (user.getAuthenticate().getPassword() == null || user.getAuthenticate().getPassword().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_PASSWORD);
            }
            if (userService.existsUserByAuthenticateLogin(authenticate.getLogin())) {
                throw new ApplicationBaseException(INVALID_USER_REGISTRATION_DATA);
            }
            authenticate.setProfileEnable(true);
            user.setAuthenticate(authenticate);

            user = userService.create(user);
            user = userService.findUserWithAuthenticateAndBasketById(user.getId());
            httpSession.setAttribute("user", user);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("books", bookService.findAllBooks());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            modelAndView.setViewName("index");

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("templates/registration");
            return modelAndView;
        }
    }
}
