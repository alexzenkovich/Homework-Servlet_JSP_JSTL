package by.it_academy.model.books;

import by.it_academy.model.basket.BasketCell;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "basketCells")
@ToString(exclude = "basketCells")

@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String author;
    private String title;
    private int numberOfPages;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<BasketCell> basketCells = new HashSet<>();

    public Book(String author, String title, int numberOfPages) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public void addBasketCell(BasketCell basketCell) {
        basketCell.setBook(this);
        this.getBasketCells().add(basketCell);
    }

    public void deleteBasketCell(BasketCell basketCell) {
        basketCell.setBook(null);
        this.getBasketCells().remove(basketCell);
    }
}
