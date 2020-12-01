package by.it_academy.controllers;

import by.it_academy.exception.ApplicationBaseException;
import by.it_academy.model.users.User;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

import static by.it_academy.constants.ErrorConstants.*;

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
    public ModelAndView processLogin(HttpSession httpSession,
                                     @RequestParam String login,
                                     @RequestParam String password) {
        try {
            if (login == null || login.isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_LOGIN);
            }
            if (password == null || password.isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_PASSWORD);
            }
            if (!userService.existsUserByAuthenticateLogin(login)) {
                throw new ApplicationBaseException(USER_NOT_EXISTS);
            }

            User user = userService.findUserWithAuthenticateByLoginAndPassword(login, password);

            httpSession.setAttribute("user", user);
            httpSession.setMaxInactiveInterval(120);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("books", bookService.findAllBooks());
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));

            modelAndView.setViewName("index");

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("templates/login");
            return modelAndView;
        }
    }

    @GetMapping("/logout")
    protected ModelAndView loadLogoutPage() {
        return new ModelAndView("templates/logout");
    }

    @PostMapping("/logout")
    public ModelAndView processLogout(HttpSession httpSession,
                                      @ModelAttribute User user) {
        httpSession.invalidate();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", bookService.findAllBooks());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
