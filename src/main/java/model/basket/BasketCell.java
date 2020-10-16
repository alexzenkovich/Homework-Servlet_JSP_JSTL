package model.basket;

import lombok.*;
import model.books.Book;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@EqualsAndHashCode

public class BasketCell {
    private long id;
    private Book book;
    private LocalDate dateOfTakingBook;
    private int daysForReading;

    public BasketCell(Book book, LocalDate dateOfTakingBook, int daysForReading) {
        this.book = book;
        this.dateOfTakingBook = dateOfTakingBook;
        this.daysForReading = daysForReading;
    }
}
