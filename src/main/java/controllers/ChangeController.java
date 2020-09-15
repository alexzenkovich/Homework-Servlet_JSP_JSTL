package controllers;

import model.Role;
import model.User;
import persistance.UserDao;
import persistance.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangeController", urlPatterns = "/change")
public class ChangeController extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                    request.setAttribute("users", userDao.getUsers());
                }
                getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
                return;
            }
            if (request.getParameter("action").equals("delete")) {
                userDao.delete(userId);
                if (admin.getRole() == Role.ADMINISTRATOR) {
                    request.setAttribute("users", userDao.getUsers());
                }
                getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
            }
        } catch (RuntimeException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("/index.jsp");
        }
    }
}
