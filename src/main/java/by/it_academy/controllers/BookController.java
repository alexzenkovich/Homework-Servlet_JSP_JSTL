package by.it_academy.controllers;

import by.it_academy.model.books.Book;
import by.it_academy.model.users.User;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static by.it_academy.constants.ErrorConstants.*;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/books")
    public ModelAndView loadBooksPage(@AuthenticationPrincipal User user){
        try{
            ModelAndView modelAndView = new ModelAndView("books");
            modelAndView.addObject("books", bookService.findAllBooks(10));
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/getBookInfo")
    public ModelAndView processBookInfoPage(@AuthenticationPrincipal User user,
                                            @RequestParam Long bookId) {
        try{
            ModelAndView modelAndView = new ModelAndView("book");
            modelAndView.addObject("book", bookService.findBookById(bookId));
            modelAndView.addObject("books", bookService.findAllBooks(10));
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        }catch (Exception e){
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/deleteBook")
    public ModelAndView deleteBook(@AuthenticationPrincipal User user,
                                   @RequestParam Long bookId) {
        try {
            bookService.deleteBookById(bookId);
            ModelAndView modelAndView = new ModelAndView("books");
            modelAndView.addObject("books", bookService.findAllBooks(10));
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        } catch(Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @GetMapping("/addBook")
    public ModelAndView loadAddingBookPage() {
        ModelAndView modelAndView = new ModelAndView("templates/add_book_form");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/addBook")
    public ModelAndView addBook(@AuthenticationPrincipal User user,
                                @Validated(value = Book.class) @ModelAttribute Book book) {
        try {
            ModelAndView modelAndView = new ModelAndView("books");
            if (book.getAuthor() == null || book.getAuthor().isEmpty()){
                modelAndView.addObject("error", INVALID_BOOK_AUTHOR);
            }
            if (book.getTitle() == null || book.getTitle().isEmpty()) {
                modelAndView.addObject("error", INVALID_BOOK_TITLE);
            }
            if (book.getNumberOfPages() == 0 || String.valueOf(book.getNumberOfPages()).isEmpty()) {
                modelAndView.addObject("error", INVALID_BOOK_NUMBER_OF_PAGES);
            }
            bookService.addBook(book);
            modelAndView.addObject("books", bookService.findAllBooks(10));
            modelAndView.addObject("numberOfBooksInBasket",
                    userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        }catch(Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }
}
