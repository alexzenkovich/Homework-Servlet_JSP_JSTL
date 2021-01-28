package by.it_academy.services;

import by.it_academy.exception.ApplicationBaseException;
import by.it_academy.model.books.Book;
import by.it_academy.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.it_academy.constants.ErrorConstants.*;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        String author;
        String title;
        int nop;
        for (int i = 0; i < 100; i++) {
            author = String.valueOf((char) (Math.random() * 100));
            title = String.valueOf((char) (Math.random() * 100));
            nop = (int) (Math.random() * 1000);
            bookRepository.save(new Book(author, title, nop));
        }
//        bookRepository.save(new Book("Arthur Haily", "Airport", 5));
//        bookRepository.save(new Book("Korney Chukovskiy", "Aibolit", 4));
//        bookRepository.save(new Book("Korney Chukovskiy", "Aibolit", 4));
//        bookRepository.save(new Book("Korney Chukovskiy", "Moidodyr", 8));
//        bookRepository.save(new Book("Korney Chukovskiy", "Tarakanishche", 3));
    }

    public List<Book> findAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Book> books = bookRepository.findAll(pageable);
        if (!books.hasContent()){
            throw new ApplicationBaseException(HAS_NO_ANY_BOOKS);
        }
        return books.getContent();
    }
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

//    public ResponseEntity<Map<String, Object>> getAllTutorialsPage(int page, int size, String[] sort) {
//
//        try {
//            List<Order> orders = new ArrayList<>();
//
//            if (sort[0].contains(",")) {
//                // will sort more than 2 fields
//                // sortOrder="field, direction"
//                for (String sortOrder : sort) {
//                    String[] _sort = sortOrder.split(",");
//                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
//                }
//            } else {
//                // sort=[field, direction]
//                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
//            }
//
//            List<Book> books = new ArrayList<Book>();
//            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
//
//            Page<Book> pageBooks = bookRepository.findAll(pagingSort);
//
//            books = pageBooks.getContent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("books", books);
//            response.put("currentPage", pageBooks.getNumber());
//            response.put("totalItems", pageBooks.getTotalElements());
//            response.put("totalPages", pageBooks.getTotalPages());
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    public Book findBookById(Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}
