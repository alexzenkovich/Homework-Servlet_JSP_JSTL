package by.itacademy.controllers;

import by.itacademy.model.books.Book;
import by.itacademy.persistance.repositories.repositoryImpl.BookRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookController", urlPatterns = "/book")
public class BookController extends HttpServlet {

    private final BookRepositoryImpl bookDao = new BookRepositoryImpl();

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
