package by.itacademy.controllers;

import by.itacademy.model.books.Book;
import by.itacademy.persistance.repositories.repositoryImpl.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BookRepositoryImpl bookDao;

    @GetMapping("/")
    protected ModelAndView loadIndexPage() {return new ModelAndView("index");}

    @PostMapping
    public ModelAndView processIndex() {
        List<Book> books = bookDao.getAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", books);
        modelAndView.setViewName("index");

        return modelAndView;
    }
}
