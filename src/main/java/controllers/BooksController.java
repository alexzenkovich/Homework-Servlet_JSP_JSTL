package controllers;

import persistance.dao.daoImpl.BookDaoImpl;
import persistance.dao.daoImpl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BooksController extends HttpServlet {

    private final BookDaoImpl bookDao = new BookDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", bookDao.getAll());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
