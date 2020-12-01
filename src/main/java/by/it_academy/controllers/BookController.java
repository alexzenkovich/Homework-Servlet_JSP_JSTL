package by.it_academy.controllers;

import by.it_academy.model.users.User;
import by.it_academy.services.BasketCellService;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BasketCellService basketCellService;

    @PostMapping("/getBookInfo")
    public ModelAndView processBookInfoPage(@SessionAttribute("user") User user,
                                            @RequestParam Long bookId) {
        ModelAndView modelAndView = new ModelAndView("book");
        modelAndView.addObject("book", bookService.findBookById(bookId));
        modelAndView.addObject("numberOfBooksInBasket",
                basketCellService.countBasketCellsByBasket_Id(user.getBasket().getId()));
        return modelAndView;
    }
}
