package by.itacademy.controllers;

import by.itacademy.model.users.Role;
import by.itacademy.model.users.User;
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
import java.io.IOException;
import static by.itacademy.constants.ErrorConstants.*;
@Controller
public class ChangeController {

    @Autowired
    private UserService userService;

    @GetMapping("/update")
    protected ModelAndView loadEditProfilePage() {
        ModelAndView modelAndView = new ModelAndView("templates/edit_profile");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView processEditProfilePage(@ModelAttribute User user) {

        try {
            checkUserForm(user);

        } catch () {

        }
    }

    static void checkUserForm(@ModelAttribute User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new RuntimeException(INVALID_USER_NAME);
        }
        if (user.getSurname() == null || user.getSurname().isEmpty()) {
            throw new RuntimeException(INVALID_USER_SURNAME);
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException(INVALID_USER_EMAIL);
        }
        if (user.getAge() == 0 || String.valueOf(user.getAge()).isEmpty()) {
            throw new RuntimeException(INVALID_USER_AGE);
        }
        if (user.getAuthenticate().getLogin() == null || user.getAuthenticate().getLogin().isEmpty()) {
            throw new RuntimeException(INVALID_USER_LOGIN);
        }
        if (user.getAuthenticate().getPassword() == null || user.getAuthenticate().getPassword().isEmpty()) {
            throw new RuntimeException(INVALID_USER_PASSWORD);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = (User) request.getSession().getAttribute("user");

        if (name != null) {
            user.setName(name);
        }
        if (surname != null) {
            user.setSurname(surname);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (age != 0) {
            user.setAge(age);
        }
        if (login != null) {
            user.getAuthenticate().setLogin(login);
        }
        if (password != null) {
            user.getAuthenticate().setPassword(password);
        }
        userDao.update(user);
        
        getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = Long.parseLong(request.getParameter("userId"));
        long adminId = Long.parseLong(request.getParameter("adminId"));

        User user = userDao.getById(userId);
        User admin = userDao.getById(adminId);

        try {
            if (request.getParameter("action").equals("update")) {
                user.setRole(Role.ADMINISTRATOR);
                userDao.update(user);
                if (admin.getRole() == Role.ADMINISTRATOR) {
                    request.setAttribute("users", userDao.getAll());
                }
                getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
                return;
            }
            if (request.getParameter("action").equals("delete")) {
                userDao.delete(userId);
                if (admin.getRole() == Role.ADMINISTRATOR) {
                    request.setAttribute("users", userDao.getAll());
                }
                getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
            }
        } catch (RuntimeException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("/index.jsp");
        }
    }
}
