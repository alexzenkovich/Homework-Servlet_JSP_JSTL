package persistance;

import model.basket.Basket;
import model.basket.BasketCell;
import model.books.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class BasketDaoImpl  {

    private final static Map<Long, BasketCell> BASKET_CELL_MAP = new HashMap<>();
    private final static AtomicLong BOOK_IN_BASKET_ID = new AtomicLong(1);

    public void save(BasketCell basketCell) {
        long id = BOOK_IN_BASKET_ID.getAndIncrement();
        basketCell.setId(id);
        BASKET_CELL_MAP.put(id, basketCell);
    }

    public BasketCell getById(long id) {
        return BASKET_CELL_MAP.values().stream().filter(cell -> cell.getBook().getId()==id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No exists this book"));
    }

    public List<Book> getBooks() {
        return BASKET_CELL_MAP.values().stream().map(BasketCell::getBook).collect(Collectors.toList());
    }

    public void delete(long id) {
        BASKET_CELL_MAP.remove(id);
    }
}
