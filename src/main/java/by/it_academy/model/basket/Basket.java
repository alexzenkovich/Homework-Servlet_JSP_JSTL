package by.it_academy.model.basket;

import by.it_academy.model.users.User;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"basketCells", "user"})
@ToString(exclude = {"basketCells", "user"})

@Entity
@Table
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "basket", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketCell> basketCells = new ArrayList<>();

    @OneToOne(mappedBy = "basket", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;

    public void addBasketCell(BasketCell basketCell) {
        this.basketCells.add(basketCell);
        basketCell.setBasket(this);
    }

    public void removeBasketCell(BasketCell basketCell) {
        this.basketCells.remove(basketCell);
        basketCell.setBasket(null);
    }
}
