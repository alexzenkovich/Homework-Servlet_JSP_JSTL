package controllers;

import model.users.Authenticate;
import model.users.Role;
import model.users.User;
import persistance.BookDao;
import persistance.BookDaoImpl;
import persistance.UserDao;
import persistance.UserDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

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

        user = userDao.create(user);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(600);
            request.setAttribute("books", bookDao.getBooks());
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.setAttribute("exists", "login exists");
            request.setAttribute("books", bookDao.getBooks());
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
