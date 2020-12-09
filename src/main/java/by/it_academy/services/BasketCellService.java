package by.it_academy.services;

import by.it_academy.model.basket.BasketCell;
import by.it_academy.repositories.BasketCellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasketCellService {

    @Autowired
    private BasketCellRepository basketCellRepository;

    public BasketCell findBasketCellById(Long id) {
        return basketCellRepository.findBasketCellById(id);
    }

}
