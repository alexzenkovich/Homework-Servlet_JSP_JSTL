package by.itacademy.controllers;

import by.itacademy.model.users.Role;
import by.itacademy.model.users.User;
import by.itacademy.persistance.repositories.repositoryImpl.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangeController", urlPatterns = "/change")
public class ChangeController extends HttpServlet {

    private final UserRepositoryImpl userDao = new UserRepositoryImpl();

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
        
        getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
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
                getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
                return;
            }
            if (request.getParameter("action").equals("delete")) {
                userDao.delete(userId);
                if (admin.getRole() == Role.ADMINISTRATOR) {
                    request.setAttribute("users", userDao.getAll());
                }
                getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
            }
        } catch (RuntimeException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("/index.jsp");
        }
    }
}
