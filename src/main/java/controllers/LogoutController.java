package controllers;

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

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    private final BookDao bookDao = new BookDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        request.setAttribute("books", bookDao.getBooks());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
