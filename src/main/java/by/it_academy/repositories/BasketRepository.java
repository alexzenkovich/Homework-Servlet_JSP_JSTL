package by.it_academy.repositories;

import by.it_academy.model.basket.Basket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Basket findBasketById(Long id);

    @Query("select b from Basket b left join fetch b.basketCells where b.id = ?1")
    Basket findBasketWithCellsById(Long id);
}
