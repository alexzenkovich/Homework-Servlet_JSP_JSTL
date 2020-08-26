package controllers;

import model.Role;
import model.User;
import persistance.UserDaoImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet(value = "/registration", initParams = {
        @WebInitParam(name = "admin1", value = "admin1;1"),
        @WebInitParam(name = "admin2", value = "admin2;1")
})
public class RegistrationController extends HttpServlet {

    private final static UserDaoImpl USER_DAO = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");


        User user = new User(login, password, Role.valueOf(role.toUpperCase()));

        user = USER_DAO.save(user);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(600);
            getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
        }

        request.setAttribute("exists", "login exists");
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
