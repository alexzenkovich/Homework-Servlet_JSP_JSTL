package controllers;

import model.Role;
import model.User;
import persistance.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CRUDController", urlPatterns = {"/change"})
public class CRUDController extends HttpServlet {

    private final UserDaoImpl userDao = new UserDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long userId = Long.valueOf(request.getParameter("userId"));

        User user = userDao.getById(userId);

        try {
            if (request.getParameter("action").equals("update")) {
                User u = new User();
                u.setId(Long.valueOf(request.getParameter("userId")));
                u.setLogin(request.getParameter("login"));
                u.setPassword(request.getParameter("password"));
                u.setRole(Role.ADMIN);
                userDao.update(u);
                if (user.getRole() == Role.ADMIN) {
                    request.setAttribute("users", userDao.getUsers());
                }
                getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
                return;
            }
            if (request.getParameter("action").equals("delete")) {
                userDao.delete(Long.valueOf(request.getParameter("userId")));
                if (user.getRole() == Role.ADMIN) {
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
