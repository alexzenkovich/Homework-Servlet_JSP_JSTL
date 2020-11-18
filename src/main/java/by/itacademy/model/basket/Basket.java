package by.itacademy.model.basket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Basket {

    private long id;
    private List<BasketCell> basketcells;

    public List<BasketCell> getBasketcells() {
        return basketcells;
    }

    public void setBasketcells(List<BasketCell> basketcells) {
        this.basketcells = basketcells;
    }
}
