package by.itacademy.controllers;

import by.itacademy.model.users.User;
import by.itacademy.persistance.repositories.repositoryImpl.BookRepositoryImpl;
import by.itacademy.persistance.repositories.repositoryImpl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static by.itacademy.constants.ErrorConstants.*;

@Controller
public class LoginController{

    @Autowired
    private UserRepositoryImpl userDao;
    @Autowired
    private BookRepositoryImpl bookDao;

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
            if (!userDao.isLoginExists(login)) {
                throw new RuntimeException(USER_NOT_EXISTS);
            }

            User user = userDao.getUserByLoginAndPassword(login, password);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("index");

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

}
