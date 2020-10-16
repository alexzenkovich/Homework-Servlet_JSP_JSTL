package controllers;

import model.books.Book;
import persistance.BookDao;
import persistance.BookDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookController", urlPatterns = "/book")
public class BookController extends HttpServlet {

    private final BookDao bookDao = new BookDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        Book book = null;

        if ("info".equals(action)) {
            long bookId = Long.parseLong(request.getParameter("id"));
            book = bookDao.getById(bookId);
            request.setAttribute("bookInfo", book);
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
