package model.basket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data

public class Basket {

    private List<BasketCell> bookcells;

    public List<BasketCell> getBookcells() {
        return bookcells;
    }

    public void setBookcells(List<BasketCell> bookcells) {
        this.bookcells = bookcells;
    }
}
