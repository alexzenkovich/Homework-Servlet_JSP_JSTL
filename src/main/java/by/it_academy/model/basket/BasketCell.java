package by.it_academy.model.basket;

import by.it_academy.model.books.Book;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"book", "basket"})
@ToString(exclude = {"book", "basket"})

@Entity
@Table
public class BasketCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateOfTakingBook;

    private int daysForReading;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;

    public BasketCell(Date dateOfTakingBook, int daysForReading) {

        this.dateOfTakingBook = dateOfTakingBook;
        this.daysForReading = daysForReading;
    }
}
