package by.it_academy.repositories;

import by.it_academy.model.books.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookById(Long id);

    @Query("select b from Book b left join fetch b.basketCells where b.id = ?1")
    Book findBookWithBasketCellsById(Long id);
}
