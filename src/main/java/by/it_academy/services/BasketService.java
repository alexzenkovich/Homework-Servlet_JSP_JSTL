package by.it_academy.services;

import by.it_academy.model.basket.Basket;
import by.it_academy.repositories.BasketCellRepository;
import by.it_academy.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private BasketCellService basketCellService;

    public Basket createBasket(Basket basket){
        return basketRepository.save(basket);
    }

    public Basket findBasketById(Long id) {
        return basketRepository.findBasketById(id);
    }

    public Basket findBasketWithCellsById(Long id) {
        return basketRepository.findBasketWithCellsById(id);
    }

    public void deleteBasket(Long id){
        Basket basket = findBasketById(id);
        basketRepository.delete(basket);
    }
}
