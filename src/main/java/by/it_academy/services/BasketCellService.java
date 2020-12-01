package by.it_academy.services;

import by.it_academy.model.basket.BasketCell;
import by.it_academy.model.books.Book;
import by.it_academy.repositories.BasketCellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BasketCellService {

    @Autowired
    private BasketCellRepository basketCellRepository;

    public BasketCell createBasketCell(BasketCell basketCell) {
        return basketCellRepository.save(basketCell);
    }

    public BasketCell findBasketCellByBasket_IdAndBook_Id(Long basketId, Long bookId) {
        return basketCellRepository.findBasketCellByBasket_IdAndBook_Id(basketId, bookId);
    }

    public BasketCell findBasketCellById(Long id) {
        return basketCellRepository.findBasketCellById(id);
    }

    public List<BasketCell> findAllBasketsCellsByBasket_Id(Long id) {
        return basketCellRepository.findBasketCellsByBasket_Id(id);
    }

    public Integer countBasketCellsByBasket_Id(Long id) {
        return basketCellRepository.countBasketCellsByBasket_Id(id);
    }

    public int countBookByUserId(Long id) {
        return basketCellRepository.countBasketCellsByUserId(id);
    }

    public List<BasketCell> findAllBasketsCellsWithBookByBasketId(Long id) {
        return basketCellRepository.findBasketCellsWithBookByBasketId(id);
    }

    public void delete(BasketCell basketCell) {
        basketCellRepository.delete(basketCell);
    }
}
