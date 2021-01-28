package by.it_academy.controllers;

import by.it_academy.exception.ApplicationBaseException;
import by.it_academy.model.users.User;
import by.it_academy.services.BookService;
import by.it_academy.services.UserSecurityService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Autowired
    private UserSecurityService userSecurityService;

    @GetMapping("/login")
    protected ModelAndView loadLoginPage() {

        return new ModelAndView("templates/login");
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam String login,
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

            User user = (User) userSecurityService.loadUserByUsername(login);
            if (!user.isEnabled()){
                throw new ApplicationBaseException("userDisable");
            }

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("books", bookService.findAllBooks(10));
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));

            modelAndView.setViewName("index");

            return modelAndView;

        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("templates/login");
            return modelAndView;
        }
    }

    @PostMapping("/logout")
    public ModelAndView processLogout(HttpSession httpSession) {
        httpSession.invalidate();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("books", bookService.findAllBooks(10));
        return modelAndView;
    }

    @PostMapping("/login/sendMessage")
    public ModelAndView sendMessageToAdmin(@RequestParam String message,
                                           @AuthenticationPrincipal User user){
        boolean messageWasSent = userService.sendMessageToAdmin(message, user.getId());
        ModelAndView modelAndView = new ModelAndView("templates/login");
        if (messageWasSent){
            modelAndView.addObject("messageAccept", ACCEPT_MESSAGE_SENDING);
        } else {
            modelAndView.addObject("messageAccept", SENDING_MESSAGE_FAILED);
        }
        return modelAndView;
    }
}
