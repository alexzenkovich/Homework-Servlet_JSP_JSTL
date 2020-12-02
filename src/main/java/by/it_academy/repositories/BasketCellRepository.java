package by.it_academy.repositories;

import by.it_academy.model.basket.BasketCell;
import by.it_academy.model.books.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BasketCellRepository extends JpaRepository<BasketCell, Long> {

    BasketCell findBasketCellById(Long id);

    Integer countBasketCellsByBasket_Id(Long id);

    @Query("select count(id) from BasketCell where basket.user.id = ?1")
    Integer countBasketCellsByUserId(Long id);

    @EntityGraph(attributePaths = {"basket", "book"})
    @Query("from BasketCell where basket.id = ?1")
    List<BasketCell> findBasketCellsWithBookByBasketId(long id);

}
