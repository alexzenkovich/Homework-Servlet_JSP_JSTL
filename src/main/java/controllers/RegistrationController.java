package controllers;

import model.Authenticate;
import model.Role;
import model.User;
import persistance.UserDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/registration")
public class RegistrationController extends HttpServlet {

    private final static UserDaoImpl USER_DAO = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User(name, surname, email, age, new Authenticate(login, password, false),
                Role.valueOf(role.toUpperCase()));

        user = USER_DAO.create(user);

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
