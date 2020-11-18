package by.itacademy.model.books;

import by.itacademy.model.basket.BasketCell;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString

@Entity
@Table
public class Book {

    @Id
    @GeneratedValue
    private long id;

    private String author;
    private String title;
    private int numberOfPages;

    @OneToMany
    private Set<BasketCell> basketCells = new HashSet<>();

    public Book(String author, String title, int numberOfPages) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }
}
